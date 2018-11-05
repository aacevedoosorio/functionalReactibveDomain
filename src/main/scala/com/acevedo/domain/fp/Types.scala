package com.acevedo.domain.fp

import com.acevedo.domain.Account
import com.acevedo.domain.repositories.AccountRepository
import scalaz.{Kleisli, \/}

object Types {
  type Safe[A] = \/[Throwable, A]
  type SafeAccount = Safe[Account]

  type SafeRepo[R, A] = Kleisli[Safe, R, A]
  type SafeRepoAccount = SafeRepo[AccountRepository, Account]
}

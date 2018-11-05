package com.acevedo.domain.fp

import com.acevedo.domain.Account
import com.acevedo.domain.repositories.AccountRepository
import scalaz.{Kleisli, ValidationNel, \/}

object Types {
  type Safe[A] = \/[Throwable, A]
  type SafeAccount = Safe[Account]

  type Valid[A] = ValidationNel[Throwable, A]
  type ValidAccount = Valid[Account]


  type SafeRepo[R, A] = Kleisli[Safe, R, A]
  type SafeRepoAccount = SafeRepo[AccountRepository, Account]
}

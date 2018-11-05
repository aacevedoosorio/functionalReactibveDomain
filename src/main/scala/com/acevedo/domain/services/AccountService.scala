package com.acevedo.domain.services

import java.util.Date

import com.acevedo.domain.Balance.Amount
import com.acevedo.domain.fp.Types.Safe
import com.acevedo.domain.{Account, Balance}

trait AccountService[A <: Account] {
  def open(no: String, name: String, openDate: Option[Date]): Safe[A]
  def close(account: A, closeDate: Option[Date]): Safe[A]
  def debit(account: A, amount: Amount): Safe[A]
  def credit(account: A, amount: Amount): Safe[A]
  def balance(account: A): Safe[Balance]

  def tranfer(from: A, to: A, amount: Amount): Safe[(A, A, Amount)] =
    for {
      a <- debit(from, amount)
      b <- credit(to, amount)
    } yield(a, b, amount)
}
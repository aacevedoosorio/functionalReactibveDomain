package com.acevedo.domain.services

import java.util.Date

import com.acevedo.domain.Balance.Amount
import com.acevedo.domain.fp.Types.Safe
import com.acevedo.domain.{Account, Balance, MoneyBalance}
import scalaz.Functor
import scalaz.std.list._
import scalaz.std.option._

import scala.language.higherKinds

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

  def fmap[F[_], A, B](fa: F[A])(f: A => B)(implicit ft: Functor[F]): F[B] = ft.map(fa)(f)

  def accountsOpenedBefore(date: Date): List[Account] = ???
  def interestOn(ac: Account): Amount = ???
  def accountFor(no: String): Option[Account] = ???
  def close(ac: Account): Account = ???
  def calculateInterest(dt: Date): List[Amount] = fmap(accountsOpenedBefore(dt))(interestOn)
  def closeAccount(no: String): Option[Account] = fmap(accountFor(no))(close)
}
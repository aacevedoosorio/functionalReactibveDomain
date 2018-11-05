package com.acevedo.domain.services.interpreter

import com.acevedo.domain.Balance.Amount
import com.acevedo.domain._
import com.acevedo.domain.services.AnalyticsService
import com.acevedo.domain.services.interpreter.AnalyticsServiceInterpreter.zeroMoney
import scalaz.{Foldable, Monoid}
import scalaz.std.list._
import scalaz.std.map._
import scalaz.std.math.bigDecimal._

import scala.language.higherKinds

object AnalyticsServiceInterpreter extends AnalyticsService[Transaction, MoneyBalance, Money] {
  override def maxDebitOnday(txns: List[Transaction])(implicit m: Monoid[Money]): Money = {
    mapReduce(txns.filter(_.txnType == DR))(valueOf)
  }

  override def sumBalances(balances: List[MoneyBalance])(implicit m: Monoid[Money]): Money = {
    mapReduce(balances)(creditBalance)
  }

  private def valueOf(txn: Transaction): Money = ???
  private def creditBalance(b: MoneyBalance): Money = ???

  def mapReduce[F[_], A, B](as: F[A])(f: A => B)(implicit fd: Foldable[F], m: Monoid[B]): B = fd.foldMap(as)(f)

  final val zeroMoney = Money(Monoid[Map[Currency, Amount]].zero)
}

trait MoneyInstances {
  implicit def moneyAdditionMonoid: Monoid[Money] = new Monoid[Money] {
    val monoid: Monoid[Map[Currency, Amount]] = implicitly[Monoid[Map[Currency, Amount]]]
    override def zero: Money = zeroMoney

    override def append(f1: Money, f2: => Money): Money = Money(monoid.append(f1.m, f2.m))
  }
}
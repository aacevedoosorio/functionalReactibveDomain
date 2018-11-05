package com.acevedo.domain.services

import scalaz.Monoid

trait AnalyticsService[Transaction, MoneyBalance, Money] {
  def maxDebitOnday(txns: List[Transaction])(implicit m: Monoid[Money]): Money
  def sumBalances(bs: List[MoneyBalance])(implicit m: Monoid[Money]): Money
}

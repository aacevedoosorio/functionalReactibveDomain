package com.acevedo.domain

import java.util.Date

import com.acevedo.domain.Balance.Amount

case class Portfolio(pos: Seq[Position], asOf: Date) {
  def balance(a: Account, ccy: Currency): Amount =
    pos.find(pos => pos.account == a && pos.ccy == ccy).map(_.balance.amount).getOrElse(0)

  def balance(ccy: Currency): Amount =
    pos.find(_.ccy == ccy).map(_.balance.amount).foldLeft(BigDecimal(0)){_ + _}
}

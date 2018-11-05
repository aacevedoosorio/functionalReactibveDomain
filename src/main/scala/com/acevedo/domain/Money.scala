package com.acevedo.domain

import com.acevedo.domain.Balance.Amount

case class Money(m: Map[Currency, Amount])

case class MoneyBalance(b: Money)

sealed trait Currency
case object USD extends Currency
case object EUR extends Currency
case object JPY extends Currency
case object AUD extends Currency
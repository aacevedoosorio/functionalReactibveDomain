package com.acevedo.domain

import com.acevedo.domain.Balance.Amount

case class Balance(amount: Amount = 0)

object Balance {
  type Amount = BigDecimal
}

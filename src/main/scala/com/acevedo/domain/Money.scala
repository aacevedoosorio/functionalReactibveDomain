package com.acevedo.domain

import com.acevedo.domain.Balance.Amount

case class Money(amount: Amount)

sealed trait Currency

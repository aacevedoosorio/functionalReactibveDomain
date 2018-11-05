package com.acevedo.domain

sealed trait TransactionType
case object DR extends TransactionType
case object CR extends TransactionType


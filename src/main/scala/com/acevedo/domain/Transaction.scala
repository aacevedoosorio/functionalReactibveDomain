package com.acevedo.domain

import java.util.Date

case class Transaction(txId: String, accountNo: String, date: Date, amount: Money, txnType: TransactionType, status: Boolean)

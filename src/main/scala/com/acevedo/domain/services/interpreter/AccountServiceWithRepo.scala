package com.acevedo.domain.services.interpreter

import java.util.Date

import com.acevedo.domain.fp.Types.{SafeRepo, SafeRepoAccount}
import com.acevedo.domain.repositories.AccountRepository

trait AccountServiceWithRepo[Account, Amount, Balance] {
  def open(no: String, name: String, openDate: Date): SafeRepoAccount
  def close(no: String, closeDate: Option[Date]): SafeRepoAccount
  def debit(no: String, amount: Amount): SafeRepoAccount
  def credit(no: String, amount: Amount): SafeRepoAccount
  def balance(no: String): SafeRepo[AccountRepository, Balance]
}

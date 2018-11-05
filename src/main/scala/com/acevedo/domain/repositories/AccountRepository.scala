package com.acevedo.domain.repositories

import java.util.Date

import com.acevedo.domain.{Account, MoneyBalance}
import com.acevedo.domain.fp.Types.{Safe, SafeAccount}

trait AccountRepository extends Repository[Account, String] {
  def query(id: String): SafeAccount
  def store(a: Account): SafeAccount
  def balance(accountNo: String): Safe[MoneyBalance]
  def openedOn(date: Date): Safe[Seq[Account]]
}

trait Repository[A, IdType] {
  def query(id: IdType): Safe[A]
  def store(a: A): Safe[A]
}
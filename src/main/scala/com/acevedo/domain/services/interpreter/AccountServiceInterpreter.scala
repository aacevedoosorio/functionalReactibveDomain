package com.acevedo.domain.services.interpreter

import java.util.{Calendar, Date}

import com.acevedo.domain.Balance.Amount
import com.acevedo.domain.fp.Types.Safe
import com.acevedo.domain.services.AccountService
import com.acevedo.domain.{Account, Balance, CheckingAccount, MoneyBalance}
import scalaz.{-\/, \/-}

object AccountServiceInterpreter extends AccountService[CheckingAccount] {
  val today = Calendar.getInstance().getTime

  override def open(no: String, name: String, openDate: Option[Date]): Safe[CheckingAccount] = {
    Account.checkingAccount(no, name, openDate.getOrElse(today), None, Balance())
  }

  override def close(account: CheckingAccount, closeDate: Option[Date]): Safe[CheckingAccount] = {
    if (closeDate.getOrElse(today).before(account.dateOfOpen)) {
      -\/(new Exception("Closing date can't be before than opening"))
    } else {
      \/-(account.copy(dateOfClose = closeDate))
    }
  }

  override def debit(account: CheckingAccount, amount: Amount): Safe[CheckingAccount] = {
    if (account.balance.amount < amount) {
      -\/(new Exception("Insuffcient balance in account"))
    } else {
      \/-(account.copy(balance = Balance(account.balance.amount - amount)))
    }
  }

  override def credit(account: CheckingAccount, amount: Amount): Safe[CheckingAccount] = {
    \/-(account.copy(balance = Balance(account.balance.amount + amount)))
  }

  override def balance(account: CheckingAccount): Safe[Balance] = {
    \/-(account.balance)
  }
}

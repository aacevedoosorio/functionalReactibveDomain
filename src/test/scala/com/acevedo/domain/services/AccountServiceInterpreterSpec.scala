package com.acevedo.domain.services

import java.util.Date

import com.acevedo.domain.Balance.Amount
import com.acevedo.domain.{Balance, CheckingAccount, MoneyBalance}
import org.scalacheck.Prop.forAll
import org.scalacheck.{Gen, _}
import org.scalatest._
import scalaz.\/-

object AccountServiceInterpreterSpec extends Properties("AccountService") with Matchers with DomainGenerators {
  import com.acevedo.domain.services.interpreter.AccountServiceInterpreter._

  property("Equal credit and debit in sequence retain the same balance") =
    forAll((a: CheckingAccount, m: Amount) => {
      val \/-((before, after)) = for {
        b <- balance(a)
        c <- credit(a, m)
        d <- debit(c, m)
      } yield (b, d.balance)

      before == after
    })
}

trait DomainGenerators {
  val bigDecimalGen: Gen[Amount] = Gen.chooseNum(0, 100000000).map(BigDecimal.valueOf(_))
  val balanceGen: Gen[Balance] = bigDecimalGen.map(Balance(_))

  val accountGen: Gen[CheckingAccount] = for {
    no <- Gen.alphaLowerStr suchThat (!_.isEmpty)
    name <- Gen.alphaStr suchThat (!_.isEmpty)
    balance <- balanceGen
  } yield CheckingAccount(no, name, new Date(), None, balance)

  implicit val accountArb: Arbitrary[CheckingAccount] = Arbitrary(accountGen)
}

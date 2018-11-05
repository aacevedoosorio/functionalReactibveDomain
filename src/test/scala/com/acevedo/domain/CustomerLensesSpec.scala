package com.acevedo.domain

import org.scalacheck.Prop.forAll
import org.scalacheck._

object CustomerLensesSpec extends Properties("CustomerLenses") with CustomerLenses with CustomerGenerators {
  property("Customer lenses update the stree number") =
    forAll((c: Customer, no: String) => {
      val newC = customerAddressNoLens.set(c, no)

      newC.address.no == no
    })
}

trait CustomerGenerators {
  val addressGen: Gen[Address] = for {
    no <- Gen.alphaNumStr
    street <- Gen.alphaLowerStr
    city <- Gen.alphaLowerStr
    state <- Gen.alphaLowerStr
    zip <- Gen.alphaNumStr
  } yield Address(no, street, city, state, zip)

  val customerGen: Gen[Customer] = for {
    id <- Gen.chooseNum(1, 5000)
    name <- Gen.alphaLowerStr
    address <- addressGen
  } yield Customer(id, name, address)

  implicit val arbCustomer: Arbitrary[Customer] = Arbitrary(customerGen)
}

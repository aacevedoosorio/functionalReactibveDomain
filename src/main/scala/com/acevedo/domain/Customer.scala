package com.acevedo.domain

import scalaz.Lens

case class Customer(id: Int, name: String, address: Address)
case class Address(no: String, street: String, city: String, state: String, zip: String)

trait CustomerLenses{
  val addressNoLens: Lens[Address, String] = Lens.lensg[Address, String](a => no =>
    a.copy(no = no), _.no)

  val customerAddressLens: Lens[Customer, Address] = Lens.lensg[Customer, Address](c => a =>
    c.copy(address = a), _.address)

  val customerAddressNoLens: Lens[Customer, String] = addressNoLens <=< customerAddressLens
}
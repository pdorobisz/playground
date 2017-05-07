package playground.elasticsearch.data

case class Person(title: String, name: String, age: Int)

case class Address(town: String, street: String)

case class Company(name: String, description: String, phone: String, address: Address, ceo: Person, tags: List[String])

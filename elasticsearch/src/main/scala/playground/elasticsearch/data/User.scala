package playground.elasticsearch.data

import java.util.UUID

case class User(firstName: String, lastName: String, age: Int, company: UserCompany)

case class UserCompany(id: String, name: String)
package domain.model

case class Account(no: String, balance: Double)




object Account {

  def account(): Either[String, Account] = ???

}

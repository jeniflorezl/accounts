package domain.model

import scala.util.Try

case class Account( no: String, balance: Double )

object Account {

  def account( no: String, balance: Double ): Either[String, Account] = {
    if ( isValidNo( no ) && isValidBalance( balance ) ) {
      Right( new Account( no, balance ) )
    } else {
      Left( s"Ocurrió un error al crear la cuenta con el número ${no} y el balance ${balance}" )
    }
  }

  def isValidNo( no: String ): Boolean = {
    !no.isEmpty && Try( no.toInt ).isSuccess
  }

  def isValidBalance( balance: Double ): Boolean = {
    balance >= 0
  }

}

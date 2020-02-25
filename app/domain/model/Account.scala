package domain.model

import domain.model.ValidateAccounts._
import org.joda.time.DateTime

trait Account {
  val no: String
  val dateOfOpen: DateTime
  val dateOfClose: Option[DateTime]
  val balance: Double
  val status: Status
}

case class SavingAccount( no: String, dateOfOpen: DateTime, dateOfClose: Option[DateTime], balance: Double,
                          status: Status, rateOfInterest: Double ) extends Account

case class CheckingAccount( no: String, dateOfOpen: DateTime, dateOfClose: Option[DateTime], balance: Double,
                            status: Status ) extends Account

object Account {

  def savingAccount( no: String, dateOfOpen: DateTime, balance: Double, status: Status,
                     rateOfInterest: Double ): Either[String, SavingAccount] = {

    for {
      no <- isValidNoAccount( no )
      rateOfInterest <- isValidRateOfInteres( rateOfInterest )
    } yield SavingAccount( completeNo( no ), dateOfOpenAccount( dateOfOpen ), Option.empty, validBalance( balance ),
      Active, rateOfInterest )
  }

  def checkingAccount( no: String, dateOfOpen: DateTime, balance: Double, status: Status ): Either[String, CheckingAccount] = {

    isValidNoAccount( no ).fold(
      error => Left( error ),
      no => Right( CheckingAccount( no, dateOfOpen, Option.empty, validBalance( balance ), Active ) )
    )
  }

  def completeNo( no: String ): String = {
    val lenghtNoAccount = 11
    if ( no.length < lenghtNoAccount ) {
      s"%0${lenghtNoAccount}d" format no.toInt
    } else no
  }

  def dateOfOpenAccount( dateOfOpen: DateTime ): DateTime = {
    Option( dateOfOpen ).getOrElse( DateTime.now() )
  }

  def validBalance( balance: Double ): Double = {
    if ( balance >= 0 ) balance else 0
  }
}


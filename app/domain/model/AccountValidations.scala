package domain.model

import scala.util.Try

object ValidateAccounts {
  def isValidNoAccount( no: String ): Either[String, String] = {
    val lenghtNoAccount = 11
    if ( !no.isEmpty && Try( no.toInt ).isSuccess && no.length <= lenghtNoAccount ) Right( no )
    else Left( "The account number must be 11 characters numeric" )
  }

  def isValidRateOfInteres( rateOfInterest: Double ): Either[String, Double] = {
    if ( rateOfInterest >= 0 && rateOfInterest <= 30 ) Right( rateOfInterest )
    else Left( "The rate of interest must be between 0 and 30 percent" )
  }
}
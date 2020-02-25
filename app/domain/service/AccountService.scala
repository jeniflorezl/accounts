package domain.service

import domain.model.{ Account, Active, Status }
import domain.repository.AccountRepository
import org.joda.time.DateTime

import scala.concurrent.{ ExecutionContext, Future }

object AccountService {

  def saveAccount( account: Account, accountRepository: AccountRepository )( implicit ec: ExecutionContext ): Future[Account] = {
    accountRepository.save( account )
  }

  def openAccount( no: String, dateOfOpen: DateTime, balance: Double, rateOfInterest: Double,
                   typeAccount: String )( implicit ec: ExecutionContext ): Future[Either[String, Account]] = {
    Future {
      typeAccount match {
        case "S" => Account.savingAccount( no, dateOfOpen: DateTime, balance, Active, rateOfInterest )
        case "C" => Account.checkingAccount( no, dateOfOpen, balance, Active )
        case _   => Left( "Error, there are only two types of accounts: S - Saving account and C - Checking account" )
      }
    }
  }

}

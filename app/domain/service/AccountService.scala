package domain.service

import domain.model.{Account, AccountStatus}
import domain.repository.AccountRepository

import scala.concurrent.Future

object AccountService {

  def saveAccount(account: Account, accountRepository: AccountRepository) : Future[Account] = {
    accountRepository.save(account)
  }

  def setStatus( account: Account, status: AccountStatus ): Unit = {

  }

}

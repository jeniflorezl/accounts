package domain.service

import domain.model.Account
import domain.repository.AccountRepository
import scala.concurrent.{ ExecutionContext, Future }

object AccountService {

  def saveAccount( account: Account, accountRepository: AccountRepository )( implicit ec: ExecutionContext ): Future[Account] = {
    accountRepository.save( account )
  }

}

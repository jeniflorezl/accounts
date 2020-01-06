package infrastructure.persistence

import domain.model.Account
import domain.repository.AccountRepository
import scala.concurrent.Future
import scala.collection.mutable.{ Map => MMap }
import scala.concurrent.ExecutionContext.Implicits.global

class AccountRepositoryInMemory extends AccountRepository {

  private val repository = MMap.empty[String, Account]

  def save( account: Account ): Future[Account] = Future {
    val result = repository += ( ( account.no, account ) )
    account
  }

}

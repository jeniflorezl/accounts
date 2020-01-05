package infrastructure.persistence

import domain.model.Account
import domain.repository.AccountRepository

import scala.concurrent.Future

class AccountRepositoryInMemory extends AccountRepository {
  def save(account: Account): Future[Account] = {
    Future.successful(account)
  }
}

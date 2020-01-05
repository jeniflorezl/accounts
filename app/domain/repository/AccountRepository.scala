package domain.repository

import domain.model.Account

import scala.concurrent.Future

trait AccountRepository {

  def save(account: Account): Future[Account]


}

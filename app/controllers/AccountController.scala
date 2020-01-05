package controllers

import domain.model.Account
import domain.service.AccountService
import infrastructure.persistence.AccountRepositoryInMemory
import javax.inject._
import play.api.mvc._

import scala.util.{Failure, Success}


@Singleton
class AccountController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val accountRepository = new AccountRepositoryInMemory()

  def saveAccount() = Action { implicit request: Request[AnyContent] =>

    val account = Account("1234", 1000000000)

    AccountService.saveAccount(account, accountRepository) match {
      case Success(acc) => Ok( s"Account saved successfully" )
      case Failure(ex) => InternalServerError(s"Account fail " + ex.getMessage)
    }

  }
}

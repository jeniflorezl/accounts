package controllers

import domain.model.Account
import domain.service.AccountService
import infrastructure.persistence.AccountRepositoryInMemory
import javax.inject._
import play.api.Logger
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{ Failure, Success }

@Singleton
class AccountController @Inject() ( val controllerComponents: ControllerComponents ) extends BaseController {

  private val logger = Logger( this.getClass )
  private val accountRepository = new AccountRepositoryInMemory()

  def saveAccount(): Action[AnyContent] = Action.async {

    implicit request: Request[AnyContent] =>

      createAccount( "1234A", 1000000000 )
        .flatMap( accountEither => accountEither.fold(
          left => {
            logger.error( left )
            Future.successful( InternalServerError( s"Error saving account" ) )
          },
          account => {
            val result: Future[Account] = AccountService.saveAccount( account, accountRepository )

            result
              .map { account =>
                Ok( s"Account no ${account.no} saved successfully" )
              }
              .recoverWith {
                case ex: Exception =>
                  logger.error( s"Error saving account", ex )
                  Future.successful( InternalServerError( s"Error saving account. ${ex.getMessage}" ) )
              }
          }
        ) )
  }

  private[this] def createAccount( no: String, balance: Double ): Future[Either[String, Account]] = {
    Future { Account.account( no, balance ) }
  }

}

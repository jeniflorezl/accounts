package controllers

import domain.model.Account
import domain.service.AccountService
import infrastructure.dto.account.AccountDTO
import infrastructure.persistence.AccountRepositoryInMemory
import javax.inject._
import org.joda.time.DateTime
import play.api.Logger
import play.api.libs.json.JsValue
import play.api.mvc._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton
class AccountController @Inject() ( val controllerComponents: ControllerComponents ) extends BaseController {

  private val logger = Logger( this.getClass )
  private val accountRepository = new AccountRepositoryInMemory()

  def saveAccount(): Action[AnyContent] = Action.async {

    implicit request: Request[AnyContent] =>

      println( request.body.asJson )

      createAccount( trasform( request.body.asJson ) )
        .flatMap( accountEither => accountEither.fold(
          left => {
            logger.error( left )
            Future.successful( InternalServerError( left ) )
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

  private def createAccount( accountDTO: AccountDTO ): Future[Either[String, Account]] = {
    AccountService.openAccount( accountDTO.no, DateTime.parse( accountDTO.dateOfOpen ),
      accountDTO.balance, accountDTO.rateOfInterest, accountDTO.typeAccount )
  }

  private def trasform( json: Option[JsValue] ): AccountDTO = {
    json.map( jsonValue => {
      val no = ( jsonValue \ "no" ).as[String]
      val dateOfOpen = ( jsonValue \ "dateOfOpen" ).as[String]
      val balance = ( jsonValue \ "balance" ).as[Int]
      val rateOfInteres = ( jsonValue \ "rateOfInteres" ).as[Int]
      val typeAccount = ( jsonValue \ "typeAccount" ).as[String]
      AccountDTO( no, dateOfOpen, balance, rateOfInteres, typeAccount )
    } ).orNull
  }

}

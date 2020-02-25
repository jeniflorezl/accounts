package domain.service

import org.joda.time.DateTime
import org.scalatest.FunSuite

class AccountServiceSpec extends FunSuite {

  test("Account opened sucessful") {
    val savingAccount = AccountService.openAccount("123",
      new DateTime(2020, 1, 1, 0, 0, 0),
      100000, 23, "S")

    assert(savingAccount.isRight)
  }

  test("Intend of open an account with a wrong type") {
    val account = AccountService.openAccount("123",
      new DateTime(2020, 1, 1, 0, 0, 0),
      100000, 23, "D")

    assert(account.isLeft)
  }
}

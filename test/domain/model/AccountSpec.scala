package domain.model

import org.joda.time.DateTime
import org.scalatest.FunSuite

class AccountSpec extends FunSuite{
  test("Saving account created sucessful") {
    val savingAccount = Account.savingAccount("123",
      new DateTime(2020, 1, 1, 0,0,0),
      -50000, Active, 23)

    val resultAccount = savingAccount.getOrElse(SavingAccount("456",
      new DateTime(2020, 2, 1, 0,0,0),
      null,
      50000, Active, 30))

    assert(savingAccount.isRight)
    assert(resultAccount.no == "00000000123")
    assert(resultAccount.balance == 0)
  }

  test("Saving account with error in the account's number") {
    val savingAccount = Account.savingAccount("123ABCD",
      new DateTime(2020, 1, 1, 0,0,0),
      110000, Active, 23)

    val resultAccount = savingAccount.getOrElse(SavingAccount("456",
      new DateTime(2020, 2, 1, 0,0,0),
      null,
      50000, Active, 30))

    assert(savingAccount.isLeft)
    assert(resultAccount.no == "456")
    assert(resultAccount.balance == 50000)

  }

  test("Saving account with error in the rate of interest") {
    val savingAccount = Account.savingAccount("123",
      new DateTime(2020, 1, 1, 0,0,0),
      110000, Active, 50)

    assert(savingAccount.isLeft)
  }

  test("Checking account created sucessful") {
    val checkingAccount = Account.checkingAccount("123",
      new DateTime(2020, 1, 1, 0,0,0),
      130000, Active)

    assert(checkingAccount.isRight)
  }

  test("Checking account with error") {
    val checkingAccount = Account.checkingAccount("123ABD",
      new DateTime(2020, 1, 1, 0,0,0),
      130000, Active)

    assert(checkingAccount.isLeft)
  }
}

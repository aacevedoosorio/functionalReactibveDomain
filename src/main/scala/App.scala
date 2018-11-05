import com.acevedo.domain.Balance.Amount
import com.acevedo.domain.fp.Types.Safe
import com.acevedo.domain.repositories.AccountRepository
import com.acevedo.domain.services.interpreter.AccountServiceWithRepo
import com.acevedo.domain.{Balance, CheckingAccount}

object App {//extends App with AccountServiceWithRepo[CheckingAccount, Amount, Balance] {
  /*def op(no: String) =
    for {
      _ <- credit(no, BigDecimal(100))
      _ <- credit(no, BigDecimal(300))
      _ <- debit(no, BigDecimal(160))
      b <- balance(no)
    } yield b

  def perform(no: String, accountRepository: AccountRepository): Safe[Balance] = op(no).run(accountRepository)*/
}

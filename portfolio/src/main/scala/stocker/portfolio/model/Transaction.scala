package stocker.portfolio.model

import java.time.LocalDateTime

/**
  * Created by marcin1 on 9/1/17.
  */

trait TransactionType
object TransactionType {
    case object Buy extends TransactionType
    case object Sell extends TransactionType
}

case class Transaction(id: String, ttype: TransactionType, date: LocalDateTime, price: Double) extends Ordered[Transaction] {
    def compare(that: Transaction): Int =
        if (this.date.isAfter(that.date)) 1
        else if (this.date.isBefore(this.date)) -1
        else 0
}

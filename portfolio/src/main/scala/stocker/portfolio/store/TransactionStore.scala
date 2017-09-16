package stocker.portfolio.store

import stocker.portfolio.model.Transaction

/**
  * Created by marcin1 on 9/2/17.
  */
trait TransactionStore {
    def find(transactionId: String): Option[Transaction]

    def findByStock(stock: String, exchange: String): Seq[Transaction]
}

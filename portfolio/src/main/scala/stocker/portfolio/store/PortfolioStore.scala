package stocker.portfolio.store

import stocker.portfolio.model.{Stock, Transaction}
import java.time.LocalDate

/**
  * Created by marcin1 on 8/23/17.
  */
trait PortfolioStore {
    def listStocks(userId: String): Seq[Stock]

    def listTransactions(userId: String, stock: Stock): Seq[Transaction]

    def addStock(userId: String, stock: Stock): Unit

    def removeStock(userId: String, stock: Stock): Unit

    def addTransaction(userId: String, stock: Stock, transaction: Transaction): Unit

    def removeTransaction(transactionId: String): Unit
}

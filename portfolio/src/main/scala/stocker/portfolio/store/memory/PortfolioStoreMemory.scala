package stocker.portfolio.store.memory

import java.time.LocalDate

import stocker.portfolio.model.{PortfolioItem, Stock, Transaction}
import stocker.portfolio.store.PortfolioStore

import scala.collection.mutable.MutableList

/**
  * Created by marcin1 on 8/23/17.
  */
class PortfolioStoreMemory extends PortfolioStore {
    val items = MutableList[Stock](
        Stock("LL", "NYSE"),
        Stock("TSLA", "NASDAQ"),
        Stock("GPRO", "NASDAQ")
    )

    def addStock(userId: String, stock: Stock): Unit =
        items += stock

    def listStocks(userId: String): Seq[Stock] = items

    def listTransactions(userId: String, stock: Stock): Seq[Transaction] = ???

    def removeStock(userId: String, stock: Stock): Unit = ???

    def addTransaction(userId: String, stock: Stock, transaction: Transaction): Unit = ???

    def removeTransaction(transactionId: String): Unit = ???
}

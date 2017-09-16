package stocker.portfolio.usecase

import java.time.LocalDate
import stocker.portfolio.model._
import stocker.portfolio.store._

/**
  * Created by marcin1 on 8/25/17.
  */
class PortfolioAddRemove(portfolioStore: PortfolioStore) {
    def addStock(userId: String, stock: Stock): Unit = {
        portfolioStore.addStock(userId, stock)
    }

    def removeStock(userId: String, stock: Stock): Unit = {
        portfolioStore.removeStock(userId, stock)
    }

    def addTransaction(userId: String, stock: Stock, transcation: Transaction): Unit = ???

    def removeTransaction(userId: String, transactionId: String): Unit = ???
}

object PortfolioAddRemove {
    def getInstance = new PortfolioAddRemove(ConfigStore.portfolioStore)
}

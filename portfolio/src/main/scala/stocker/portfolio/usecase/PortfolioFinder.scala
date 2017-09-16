package stocker.portfolio.usecase

/**
  * Created by marcin1 on 9/2/17.
  */

import stocker.portfolio.model.{PortfolioItem, Stock, Transaction, TransactionType}
import stocker.portfolio.store.{ConfigStore, PortfolioStore}

class PortfolioFinder(portfolioStore: PortfolioStore) {
    def listPortfolio(userId: String): Seq[PortfolioItem] = ???

    def getTransactions(stock: Stock, ttype: TransactionType): Seq[Transaction] = ???
}

object PortfolioFinder {
    def getInstance = new PortfolioFinder(ConfigStore.portfolioStore)
}

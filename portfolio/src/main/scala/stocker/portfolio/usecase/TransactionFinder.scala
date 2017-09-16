package stocker.portfolio.usecase

import stocker.portfolio.model.{Stock, Transaction, TransactionType}
import stocker.portfolio.store.TransactionStore

import scala.util.Try

/**
  * Created by marcin1 on 9/2/17.
  */
class TransactionFinder(tranStore: TransactionStore) {
    def newestBuy(stock: Stock): Option[Transaction] = {
        val trans = tranStore.findByStock(stock.name, stock.exchange)

        val buy: Seq[Transaction] = trans.filter { t =>
            t.ttype match {
                case TransactionType.Buy => true
                case _ => false
            }
        }

        Try(buy.head).toOption
    }
}

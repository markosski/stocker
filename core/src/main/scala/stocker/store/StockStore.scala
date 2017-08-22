package stocker.store

import org.joda.time.LocalDate
import stocker.model.Stock
import stocker.store.impl.StockStoreES

/**
  * Created by marcin on 9/25/16.
  */
trait StockStore {

    def getMany(start: Int, offset: Int): List[Stock]

    def getManyBeforeDate(start: Int, offset: Int, checkedDate: LocalDate): List[Stock]

    def find(name: String, exchange: String): Option[Stock]

    def updateChecked(name: String, exchange: String, date: LocalDate): Unit

    def add(stock: Stock): Unit
}

object StockStore {
    lazy val getInstance: StockStore = {
        new StockStoreES
    }
}

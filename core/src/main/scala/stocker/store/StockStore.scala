package stocker.store

import org.joda.time.LocalDate
import stocker.model.StockDetails
import stocker.store.es.StockStoreES

/**
  * Created by marcin on 9/25/16.
  */
trait StockStore {
    def getMany(start: Int, offset: Int): List[StockDetails]

    def getManyBeforeDate(start: Int, offset: Int, checkedDate: LocalDate): List[StockDetails]

    def find(name: String, exchange: String): Option[StockDetails]

    def updateChecked(name: String, exchange: String, date: LocalDate): Unit

    def add(stock: StockDetails): Unit
}

object StockStore {
    lazy val getInstance: StockStore = {
        new StockStoreES
    }
}

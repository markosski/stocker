package stocker.store

import org.joda.time.LocalDate
import stocker.model.StockData
import stocker.store.impl._

/**
  * Created by marcin on 9/25/16.
  */
trait StockDataStore {

    def add(data: StockData): Unit

    def findBetween(symbol: String, exchange: String, startDate: LocalDate, endDate: LocalDate, start: Int, offset: Int): Seq[StockData]

    def latest(symbol: String, exchange: String): Option[StockData]

    def deleteAll(symbol: String, exchange: String): Unit
}

object StockDataStore {
    lazy val getInstance: StockDataStore = {
        new StockDataStoreES
    }
}

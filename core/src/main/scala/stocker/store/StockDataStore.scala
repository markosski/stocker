package stocker.store

import org.joda.time.LocalDate
import stocker.model.StockDay
import stocker.store.es.StockDataStoreES

/**
  * Created by marcin on 9/25/16.
  */
trait StockDataStore {

    def add(data: StockDay): Unit

    def findBetween(symbol: String, exchange: String, startDate: LocalDate, endDate: LocalDate, start: Int, offset: Int): Seq[StockDay]

    def latest(symbol: String, exchange: String): Option[StockDay]

    def deleteAll(symbol: String, exchange: String): Unit
}

object StockDataStore {
    lazy val getInstance: StockDataStore = {
        new StockDataStoreES
    }
}

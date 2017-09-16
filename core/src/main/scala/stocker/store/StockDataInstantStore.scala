package stocker.store

import org.joda.time.LocalDate
import stocker.model.StockDataInstant
import stocker.store.alpha.StockDataInstantStoreAlpha

/**
  * Created by marcin1 on 8/23/17.
  */
trait StockDataInstantStore {
    def findBetween(symbol: String, exchange: String, startDate: LocalDate, endDate: LocalDate): Seq[StockDataInstant]

    def latest(symbol: String, exchange: String): Option[StockDataInstant]
}

object StockDataInstantStore {
    lazy val getInstance: StockDataInstantStore = {
        new StockDataInstantStoreAlpha
    }
}

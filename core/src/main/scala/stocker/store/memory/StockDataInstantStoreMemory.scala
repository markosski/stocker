package stocker.store.memory

/**
  * Created by marcin1 on 9/1/17.
  */

import stocker.store.StockDataInstantStore
import org.joda.time.LocalDate
import stocker.model.StockDataInstant
import java.time.LocalDateTime


class StockDataInstantStoreMemory extends StockDataInstantStore {
    def findBetween(symbol: String, exchange: String, startDate: LocalDate, endDate: LocalDate): Seq[StockDataInstant] = List(
        StockDataInstant(10.15, LocalDateTime.parse("2017-09-01T10:32:00")),
        StockDataInstant(10.25, LocalDateTime.parse("2017-09-01T10:31:00")),
        StockDataInstant(10.35, LocalDateTime.parse("2017-09-01T10:30:00"))

    )

    def latest(symbol: String, exchange: String): Option[StockDataInstant] = {
        Some(StockDataInstant(10.15, LocalDateTime.parse("2017-09-01T10:30:00")))
    }
}

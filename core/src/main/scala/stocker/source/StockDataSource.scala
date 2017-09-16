package stocker.source

import org.joda.time.LocalDate
import stocker.model.StockDay
import stocker.source.impl.StockDataSourceGoogle

/**
  * Created by marcin on 9/25/16.
  */
abstract class StockDataSource {

    def getRecentData(symbol: String, exchange: String, from: LocalDate): List[StockDay]

    def getAllTimeData(symbol: String, exchange: String): List[StockDay]

}

object StockDataSource {
    def getInstance = {
        new StockDataSourceGoogle
    }
}


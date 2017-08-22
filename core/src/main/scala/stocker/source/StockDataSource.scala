package stocker.source

import org.joda.time.LocalDate
import stocker.model.StockData
import stocker.source.impl.StockDataSourceGoogle

/**
  * Created by marcin on 9/25/16.
  */
abstract class StockDataSource {

    def getRecentData(symbol: String, exchange: String, from: LocalDate): List[StockData]

    def getAllTimeData(symbol: String, exchange: String): List[StockData]

}

object StockDataSource {
    def getInstance = {
        new StockDataSourceGoogle
    }
}


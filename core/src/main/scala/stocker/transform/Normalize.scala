package stocker.transform

import stocker.model.{StockData, StockValue}

/**
  * Created by marcin on 10/4/16.
  */
object Normalize {
    def apply(data: Seq[StockData]): Seq[StockValue] = {
        val min: Double = (data.reduceLeft((a, b) => if (a.close < b.close) a else b)).close
        val max: Double = (data.reduceLeft((a, b) => if (a.close > b.close) a else b)).close - min

        for (day <- data) yield StockValue(day.symbol, day.exchange, (day.close - min) / max, day.date)
    }
}

package stocker.scanner

import stocker.store._
import stocker.transform._
import org.joda.time._
import stocker.model.{Stock, StockDetails}
import org.slf4j.LoggerFactory

object MovAvgBuy {
    val logger = LoggerFactory.getLogger(getClass.getName)

    def main(args: Array[String]): Unit = {
        val stockStore = StockStore.getInstance
        val stockDataStore = StockDataStore.getInstance

        def calcMovAvg(s: StockDetails): Double = {
            val movAvgDaysShort = 15
            val movAvgDaysNarrow = 50
            val movAvgDaysWide = 200

            val days = stockDataStore.findBetween(s.symbol, s.exchange, LocalDate.now.minusDays(500), LocalDate.now, 0, 1000)
            logger.info(s"Found ${days.size} data points for stock ${s.symbol}")

            if (days.size < movAvgDaysWide) {
                logger.info(s"Could not retrieve enough data points for stock ${s.symbol}")
                0.0
            } else {
                val movAvg50: Double = MovAvg(movAvgDaysNarrow, days.map(_.close)).last
                val movAvg200: Double = MovAvg(movAvgDaysWide, days.map(_.close)).last
                logger.info(s"movAvg50 = $movAvg50, movAvg200 = $movAvg200, for symbol ${s.symbol}")

                val change = (movAvg50 - movAvg200) / movAvg50
                logger.info(s"change $change for stock ${s.symbol}")
                change
            }
        }

        val stocks = stockStore.getMany(0, 500)
        logger.info(s"Scanning ${stocks.size} stocks")

        val res = stocks.map(s => (s, calcMovAvg(s)))
        res.filter(x => x._2 > 0.25).foreach(x => println((x._1.symbol, x._2)))
    }
}

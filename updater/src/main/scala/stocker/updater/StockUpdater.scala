package stocker.updater

import java.util.concurrent.Executors

import org.slf4j.LoggerFactory
import org.joda.time.{LocalDate, LocalTime}
import stocker.model.StockDetails
import stocker.source.StockDataSource
import stocker.store._
import stocker.util.DateUtil

import scala.collection.mutable
import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Success, Try}

/**
  * Created by marcin on 9/27/16.
  *
  * # Stock data
  * - loop through all active stocks.
  * - for each selected retrieve latest day.
  * - pull data from that day to present day, make sure full data range was retrieved otherwise notify
  *     if data is missing for some period of time mark as inactive.
  * - index new data.
  *
  * References:
  * http://stackoverflow.com/questions/15285284/how-to-configure-a-fine-tuned-thread-pool-for-futures
  */

object StockUpdater {
    val logger = LoggerFactory.getLogger(getClass.getName)
    val queue = mutable.Queue[StockDetails]()
    val executeTime = new LocalTime(16, 0, 0)
    val interval = 1 * 60 // seconds
    val concurrency = 4
    val batchSize = 500

    def loop = {
        while (true) {
            val now = new LocalTime()
            logger.info(s"Current time: ${now}, trigger time: ${executeTime}")
            if (now.isAfter(executeTime) && queue.isEmpty) {
                main(Array[String]())
            }
            logger.info(s"Sleeping for: $interval seconds.")
            Thread.sleep(interval * 1000)
        }
    }

    def main(args: Array[String]) = {
        implicit val ec = ExecutionContext.fromExecutor(Executors.newFixedThreadPool(concurrency))

        val stockStore = StockStore.getInstance
        val stockDataStore = StockDataStore.getInstance
        val stockDataSource = StockDataSource.getInstance

        // Allow supplying stock list as argument
        // e.g. NASDAQ:TSLA,NASDAQ:MSFT
        val userStocks = Try(args(0)).toOption

        val stocks: List[StockDetails] = userStocks match {
            case Some(x) => (for ( stock <- x.toString.split(",")) yield {
                val pair = stock.split(":")
                stockStore.find(pair(1), pair(0)) match {
                    case Some(x) => x
                    case None => throw new Exception("Stock does not exist.")
                }
            })(collection.breakOut)
            case None => stockStore.getManyBeforeDate(0, batchSize, new LocalDate().minusDays(1))
        }

        logger.info(s"Size of stock in this batch: ${stocks.size}")

        queue.enqueue(stocks: _*)

        while (queue.size > 0) {
            val stock = queue.dequeue()
            val latest = stockDataStore.latest(stock.symbol, stock.exchange)

            // TODO: handle exception
            // TODO: record future results to know if all finished
            Future {
                Try {
                    val stockData = latest match {
                        case Some(x) => stockDataSource.getRecentData(stock.symbol, stock.exchange, DateUtil.ISODateFormat.parseLocalDate(x.date))
                        case None => stockDataSource.getAllTimeData(stock.symbol, stock.exchange)
                    }

                    logger.info(s"Stock ${stock.symbol} has ${stockData.size} new entries.")

                    for (stockDay <- stockData) stockDataStore.add(stockDay)
                }

                stockStore.updateChecked(stock.symbol, stock.exchange, new LocalDate)
                logger.info(s"Stock ${stock.symbol} has been updated.")
            }

            Thread.sleep(100)
        }
    }
}

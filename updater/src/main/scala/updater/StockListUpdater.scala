package updater

import org.apache.log4j.Logger
import stocker.source.StockListSource
import stocker.store.StockStore


/**
  * Created by marcin on 9/27/16.
  *
  * # Stock list
  * - fetch list for selected exchanges
  * - create two lists for new and removed stocks
  * - determine if stock should be added (e.g. if it's too know there is no benefit of adding it)
  * - determine if stock should be removed
  * - stamp record with date
  */
object StockListUpdater extends App {
    val logger = Logger.getLogger("stocker")

    val stockStore = StockStore.getInstance
    val tickerList = StockListSource.getInstance

    logger.info("Working on NASDAQ")
    for (ticker <- tickerList.getAll("NASDAQ")) stockStore.add(ticker)
}

package stocker.store.memory

import org.joda.time.LocalDate
import stocker.model.StockDetails
import stocker.store.StockStore

import scala.util.Try

/**
  * Created by marcin on 9/25/16.
  */
class StockStoreInMemory extends StockStore {
    private var stockNames: List[StockDetails] = List(
        StockDetails("GPRO", "", "NASDAQ", "", "", "2016-09-10", true),
        StockDetails("TSLA", "", "NASDAQ", "", "", "2016-09-10", true)
    )
    def getMany(start: Int, offset: Int): List[StockDetails] = stockNames

    def getManyBeforeDate(start: Int, offset:Int, checkedDate: LocalDate) = ???

    def find(name: String, exchange: String) = {
        val res = for (stock <- stockNames if stock.symbol == name) yield stock
        Try(res.head).toOption
    }

    def updateChecked(name: String, exchange: String, date: LocalDate) = ???

    def add(stock: StockDetails): Unit = stockNames = stockNames :+ stock
}

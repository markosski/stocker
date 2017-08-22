package stocker.source

import stocker.model._
import stocker.source.impl.StockListSourceNasdaq

/**
  * Created by marcin on 9/27/16.
  */
abstract class StockListSource {
    def getAll(exchange: String): Seq[Stock]
}

object StockListSource {
    def getInstance = new StockListSourceNasdaq
}

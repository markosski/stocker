package stocker.transform

import stocker.model.{StockData, StockValue}

import scala.collection.mutable

/**
  * Created by marcin on 10/3/16.
  */
object MovAvg {
    def apply(size: Int, data: Seq[Double]): Seq[Double] = {
        var dataWindow = mutable.MutableList[Double]()

        for (el <- data) yield {
            if (dataWindow.size < size) {
                dataWindow += el
                0.0
            } else {
                dataWindow = dataWindow.tail += el
                dataWindow.sum / size
            }
        }
    }
}

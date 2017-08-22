package stocker.model

/**
  * Created by marcin on 10/2/16.
  */
case class StockData(
                            symbol: String,
                            exchange: String,
                            date: String,
                            open: Double,
                            close: Double,
                            low: Double,
                            high: Double,
                            volume: Integer) extends Ordered[StockData] {

    def compare(that: StockData): Int = this.date compare that.date
}

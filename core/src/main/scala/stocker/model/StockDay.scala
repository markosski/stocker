package stocker.model

/**
  * Created by marcin on 10/2/16.
  */
case class StockDay(
                            symbol: String,
                            exchange: String,
                            date: String,
                            open: Double,
                            close: Double,
                            low: Double,
                            high: Double,
                            volume: Integer) extends Ordered[StockDay] {

    def compare(that: StockDay): Int = this.date compare that.date
}

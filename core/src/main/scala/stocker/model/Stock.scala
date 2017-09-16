package stocker.model

/**
  * Created by marcin1 on 9/1/17.
  */
case class Stock(symbol: String, exchange: String) extends Ordered[Stock] {
    def compare(that: Stock) = this.symbol compare that.symbol
}

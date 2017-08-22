package stocker.store

/**
  * Created by marcin on 10/2/16.
  */
trait StockDataTransform {
    def movingAVG(size: Int): Unit
}

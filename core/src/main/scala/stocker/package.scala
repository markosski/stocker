/**
  * Created by marcin on 10/11/16.
  */
package object stocker {
    lazy val stockStore = stocker.store.StockStore.getInstance
    lazy val stockDataStore = stocker.store.StockDataStore.getInstance
}

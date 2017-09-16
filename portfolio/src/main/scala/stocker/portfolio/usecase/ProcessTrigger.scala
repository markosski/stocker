package stocker.portfolio.usecase

import stocker.model.StockDataInstant
import stocker.portfolio.store.{TransactionStore, TriggerStore}
import stocker.portfolio.model._
import stocker.store.StockDataInstantStore

/**
  * Created by marcin1 on 9/2/17.
  */
class ProcessTrigger(
                            triggerStore: TriggerStore,
                            triggerFinder: TriggerFinder,
                            stockInstantStore: StockDataInstantStore) {

    def processTrigger(triggerId: String): Unit = {
        triggerFinder.find(triggerId) match {
            case Some(t) => {

                t.cond match {
                    case Condition.PriceLowerBound(price) => {
                        for (latest <- stockInstantStore.latest(t.stock.name, t.stock.exchange)) {
                            if (latest.value < price) println("Notify")
                        }
                    }
                    case Condition.PriceUpperBound(price) => {
                        for (latest <- stockInstantStore.latest(t.stock.name, t.stock.exchange)) {
                            if (latest.value > price) println("Notify")
                        }
                    }
                }
            }
            case None => _
        }
    }
}

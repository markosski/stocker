package stocker.portfolio.store.memory

import stocker.portfolio.store.TriggerStore
import stocker.portfolio.model._
import stocker.util.UUID

import scala.collection.mutable

/**
  * Created by marcin1 on 8/25/17.
  */
class TriggerStoreMemory extends TriggerStore {

    val triggers = mutable.MutableList(
        Trigger(UUID.get, Stock("LL", "NYSE"), Condition.PriceLowerBound(20.00), Action.EmailNotification),
        Trigger(UUID.get, Stock("LL", "NYSE"), Condition.PriceUpperBound(30.00), Action.EmailNotification)
    )

    def list(userId: String): Seq[Trigger] = triggers

    def add(userId: String, stock: Stock, cond: Condition, action: Action): Unit = {
        triggers += Trigger(UUID.get, stock, cond, action)
    }

    def remove(triggerId: String): Unit = ???
}

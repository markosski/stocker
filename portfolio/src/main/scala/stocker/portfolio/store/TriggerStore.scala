package stocker.portfolio.store

import stocker.portfolio.model._

/**
  * Created by marcin1 on 8/25/17.
  */
trait TriggerStore {

    def list(userId: String): Seq[Trigger]

    def add(userId: String, stock: Stock, cond: Condition, action: Action): Unit

    def remove(triggerId: String): Unit
}

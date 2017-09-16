package stocker.portfolio.usecase

import stocker.portfolio.model.Trigger
import stocker.portfolio.store.{ConfigStore, TriggerStore}

/**
  * Created by marcin1 on 9/2/17.
  */
class TriggerFinder(triggerStore: TriggerStore) {
    def findByUser(userId: String): Seq[Trigger] = ???

    def find(triggerId: String): Option[Trigger] = ???

    def allTransactions(offset: Int, limit: Int) = ???
}

object TriggerFinder {
    def getInstance = new TriggerFinder(ConfigStore.triggerStore)
}

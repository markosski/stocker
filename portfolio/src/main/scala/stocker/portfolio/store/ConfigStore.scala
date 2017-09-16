package stocker.portfolio.store

import memory._

/**
  * Created by marcin1 on 8/24/17.
  */
object ConfigStore {
    lazy val portfolioStore: PortfolioStore = new PortfolioStoreMemory
    lazy val userStore: UserStore = new UserStoreMemory
    lazy val triggerStore: TriggerStore = new TriggerStoreMemory
}

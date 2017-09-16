package stocker.portfolio.usecase

import stocker.portfolio.store.UserStore
import stocker.portfolio.model._

/**
  * Created by marcin1 on 9/2/17.
  */
class UserFinder(userStore: UserStore) {
    def findUserById(userId: String): Option[User] = userStore.getById(userId)

    def findUserByEmail(email: String): Option[User] = userStore.get(email)
}

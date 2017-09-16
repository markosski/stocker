package stocker.portfolio.usecase

import stocker.portfolio.store._
import stocker.util.UUID
import stocker.portfolio.model._

/**
  * Created by marcin1 on 8/25/17.
  */
class UserCreateUpdate(store: UserStore) {
    def createUser(email: String, fname: String, lname: String): Unit = {
        store.insert(UUID.get, email, fname, lname)
    }

    def createUpdateUserCredentials(userId: String, pass: String): Unit = ???

    def updateUser(userId: String, fname: String, lname: String): Unit = ???

    def authenticate(email: String, pass: String): Boolean = ???
}

object UserCreateUpdate {
    def getInstance: UserCreateUpdate = {
        new UserCreateUpdate(ConfigStore.userStore)
    }
}

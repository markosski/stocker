package stocker.portfolio.store

import stocker.portfolio.model._
/**
  * Created by marcin1 on 8/25/17.
  */
trait UserStore {
    def insert(userId: String, email: String, fname: String, lname: String): Unit

    def get(email: String): Option[User]

    def getById(userId: String): Option[User]
}

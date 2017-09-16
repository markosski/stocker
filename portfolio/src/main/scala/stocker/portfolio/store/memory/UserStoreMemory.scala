package stocker.portfolio.store.memory

import scala.util.Try
import stocker.portfolio.model._
import stocker.portfolio.store._

import scala.collection.mutable

/**
  * Created by marcin1 on 8/25/17.
  */
class UserStoreMemory extends UserStore {
    val users = mutable.MutableList(
        User("dc7158fa-866a-4790-9ffd-3af162120254", "marcin.kossakowski@gmail.com", "Marcin", "Kossakowski")
    )

    def insert(userId: String, email: String, fname: String, lname: String): Unit = {
        users += User(userId, email, fname, lname)
    }

    def get(email: String): Option[User] = Try(users.filter(x => x.email == email).head).toOption

    def getById(userId: String): Option[User] = Try(users.filter(x => x.id == userId).head).toOption
}

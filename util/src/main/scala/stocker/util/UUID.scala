package stocker.util

/**
  * Created by marcin1 on 9/1/17.
  */
import java.util

object UUID {
    def get = util.UUID.randomUUID().toString
}

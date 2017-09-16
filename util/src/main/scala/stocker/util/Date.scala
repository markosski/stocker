package stocker.util

import org.joda.time.{DateTime, LocalDate}
import org.joda.time.format.DateTimeFormat

/**
  * Created by marcin on 9/27/16.
  */
object Date {
    def unixTimeNow = new DateTime().getMillis / 1000

    def dateNow = new LocalDate()

    val ISODateFormat = DateTimeFormat.forPattern("yyyy-MM-dd")
}

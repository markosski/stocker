package stocker.source.impl

import java.text.SimpleDateFormat
import java.util.Date

import org.joda.time.{DateTime, LocalDate}
import org.apache.log4j.Logger
import org.joda.time.format.DateTimeFormat
import stocker.model.StockData
import stocker.source.StockDataSource
import stocker.util.DateUtil

import scalaj.http._

/**
  * Created by marcin on 9/25/16.
  */
class StockDataSourceGoogle extends StockDataSource {

    private val logger = Logger.getLogger(getClass.getName)

    val sourceDateFormat = DateTimeFormat.forPattern("d-MMM-yy")

    /**
      * Build Google API url with stock name, start and end date.
      *
      * @param symbol, Name of ticker
      * @param startDate, Start date
      * @param endDate, End date
      * @return
      */
    private def buildUrl(symbol: String, exchange: String, startDate: LocalDate, endDate: LocalDate) = {
        val startDateFormatted = sourceDateFormat.print(startDate)
        val endDateFormatted = sourceDateFormat.print(endDate)

        s"https://www.google.com/finance/historical?q=${exchange}:${symbol}&startdate=${startDateFormatted}&enddate=${endDateFormatted}&output=csv"
    }

    /**
      * Get most recent data to update stock. Defaults to last 5 days.
      *
      * @param symbol, Name of ticker
      * @param startDate, Start date
      */
    def getRecentData(symbol: String, exchange: String, startDate: LocalDate = new LocalDate().minusDays(5)): List[StockData] = {
        val endDate = new LocalDate
        val url = buildUrl(symbol, exchange, startDate, endDate)

        logger.debug(s"Request with url: $url")
        val resp = Http(url)

        resp.asString.body.toString.split("\\n").tail.
                map(x => x.split(",")).
                map(x => StockData(
                        symbol,
                        exchange,
                        DateUtil.ISODateFormat.print(sourceDateFormat.parseLocalDate(x(0))),
                        x(1) match { case "-" => 0.0 case s => s.toDouble },
                        x(2) match { case "-" => 0.0 case s => s.toDouble },
                        x(3) match { case "-" => 0.0 case s => s.toDouble },
                        x(4) match { case "-" => 0.0 case s => s.toDouble },
                        x(5) match { case "-" => 0 case s => s.toInt }
                    )
                ).toList
    }

    /**
      * Attempt to get all time data, starting from 2000.
      *
      * @param symbol, Name of ticker
      */
    def getAllTimeData(symbol: String, exchange: String): List[StockData] = {
        getRecentData(symbol, exchange, new LocalDate(2000, 1, 1))
    }
}

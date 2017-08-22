package stocker.source.impl

import org.joda.time.LocalDate
import stocker.model.Stock
import stocker.source.StockListSource
import stocker.util.DateUtil

import scalaj.http.Http

/**
  * Created by marcin on 9/27/16.
  */
class StockListSourceNasdaq extends StockListSource {
    def buildUrl(exchange: String) = s"http://www.nasdaq.com/screening/companies-by-name.aspx?exchange=$exchange&render=download"

    def getAll(exchange: String) = {
        if (!List("NYSE", "NASDAQ").contains(exchange)) throw new Exception(s"This exchange is not available: $exchange")

        val resp = Http(buildUrl(exchange))

        resp.asString.body.toString.split("\\n").tail.
                map(x => x.split("\",")).
                map(x => Stock(
                    x(0).replaceAll("\"", ""),
                    x(1).replaceAll("\"", ""),
                    exchange,
                    x(5).replaceAll("\"", ""),
                    x(6).replaceAll("\"", ""),
                    DateUtil.ISODateFormat.print(new LocalDate(2010, 1, 1)),
                    true)
                ).toList
    }
}

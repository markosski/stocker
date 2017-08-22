package stocker.store.impl

import stocker.model.{Stock, StockData}
import stocker.store.StockStore
import stocker.store.{ES, StockDataStore}
import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.{HitAs, RichSearchHit}
import org.elasticsearch.search.sort.SortOrder
import org.joda.time.LocalDate
import stocker.util.DateUtil

/**
  * Created by marcin on 9/26/16.
  */
class StockStoreES extends StockStore {

    // Convert ES hit into model object
    // https://github.com/sksamuel/elastic4s#search-conversion
    implicit object StockHitAs extends HitAs[Stock] {
        override def as(hit: RichSearchHit): Stock = {
            Stock(
                hit.sourceAsMap("symbol").toString,
                hit.sourceAsMap("companyName").toString,
                hit.sourceAsMap("exchange").toString,
                hit.sourceAsMap("sector").toString,
                hit.sourceAsMap("industry").toString,
                hit.sourceAsMap("lastChecked").toString,
                hit.sourceAsMap("active").toString.toBoolean
            )
        }
    }

    def getMany(start: Int, offset: Int): List[Stock] = {
        val res = ES.client.execute { search in "stocks" / "stock" query {
            bool {
                must (
                    termQuery("active", true)
                )
            }
        } start start limit offset } await

        if (res.hits.size > 0) {
            res.as[Stock].toList
        } else {
            List[Stock]()
        }
    }

    def getManyBeforeDate(start: Int, offset:Int, checkedDate: LocalDate) = {
        val res = ES.client.execute { search in "stocks" / "stock" query {
            bool {
                must (
                    termQuery("active", true)
                )
                not (
                    rangeQuery("lastChecked") from DateUtil.ISODateFormat.print(new LocalDate) to "now"
                )
            }
        } start start limit offset } await

        if (res.hits.size > 0) {
            res.as[Stock].toList
        } else {
            List[Stock]()
        }

    }

    def find(symbol: String, exchange: String): Option[Stock] = {
        val res = ES.client.execute {
            search in "stocks" / "stock" query {
                bool {
                    must(
                        termQuery("symbol", symbol),
                        termQuery("exchange", exchange)
                    )
                }
            }
        } await

        if (res.hits.size == 1) {
            val hit = res.hits(0)
            Some(res.as[Stock].head)
        } else if (res.hits.size > 1) {
            throw new Exception("Expected none or only 1 result, found more.")

        } else {
            None
        }
    }

    def updateChecked(symbol: String, exchange: String, date: LocalDate) = {
        ES.client.execute {
            update id s"$exchange:$symbol" in "stocks" / "stock" doc (
                "lastChecked" -> DateUtil.ISODateFormat.print(date)
            )
        } await
    }

    def add(stock: Stock): Unit = {
        ES.client.execute {
            index into "stocks" / "stock" id s"${stock.exchange}:${stock.symbol}" fields (
                    "symbol" -> stock.symbol,
                    "companyName" -> stock.companyName,
                    "exchange" -> stock.exchange,
                    "sector" -> stock.sector,
                    "industry" -> stock.industry,
                    "lastChecked" -> stock.lastChecked,
                    "active" -> stock.active
                    )
        } await
    }
}

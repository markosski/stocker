package stocker.store.impl

import stocker.model.{StockDetails, StockDay}
import stocker.store.StockDataStore
//import com.sksamuel.elastic4s.ElasticDsl._
//import com.sksamuel.elastic4s.{HitAs, RichSearchHit}
import org.elasticsearch.search.sort.SortOrder
import org.joda.time.{DateTime, LocalDate}
import stocker.util.DateUtil

/**
  * Created by marcin1 on 8/23/17.
  */
//class StockDataInstantStoreES {
//
//    implicit object StockDataInstantHitAs extends HitAs[StockDataInstant] {
//        override def as(hit: RichSearchHit): StockData = {
//            StockData(
//                hit.sourceAsMap("symbol").toString,
//                hit.sourceAsMap("exchange").toString,
//                hit.sourceAsMap("dateTime").toString,
//                hit.sourceAsMap("value").toString.toDouble
//            )
//        }
//    }
//
//    def add(data: StockData) = {
//        ES.client.execute {
//            index into "stock_data_instant" / "day" id s"${data.exchange}:${data.symbol}:${data.date}" fields (
//                    "symbol" -> data.symbol,
//                    "exchange" -> data.exchange,
//                    "date" -> data.date,
//                    "open" -> data.open,
//                    "close" -> data.close,
//                    "low" -> data.low,
//                    "high" -> data.high,
//                    "volume" -> data.volume
//            )
//        } await
//    }
//}

package stocker.store

import com.sksamuel.elastic4s.{ElasticClient, ElasticsearchClientUri}
import stocker.StockerConfig

/**
  * Created by marcin on 9/26/16.
  */
object ES {
    val client = {
        val host = StockerConfig.storage.getString("ES.host")
        val port = StockerConfig.storage.getString("ES.port")
        val uri = ElasticsearchClientUri(s"elasticsearch://$host:$port")
        ElasticClient.remote(uri)
    }
}

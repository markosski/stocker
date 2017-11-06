package stocker.store.alpha

/**
  * Created by marcin1 on 9/1/17.
  */
import com.typesafe.config.{Config, ConfigFactory}

protected object AlphaConfig {
    val conf = ConfigFactory.load()
    val apiKey = conf.getConfig("storage.alpha.key")
    val url = s"http://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&apikey=${apiKey}&interval=1min&outputsize=compact&symbol="
}

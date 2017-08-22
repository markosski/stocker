package stocker

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Created by marcin on 9/27/16.
  */
object StockerConfig {
    val conf = ConfigFactory.load()
    val storage = conf.getConfig("storage")
}

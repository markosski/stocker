package stocker.portfolio

import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

/**
  * Created by marcin1 on 3/28/17.
  */
trait DbConfiguration {
    lazy val config: DatabaseConfig[JdbcProfile] = DatabaseConfig.forConfig[JdbcProfile]("db")
}

trait Db {
    val config: DatabaseConfig[JdbcProfile]
    lazy val db: JdbcProfile#Backend#Database = config.db
}

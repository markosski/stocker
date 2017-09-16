# Stocker

## Features
- create sophisticated triggers
- basic charting
- function fitting


## TODO:
- specify which fields to analyze by ES
- stores should be able to remove stock from list and day data
- stores should be able to iterate over entire result set in batches
- stores read methods should return iterators

## Jobs
- we need to store job records. Information we need should be name,
    input data and output data

## Resources
https://www.google.com/finance/historical?cid=12607212&startdate=Jan+1%2C+2010&enddate=Sep+25%2C+2016&num=30&ei=1NrnV5nTNceae-2tuugC
https://www.google.com/finance/historical?q=TSLA&startdate=Jan+1%2C+2010&enddate=Sep+25%2C+2016
http://www.quantshare.com/sa-43-10-ways-to-download-historical-stock-quotes-data-for-free

https://www.alphavantage.co/#page-top

# ES mappings

import com.sksamuel.elastic4s.ElasticDsl._
import com.sksamuel.elastic4s.mappings.FieldType._
import stocker.store.ES

ES.client.execute {
    create index "stock_data" shards 3 replicas 0 mappings (
        "day" as (
            "symbol" typed StringType index NotAnalyzed omitNorms true,
            "exchange" typed StringType index NotAnalyzed omitNorms true,
            "date" typed DateType,
            "open" typed DoubleType,
            "close" typed DoubleType,
            "low" typed DoubleType,
            "high" typed DoubleType,
            "volume" typed StringType
        )
    )
} await

ES.client.execute {
    create index "stock_data_instant" shards 3 replicas 0 mappings (
        "instant" as (
            "symbol" typed StringType index NotAnalyzed omitNorms true,
            "exchange" typed StringType index NotAnalyzed omitNorms true,
            "dateTime" typed DateType,
            "value" typed DoubleType
        )
    )
} await

ES.client.execute {
    create index "stocks" shards 3 replicas 0 mappings (
        "stock" as (
            "symbol" typed StringType index NotAnalyzed omitNorms true,
            "companyName" typed StringType,
            "exchange" typed StringType index NotAnalyzed omitNorms true,
            "sector" typed StringType,
            "industry" typed StringType,
            "lastChecked" typed DateType,
            "active" typed BooleanType
        )
    )
} await

# Queries
ES.client.execute {
    search in "stock_data" / "day" start 0 limit 5
} await

ES.client.execute {
    search in "stock_data" / "day" query {
        bool {
            must (
                termQuery("symbol", "ATEN"),
                termQuery("exchange", "NYSE")
            )
        }
    }
} await

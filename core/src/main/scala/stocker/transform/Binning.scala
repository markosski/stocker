package stocker.transform

import stocker.model.StockDay

/**
  * Created by marcin on 10/4/16.
  */
object Binning {
    def apply(num: Int, data: Seq[StockDay]) = {
        require(data.size >= num, "data size must be greater than bin size")

        val bins = math.round(data.size / num.toDouble).toInt

        val result =
            for (i <- 0 until num) yield {
                val part = data.slice(i * bins, i * bins + bins)
                part.map(_.close).sum / part.size
            }

        val residual = data.drop(num * bins)

        if (residual.size > 0)
            result :+ residual.map(_.close).sum / residual.size
        else result
    }
}


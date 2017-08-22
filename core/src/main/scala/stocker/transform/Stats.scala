package stocker.transform

/**
  * Created by marcin on 10/24/16.
  */
object Stats {
    def stddev(data: Seq[Double]): Double = {
        math.sqrt(variance(data))
    }

    def variance(data: Seq[Double]): Double = {
        val mean: Double = data.sum / data.size

        (data map(x => math.pow(x - mean, 2)) sum) / data.size
    }

    def corrSqSum(d1: Seq[Double], d2: Seq[Double]) = {
        d1.zip(d2).map(x => x._1 - x._2).sum / d1.size
    }

    def corrPearson(d1: Seq[Double], d2: Seq[Double]) = {
        val d1mean = d1.sum / d1.size
        val d2mean = d2.sum / d2.size

        val zipped = d1.map(x => x - d1mean) zip d2.map(x => x - d2mean)

        zipped.map(x => x._1 * x._2).sum / math.sqrt(zipped.map(x => math.pow(x._1, 2)).sum * zipped.map(x => math.pow(x._2, 2)).sum)
    }
}

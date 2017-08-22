package stocker.transform

/**
  * Created by marcin on 10/4/16.
  */
object Histogram {
    def apply(nBins: Int, data: List[Double]): List[List[Double]] = {
        require(data.length > nBins)

        val epsilon = 0.000001
        val (max, min) = (data.max, data.min)
        val binWidth = (max - min) / nBins + epsilon
        val bounds = (1 to nBins).map { x => min + binWidth * x }.toList

        def histo(bounds: List[Double], data: List[Double]): List[List[Double]] =
            bounds match {
                case h :: Nil => List(data)
                case h :: t   => val (l, r) = data.partition( _ < h) ; l :: histo(t, r)
            }

        histo(bounds, data)
    }
}

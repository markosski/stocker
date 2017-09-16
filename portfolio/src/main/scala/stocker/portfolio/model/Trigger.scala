package stocker.portfolio.model

import java.time.LocalTime

/**
  * Created by marcin1 on 8/23/17.
  */
trait Condition
object Condition {
    case class PriceLowerBound(price: Double) extends Condition

    case class PriceUpperBound(price: Double) extends Condition

//    case class PriceRaisePct(pct: Double) extends Condition
//
//    case class PriceDropsPct(pct: Double) extends Condition
//
//    case class PriceRaiseMovingAVG(days: Int, pct: Double) extends Condition
//
//    case class PriceDropsMovingAVG(days: Int, pct: Double) extends Condition

    case class Report(time: LocalTime) extends Condition
}

trait Action
object Action {
    case object EmailNotification extends Action
}

case class Trigger(triggerId: String, stock: Stock, cond: Condition, action: Action)

case class TriggerUpdated(triggerId: String, timestamp: Long) extends Ordered[TriggerUpdated] {
    // Compare by timestamp ascending
    def compare(that: TriggerUpdated) =
        that.timestamp compare this.timestamp
}

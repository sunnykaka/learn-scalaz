trait CanTruthy[A] {
  def truthys(a: A): Boolean
}

object CanTruthy {

  def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A]() {
    def truthys(a: A): Boolean = f(a)
  }

  implicit lazy val CanTruthyInt: CanTruthy[Int] = CanTruthy.truthys {
    case 0 => false
    case _ => true
  }

}

trait CanTruthyOps[A] {

  def self: A
  implicit def F: CanTruthy[A]
  def truthy: Boolean = F.truthys(self)

}

object CanTruthyOps {
  implicit def toCanTruthyOps[A](v: A)(implicit ev: CanTruthy[A]): CanTruthyOps[A] =
    new CanTruthyOps[A]() {
      def self = v
      implicit def F: CanTruthy[A] = ev
    }
}


import CanTruthyOps._

10.truthy
0.truthy

//toCanTruthyOps(0)(CanTruthyInt).truthy





//trait CanTruthy[A] { self =>
//  /** @return true, if `a` is truthy. */
//  def truthys(a: A): Boolean
//}
//
//object CanTruthy {
//  def apply[A](implicit ev: CanTruthy[A]): CanTruthy[A] = ev
//  def truthys[A](f: A => Boolean): CanTruthy[A] = new CanTruthy[A] {
//    def truthys(a: A): Boolean = f(a)
//  }
//}
//
//trait CanTruthyOps[A] {
//  def self: A
//  implicit def F: CanTruthy[A]
//  final def truthy: Boolean = F.truthys(self)
//}
//
//object ToCanIsTruthyOps {
//  implicit def toCanIsTruthyOps[A](v: A)(implicit ev: CanTruthy[A]) =
//    new CanTruthyOps[A] {
//      def self = v
//      implicit def F: CanTruthy[A] = ev
//    }
//}
//
//implicit val intCanTruthy: CanTruthy[Int] = CanTruthy.truthys({
//  case 0 => false
//  case _ => true
//})
//
//import ToCanIsTruthyOps._
//
//10.truthy


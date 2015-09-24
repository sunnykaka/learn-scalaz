import scalaz._
import Scalaz._

(1, 2, 3) map {_ + 1}

val f1 = ((x: String) => x + "aa") map {_ * 3}

f1("c")

val f2 = ((x: String) => x + "aa") andThen {_ * 3}

f2("c")

List(1, 2, 3) >| "x"

List(1, 2, 3).fpair

List(1, 2, 3).strengthL("x")

val f3 = List(1, 2, 3, 4) map {(_: Int) * (_:Int)}.curried

f3 map (_(9))

1.point[List]

9.some <*> {(_: Int) + 3}.some

9.some map {_ + 3}
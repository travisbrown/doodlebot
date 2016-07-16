package doodlebot
package validation

import cats.Monoid
import cats.data.NonEmptyList
import cats.std.list._
import cats.std.map._
import cats.syntax.semigroup._

final case class InputError(messages: Map[String,NonEmptyList[String]])
object InputError {

  def apply(name: String, messages: NonEmptyList[String]): InputError =
    InputError(Map(name -> messages))

  implicit object inputErrorInstances extends Monoid[InputError] {
    override def combine(a1: InputError, a2: InputError): InputError =
      InputError(a1.messages |+| a2.messages)

    override def empty: InputError =
      InputError(Map.empty)
  }
}

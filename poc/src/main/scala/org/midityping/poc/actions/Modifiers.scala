package org.midityping.poc.actions

case class Modifiers(list: Set[Modifier] = Set.empty)

object Modifiers {
  def None: Modifiers = Modifiers()

  def Shift: Modifiers = Modifiers(Set(ShiftModifier))
}

package org.midityping.poc.common

case class Note(name: String, octave: Int, number: Int) {
  def fullName: String = name + octave
}

object Note {
  private val notes = "C C#D D#E F F#G G#A A#B "

  def apply(noteNumber: Int): Note = {
    val octave = noteNumber / 12 - 1
    val noteIndex: Int = (noteNumber % 12) * 2
    val noteName = notes.substring(noteIndex, noteIndex + 2).trim
    Note(noteName, octave, noteNumber)
  }

  def C2 = Note(36)
  def D2 = Note(38)
  def C4 = Note(60)
  def D4 = Note(62)
  def Ds4 = Note(63)
  def E4 = Note(64)
  def F4 = Note(65)
  def Fs4 = Note(66)
  def G4 = Note(67)
  def Gs4 = Note(68)
  def A4 = Note(69)
}

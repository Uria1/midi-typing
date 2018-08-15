package org.midityping.poc.common

case class Note(name: String, octave: Int, number: Int)

object Note {
  private val notes = "C C#D D#E F F#G G#A A#B "

  def apply(noteNumber: Int): Note = {
    val octave = noteNumber / 12 - 1
    val noteIndex: Int = (noteNumber % 12) * 2
    val noteName = notes.substring(noteIndex, noteIndex + 2).trim
    Note(noteName + octave, octave, noteNumber)
  }

  def C4 = Note(60)
}

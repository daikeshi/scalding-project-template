package com.example.scalding.avro

import com.twitter.scalding._
import com.twitter.scalding.avro.UnpackedAvroSource

class WordCountJob(args : Args) extends Job(args) {
  TextLine( args.required("input") )
    .flatMap('line → 'word) { line : String ⇒ tokenize(line) }
    .groupBy('word) { _.size }
    .write(UnpackedAvroSource( args.required("output")))

  def tokenize(text : String) : Array[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+").filter(!_.isEmpty)
  }
}

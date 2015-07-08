package com.example.scalding.avro

import com.twitter.scalding._
import com.twitter.scalding.avro.UnpackedAvroSource
import org.apache.avro.Schema

object WordCount {
  val schema = """{
    "type": "record", "name": "WordCount", "fields": [
    { "name": "word", "type": "string" },
    { "name": "count", "type": "long" }
  ] }"""
}

class WordCountAvroJob(args : Args) extends Job(args) {
  TextLine( args.required("input") )
    .flatMap('line → 'word) { line : String ⇒ tokenize(line) }
    .groupBy('word) { _.size('count) }
    .write(UnpackedAvroSource(args.required("output"), new Schema.Parser().parse(WordCount.schema)))

  def tokenize(text : String) : Array[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+").filter(!_.isEmpty)
  }
}

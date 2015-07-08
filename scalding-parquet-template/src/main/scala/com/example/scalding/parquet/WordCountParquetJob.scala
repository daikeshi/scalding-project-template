package com.example.scalding.parquet

import cascading.tuple.Fields
import com.twitter.scalding._

class WordCountParquetJob(args : Args) extends Job(args) {
  TextLine( args.required("input") )
    .flatMap('line → 'word) { line : String ⇒ tokenize(line) }
    .groupBy('word) { _.size('count) }
    .write(WordCountParquet(Fields.ALL, args.required("output")))

  def tokenize(text : String) : Array[String] = {
    text.toLowerCase.replaceAll("[^a-zA-Z0-9\\s]", "").split("\\s+").filter(!_.isEmpty)
  }
}

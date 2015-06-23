package com.example.scalding

import org.specs2._
import com.twitter.scalding._

class WordCountTest extends mutable.Specification {
  "A WordCount job" should {
    JobTest("com.example.scalding.WordCountJob")
      .arg("input", "inputFile")
      .arg("output", "outputFile")
      .source(TextLine("inputFile"), List("0" → "hack hack hack and hack"))
      .sink[(String,Int)](Tsv("outputFile")){ outputBuffer ⇒
        val outMap = outputBuffer.toMap
        "count words correctly" in {
          outMap("hack") === 4
          outMap("and") === 1
        }
      }.run.finish
  }
}

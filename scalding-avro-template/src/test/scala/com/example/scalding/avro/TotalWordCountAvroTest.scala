package com.example.scalding.avro

import com.twitter.scalding.avro.UnpackedAvroSource
import com.twitter.scalding.{JobTest, Tsv}
import org.specs2.mutable

class TotalWordCountAvroTest extends mutable.Specification {
  "A WordCount job" should {
    JobTest("com.example.scalding.avro.TotalWordCountAvroJob")
      .arg("input", "inputFile")
      .arg("output", "outputFile")
      .source(UnpackedAvroSource("inputFile"), List("foo" → 1L, "bar" → 2L))
      .sink[Long](Tsv("outputFile")){ outputBuffer ⇒
      "sum should be correctly" in {
        outputBuffer(0) === 3
      }
    }.run.finish
  }
}

package com.example.scalding.parquet

import com.twitter.scalding.Dsl._
import com.twitter.scalding._
import org.specs2.mutable

class TotalWordCountParquetTest extends mutable.Specification {
  "A WordCount job" should {
    JobTest("com.example.scalding.parquet.TotalWordCountParquetJob")
      .arg("input", "inputFile")
      .arg("output", "outputFile")
      .source(ParquetTupleSource('count, "inputFile"), List(1L, 2L))
      .sink[Long](Tsv("outputFile")) { outputBuffer ⇒
         "sum should be correctly" in {
          outputBuffer(0) === 3
        }
      }
      .run.finish
  }
}

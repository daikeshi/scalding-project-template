package com.example.scalding.avro

import com.twitter.scalding._
import com.twitter.scalding.avro.UnpackedAvroSource
import org.apache.avro.Schema
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.util.ToolRunner
import org.specs2._

class WordCountAvroTest extends mutable.Specification {
  "A WordCount job" should {
    JobTest("com.example.scalding.avro.WordCountAvroJob")
      .arg("input", "inputFile")
      .arg("output", "outputFile")
      .source(TextLine("inputFile"), List("0" → "hack hack hack and hack"))
      .sink[(String,Int)](UnpackedAvroSource("outputFile", new Schema.Parser().parse(WordCount.schema))){ outputBuffer ⇒
        val outMap = outputBuffer.toMap
        "count words correctly" in {
          outMap("hack") === 4
          outMap("and") === 1
        }
      }.run.finish
  }

  "A WordCount job" should {
    "run locally" in {
      val args = Array(
        "com.example.scalding.avro.WordCountAvroJob",
        "--hdfs",
        "--input", "data/alice.txt",
        "--output", "output/avro",
        "--trap-dir", "trap/avro",
        "--tool.partialok"
      )

      ToolRunner.run(new JobConf, new Tool, args) === 0
    }
  }
}

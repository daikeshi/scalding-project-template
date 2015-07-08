package com.example.scalding.core

import com.twitter.scalding._
import org.apache.hadoop.mapred.JobConf
import org.apache.hadoop.util.ToolRunner
import org.specs2._

class WordCountTest extends mutable.Specification {
  "A WordCount job" should {
    JobTest("com.example.scalding.core.WordCountJob")
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

  "A WordCount job" should {
    "run locally" in {
      val args = Array(
        "com.example.scalding.core.WordCountJob",
        "--hdfs",
        "--input", "data/alice.txt",
        "--output", "output/core",
        "--trap-dir", "trap/core",
        "--tool.partialok"
      )

      ToolRunner.run(new JobConf, new Tool, args) === 0
    }
  }
}

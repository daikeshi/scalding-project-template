package com.example.scalding.avro

import com.twitter.scalding.{Args, Job, _}
import com.twitter.scalding.avro.UnpackedAvroSource
import org.apache.avro.Schema

class TotalWordCountAvroJob(args : Args) extends Job(args) {
  UnpackedAvroSource(args.required("input"), new Schema.Parser().parse(WordCount.schema))
    .project('count)
    .groupAll { _.sum[Long]('count → 'total) }
    .write(Tsv(args.required("output")))
}

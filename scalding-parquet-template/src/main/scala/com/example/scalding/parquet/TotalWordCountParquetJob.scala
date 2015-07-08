package com.example.scalding.parquet

import com.twitter.scalding.{Job, Args}
import com.twitter.scalding._

class TotalWordCountParquetJob(args : Args) extends Job(args) {
  ParquetTupleSource('count, args("input"))
    .groupAll { _.sum[Long]('count → 'total) }
    .write(Tsv(args.required("output")))
}

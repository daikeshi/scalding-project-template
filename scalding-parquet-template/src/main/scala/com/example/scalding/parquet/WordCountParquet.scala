package com.example.scalding.parquet

import cascading.scheme.Scheme
import cascading.tuple.Fields
import com.twitter.scalding.{FileSource, FixedPathSource, HadoopSchemeInstance}
import parquet.cascading.ParquetTupleScheme

trait ParquetTupleSink extends FileSource {
  def fields: Fields

  def schema: String

  override def hdfsScheme = {
    val parquetTupleSchema = new ParquetTupleScheme(Fields.ALL, fields, schema)
    HadoopSchemeInstance(parquetTupleSchema.asInstanceOf[Scheme[_, _, _, _, _]])
  }
}

object ParquetTupleSource {
  def apply(fields: Fields, paths: String*) = new FixedPathParquetTuple(fields, paths: _*)
}

trait ParquetTupleSource extends FileSource {
  def fields: Fields

  override def hdfsScheme = {
    val scheme = new ParquetTupleScheme(fields)
    HadoopSchemeInstance(scheme.asInstanceOf[Scheme[_, _, _, _, _]])
  }
}

class FixedPathParquetTuple(override val fields: Fields, paths: String*) extends FixedPathSource(paths: _*) with ParquetTupleSource

case class WordCountParquet(override val fields: Fields, paths: String*) extends FixedPathSource(paths: _*) with ParquetTupleSink {
  override def schema: String =
    """
      |message WordCount {
      |    required binary word (UTF8);
      |    required int64 count;
      |}
    """.stripMargin
}

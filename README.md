A Scalding Job Template
=======
How to run:
```shell
sbt test
sbt assembly
# scalding field api
hadoop jar assembly-jars/basic-0.0.1-SNAPSHOT-assembly.jar com.example.scalding.core.WordCountJob --hdfs --input data/alice.txt --output target/output
# write in avro
hadoop jar assembly-jars/avro-0.0.1-SNAPSHOT-assembly.jar com.example.scalding.avro.WordCountAvroJob --hdfs --input data/alice.txt --output target/output
# write in parquet tuple
hadoop jar assembly-jars/parquet-0.0.1-SNAPSHOT-assembly.jar com.example.scalding.parquet.WordCountParquetJob --hdfs --input data/alice.txt --output target/output
# read from avro
hadoop jar assembly-jars/avro-0.0.1-SNAPSHOT-assembly.jar com.example.scalding.avro.TotalWordCountAvroJob --hdfs --input data/avro --output target/output
# read from parquet tuple
hadoop jar assembly-jars/parquet-0.0.1-SNAPSHOT-assembly.jar com.example.scalding.parquet.TotalWordCountParquetJob --hdfs --input data/parquet --output target/output
```

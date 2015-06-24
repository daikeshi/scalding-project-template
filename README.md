A Scalding Job Template
=======
How to run:
```shell
sbt test
sbt assembly
hadoop jar target/scalding-project-template-0.0.1-SNAPSHOT-assembly.jar com.example.scalding.WordCountJob --hdfs --input data/alice.txt --output target/output
```

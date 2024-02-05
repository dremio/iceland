# Iceland, data playground

Iceland is a data playground project, involving Apache Iceberg and other OSS projects in the domain.

It contains several components used to easily run, test, benchmark with different flavors (different catalogs, different engines, ...).

The objectives are:

1. Provide resources (docker images, helm charts, notebooks, ...) to easily start with Apache Iceberg using different flavors
2. Easily run use cases with concrete datasets, representing concrete usage
3. Compare the performance on the use cases with the different flavors

The components are:
* `iceberg/datasets` contains scripts to retrieve ready to use data.
* `iceberg/usecases` contains samples and examples using the datasets.
* `iceberg/benchmark` contains use cases benchmark
* `icekube` contains Docker images, HELM charts, ... Basically everything needed to start with Apache Iceberg

## Datasets

### GDELT

GDELT Project stores all new articles as "events": http://data.gdeltproject.org/events/index.html

Daily, a zip file is created, containing a CSV file with all events using the following format:

```
545037848       20150530        201505  2015    2015.4110                                                                                       JPN     TOKYO   JPN                                                             1       046     046     04      1       7.0     15      1       15      -1.06163552535792       0                                                       4       Tokyo, Tokyo, Japan     JA      JA40    35.685  139.751 -246227 4       Tokyo, Tokyo, Japan     JA      JA40    35.685  139.751 -246227 20160529        http://deadline.com/print-article/1201764227/
```

The format is described here: http://data.gdeltproject.org/documentation/GDELT-Data_Format_Codebook.pdf

The `iceberg/datasets/gdelt/fetch.sh` script download the GDELT file (eventually using a given date):

```
./iceberg/datasets/gdelt/fetch.sh 20240114
```

The script downloads and extracts the GDELT ready to be used.

## Use cases

### Data Ingestion to Iceberg using Spark

`iceberg/usecases/injection` contains `CreateTable` class. This class creates an Iceberg table to store the GDELT events.

You first have to execute `CreateTable` to create the table used by other use cases.

You can build a uber jar using:

```
mvn clean install
```

You now have the `iceberg/usecases/injection/target/injection-1.0-SNAPSHOT.jar` uber jar.

You can run `CreateTable` using:

```
java -cp iceberg/usecases/injection/target/injection-1.0-SNAPSHOT.jar com.dremio.iceland.iceberg.injection.CreateTable
```

You now have the `iceland.gdelt.events` Iceberg table created. You can use `DataInjection`, which parses the GDEL CSV file and insert events in the Iceberg table.

You can run `DataInjection` using:

```
java -cp iceberg/usecases/injection/target/injection-1.0-SNAPSHOT.jar com.dremio.iceland.iceberg.injection.DataInjection
```

## First simple analytic queries

The `injection` module also provide the first simple analytic queries. You can use `Analytics` that performs the following queries:

* count the number of events in the table (`SELECT COUNT(*) FROM iceland.gdelt.events`)
* the 10 first events by number of articles (`SELECT * FROM iceland.gdelt.events ORDER by NumArticles DESC LIMIT 10`)
* the 10 first events by number of mentions (`SELECT * FROM iceland.gdelt.events ORDER by NumMentions DESC LIMIT 10`)
* the 10 first events by average tone (`SELECT * FROM iceland.gdelt.events ORDER by AvgTone DESC LIMIT 10`)
* the number of events located in the US (`SELECT count(*) FROM iceland.gdelt.events WHERE Country = "US"`)

You can run `Analytics` with:

```
java -cp iceberg/usecases/injection/target/injection-1.0-SNAPSHOT.jar com.dremio.iceland.iceberg.injection.Analytics
```


### Flavored Docker Images

### Notebook

### Helm Charts

# Iceland, Apache Iceberg playground

Iceland is a playground project for Apache Iceberg.

It contains several components used to easily run, test, benchmark Apache Iceberg, with different flavors (different catalogs, different engines, ...).

The objectives are:

1. Provide resources (docker images, helm charts, notebooks, ...) to easily start with Apache Iceberg using different flavors
2. Easily run use cases with concrete datasets, representing concrete usage
3. Compare the performance on the use cases with the different flavors

The components are:
* `datasets` contains concrete data used in the samples and tests
* `usecases` contains samples and examples using the datasets
* `benchmark` contains use cases benchmark
* `icekube` contains Docker images, HELM charts, ... Basically everything needed to start with Apache Iceberg

## Datasets

### GDELT

GDELT Project stores all new articles as "events": http://data.gdeltproject.org/events/index.html

Daily, a zip file is created, containing a CSV file with all events using the following format:

```
545037848       20150530        201505  2015    2015.4110                                                                                       JPN     TOKYO   JPN                                                             1       046     046     04      1       7.0     15      1       15      -1.06163552535792       0                                                       4       Tokyo, Tokyo, Japan     JA      JA40    35.685  139.751 -246227 4       Tokyo, Tokyo, Japan     JA      JA40    35.685  139.751 -246227 20160529        http://deadline.com/print-article/1201764227/
```

The format is described here: http://data.gdeltproject.org/documentation/GDELT-Data_Format_Codebook.pdf

### TPCDS

## Use cases

### Data Ingestion to Iceberg using Spark

* `gdelt/spark/di`

### Q1: Events By Location

* `gdelt/spark/q1`

This query extracts all events for a specific location, using Spark engine.

## Performance Benchmark

## Icekube

* `icekube`

### Flavored Docker Images

### Notebook

### Helm Charts

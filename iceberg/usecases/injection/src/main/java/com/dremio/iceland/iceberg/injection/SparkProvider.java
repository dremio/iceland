package com.dremio.iceland.iceberg.injection;

import org.apache.iceberg.spark.SparkCatalog;
import org.apache.iceberg.spark.SparkSessionCatalog;
import org.apache.iceberg.spark.extensions.IcebergSparkSessionExtensions;
import org.apache.spark.sql.SparkSession;

public class SparkProvider {

    public static SparkSession get() {
        return SparkSession.builder()
                .master("local[2]")
                .appName("simple")
                .config("spark.sql.extensions", IcebergSparkSessionExtensions.class.getName())
                .config("spark.sql.catalog.spark_catalog", SparkSessionCatalog.class.getName())
                .config("spark.sql.catalog.spark_catalog.type", "hive")
                .config("spark.sql.catalog.local", SparkCatalog.class.getName())
                .config("spark.sql.catalog.local.type", "hadoop")
                .config("spark.sql.catalog.local.warehouse", ".")
                .config("spark.sql.defaultCatalog", "local")
                .getOrCreate();
    }

}

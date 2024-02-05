package com.dremio.iceland.iceberg.injection;

import org.apache.spark.sql.SparkSession;

/**
 * Create the GDELT iceberg table, ready to receive events/news.
 */
public class CreateTable {

    public static final void main(String... args) throws Exception {
        try (SparkSession spark = SparkProvider.get()) {
            spark.sql("CREATE TABLE iceland.gdelt.events(" +
                    "EventID bigint," +
                    "Date int," +
                    "NumMentions int," +
                    "NumSources int," +
                    "NumArticles int," +
                    "AvgTone double," +
                    "Location string," +
                    "Country string," +
                    "Latitude double," +
                    "Longitude double," +
                    "Source string" +
                    ") USING iceberg " +
                    "PARTITIONED BY (Date)");
        }
    }

}

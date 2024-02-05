package com.dremio.iceland.iceberg.injection;

import org.apache.spark.sql.SparkSession;

public class Analytics {

    public static void main(String... args) throws Exception {
        try (SparkSession spark = SparkProvider.get()) {
            System.out.println("Number of Events");
            spark.sql("SELECT COUNT(*) FROM iceland.gdelt.events").show();

            System.out.println("Top 10: Events ordered by number of articles");
            spark.sql("SELECT * FROM iceland.gdelt.events ORDER by NumArticles DESC LIMIT 10").show();
            System.out.println("Top 10: Events ordered by number of mentions");
            spark.sql("SELECT * FROM iceland.gdelt.events ORDER by NumMentions DESC LIMIT 10").show();
            System.out.println("Top 10: Events ordered by average tone");
            spark.sql("SELECT * FROM iceland.gdelt.events ORDER by AvgTone DESC LIMIT 10").show();

            System.out.println("Number of events in the US");
            spark.sql("SELECT count(*) FROM iceland.gdelt.events WHERE Country = \"US\"").show();
        }
    }

}

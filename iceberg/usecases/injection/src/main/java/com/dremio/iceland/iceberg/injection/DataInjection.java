package com.dremio.iceland.iceberg.injection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.spark.sql.SparkSession;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class DataInjection {

    public static void main(String... args) throws Exception {

        String location = "gdelt.csv";

        if (args.length == 2) {
            location = args[1];
        }

        Path file = Paths.get(location);
        if (!Files.exists(file)) {
            System.err.println(file.toAbsolutePath() + " doesn't exist");
            System.exit(1);
        }

        try (SparkSession spark = SparkProvider.get()) {
            List<List<String>> lines = parse(file.toFile());

            for (List<String> line : lines) {
                StringBuilder builder = new StringBuilder();
                builder.append("INSERT INTO iceland.gdelt.events VALUES(");

                // GLOBALEVENTID
                builder.append(line.get(0)).append(",");
                // SQLDATE
                builder.append(line.get(1)).append(",");

                // NumMentions
                builder.append(line.get(31)).append(",");
                // NumSources
                builder.append(line.get(32)).append(",");
                // NumArticles
                builder.append(line.get(33)).append(",");
                // AvgTone
                builder.append(line.get(34)).append(",");

                // ActionGeo_FullName
                builder.append("\"").append(line.get(50)).append("\"").append(",");
                // ActionGeo_CountryCode
                builder.append("\"").append(line.get(51)).append("\"").append(",");

                // ActionGeo_Lat
                builder.append(line.get(53).isEmpty() ? "null" : line.get(53)).append(",");
                // ActionGeo_Long
                builder.append(line.get(54).isEmpty() ? "null" : line.get(54)).append(",");

                // SOURCEURL
                builder.append("\"").append(line.get(57)).append("\"");

                builder.append(")");

                System.out.println(builder);

                spark.sql(builder.toString());
            }

        }
    }

    private static List<List<String>> parse(File file) throws IOException {
        List<List<String>> result = new ArrayList<>();
        try (CSVParser parser = CSVParser.parse(file, StandardCharsets.UTF_8, CSVFormat.TDF)) {
            for (CSVRecord record : parser) {
                result.add(record.toList());
            }
        }
        return result;
    }


}

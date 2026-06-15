package com.sid.tutorials.java.apache.spark.app;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        List<String> inputData = new ArrayList<>();
        inputData.add("WARN: Tuesday 4 September 0405");
        inputData.add("ERROR: Tuesday 4 September 0408");
        inputData.add("FATAL: Wednesday 5 September 1632");
        inputData.add("ERROR: Friday 7 September 1854");
        inputData.add("WARN: Saturday 8 September 1942");

        SparkConf conf = new SparkConf().setAppName("MyApp").setMaster("local[*]");
        JavaSparkContext sc = sc = new JavaSparkContext(conf);
        JavaRDD<String> inputDataRdd = sc.parallelize(inputData);
        JavaPairRDD<String, String> pairRDDKeyValue = inputDataRdd.mapToPair((String inputdata) -> {
            String[] parts = inputdata.split(":");
            return new Tuple2<>(parts[0], parts[1]);
        });
        pairRDDKeyValue.foreach((Tuple2<String, String> pair) -> {
            System.out.println("Key : " + pair._1 + " value : " + pair._2 + " ");
        });
        pairRDDKeyValue.groupByKey().foreach((Tuple2<String, Iterable<String>> pair) -> {
            System.out.println("Key : " + pair._1 + " value : " + pair._2 + " ");
        });
        JavaPairRDD<String, Integer> keyValueIntPairRDD = inputDataRdd.mapToPair((String inputdata) -> {
            String[] parts = inputdata.split(":");
            return new Tuple2<>(parts[0], 1);
        });
        JavaPairRDD<String, Integer> keyValueIntPairRDDSize = keyValueIntPairRDD.reduceByKey((Integer value1, Integer value2) -> {
            return value1 + value2;
        });
        keyValueIntPairRDDSize.foreach((Tuple2<String, Integer> pair) -> {
            System.out.println("Key : " + pair._1 + " value : " + pair._2 + " ");
        });
        sc.close();
    }
}

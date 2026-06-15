package com.sid.tutorials.java.apache.spark.app;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        List<Integer> inputData = new ArrayList<>();
        inputData.add(1);
        inputData.add(2);
        inputData.add(3);
        inputData.add(4);
        inputData.add(5);
        inputData.add(6);
        inputData.add(7);
        inputData.add(8);
        inputData.add(9);
        inputData.add(10);

        SparkConf conf = new SparkConf().setAppName("MyApp").setMaster("local[*]");
        JavaSparkContext sc = sc = new JavaSparkContext(conf);
        JavaRDD<Integer> inputDataRdd = sc.parallelize(inputData);
        Integer reduce = inputDataRdd.reduce((Integer inputdata1, Integer inputdata2) -> inputdata1 + inputdata2);
        System.out.println("Reduce result: " + reduce);
        JavaRDD<Integer> squaredRdd = inputDataRdd.map((Integer x) -> x * x);
        squaredRdd.foreach((Integer x) -> System.out.println("Squared value: " + x));
        sc.close();
    }
}

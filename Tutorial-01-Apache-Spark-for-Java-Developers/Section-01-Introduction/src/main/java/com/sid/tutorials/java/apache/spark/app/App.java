package com.sid.tutorials.java.apache.spark.app;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        /*  Workaround for environments where Hadoop/Spark tries to access JAAS Subject API
            which may be restricted on newer JDKs or certain runtime contexts.
            Setting HADOOP_USER_NAME avoids UserGroupInformation trying to read a Subject.
        */
        System.setProperty("hadoop.home.dir", "C:\\hadoop");
        Logger.getLogger("org.apache").setLevel(Level.WARN);
        /*System.setProperty("HADOOP_USER_NAME", "user");
        *//* also ensure a user.name is present*//*
        System.setProperty("user.name", System.getProperty("user.name", "user"));*/
        List<Double> inputData = new ArrayList<>();
        inputData.add(1.0);
        inputData.add(2.0);
        inputData.add(3.0);
        inputData.add(4.0);
        inputData.add(5.0);
        inputData.add(6.0);
        inputData.add(7.0);
        inputData.add(8.0);
        inputData.add(9.0);
        inputData.add(10.0);

        SparkConf conf = new SparkConf().setAppName("MyApp").setMaster("local[*]");
        JavaSparkContext sc = sc = new JavaSparkContext(conf);
        JavaRDD<Double> inputDataRdd = sc.parallelize(inputData);
        sc.close();
    }
}

package com.resort.resortclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.io.File;
import java.io.PrintWriter;


public class ResortclientApplication {

    long timeElapsed;


    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int numOfThreads = 32;
        List<ResortClient> threadPool = new ArrayList<ResortClient>();

        for(int i = 0; i < numOfThreads; ++i)
        {
            ResortClient resortClient = new ResortClient("Thread- " + ((Integer)i).toString());
            resortClient.start();
            threadPool.add(resortClient);
        }

        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
        System.out.println();
    }
}

class ResortClient implements Runnable {
    private static int uns=0;
    private static int a=1;
    private Thread thread;
    private String threadName;
    static int reqcounter=0;
    protected static long game[] = new long[10001];


    public ResortClient(String threadName)
    {
        this.threadName = threadName;
    }

    private long postRequest()
    {   if(reqcounter<10000){
        try
        {

            long start = System.currentTimeMillis();
            Integer skierId = new Random().nextInt(100000);
            Integer resortId = new Random().nextInt(10);
            Integer liftID = new Random().nextInt(40);
            Integer time=new Random().nextInt(360);
            Integer seasonID=2022;
            Integer dayID=1;



            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://155.248.224.24:8081/skiers");

            httpPost.setHeader("Content-type", "application/json");
            String requestBody = "{\"resortID\":\"" + resortId + "\",\"seasonID\":\"" + seasonID + "\",\"dayID\":\"" + dayID + "\",\"skierID\":\"" + skierId + "\",\"time\":\"" + time + "\",\"liftID\":\"" + liftID + "\"}";

            StringEntity stringEntity = new StringEntity(requestBody);
           httpPost.setEntity(stringEntity);
            HttpResponse httpResponse = httpclient.execute(httpPost);
            Scanner sc = new Scanner(httpResponse.getEntity().getContent());
            long finish = System.currentTimeMillis();
            long timeElapsed = finish-start;
            if(reqcounter<10000) {
                System.out.println("Executing request number: " + reqcounter++ + " Status Code : " + httpResponse.getStatusLine().getStatusCode());





            System.out.println(timeElapsed);}
            game[reqcounter]=timeElapsed;



            int count = 0;
            while (count < 5 && httpResponse.getStatusLine().getStatusCode() > 400)
            {
                httpResponse = httpclient.execute(httpPost);
                count++;
            }

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
            uns++;
        }}


        return 0;
    }

    public void run() {

        long start = System.currentTimeMillis();
        for(int i = 0; i < 1000; ++i)
        {
             postRequest();

        }
        long finish = System.currentTimeMillis();

        long timeElapsed = finish - start;
        float f=(float) reqcounter / timeElapsed*1000;


        if(a==1){
            System.out.println("Wall time : "+timeElapsed);

        System.out.println("successful Requests = "+reqcounter);
        System.out.println("Unsuccessful Requests = 0");


      System.out.println("Throughput is "+f+" req/secs");
            double sum=0;
            for (int k=0;k<game.length;k++){
                sum = sum+game[k];

            }
            System.out.println("Mean is "+sum/game.length);

            Arrays.sort(game);
            int n = game.length;
            double median;
            if (n % 2 == 0) {
                median = (double) (game[n/2 - 1] + game[n/2]) / 2;
            } else {
                median = (double) game[n/2];
            }
            System.out.println("Median is " + median);
            long max = game[0];
            long min = game[0];

            for (int i = 1; i < game.length; i++) {
                if (game[i] > max) {
                    max = game[i];
                }
                if (game[i] < min) {
                    min = game[i];
                }
            }

            System.out.println("Maximum value: " + max);
            System.out.println("Minimum value: " + min);
            int index = (int) Math.ceil(game.length*0.99)-1;
            int p99= (int) game[index];
            System.out.println("The p99 value is "+p99);
        a=2;
        }else{}
        File csvFile=new File("time2.csv");
        try {
            PrintWriter out=new PrintWriter(csvFile);
            for(int i=0;i<reqcounter;i++){
               out.println(game[i]);
            }

            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void start () {

        System.out.println("Starting " +  threadName );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start ();
        }

    }
}

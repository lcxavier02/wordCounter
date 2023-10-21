/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.xav.wordscounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Xavi
 */
public class WordsCounterConcurrent {
    public static void main(String[] args) {
        String fileName = "C:/Users/Xavi/Documents/NetBeansProjects/WordsCounter/src/main/java/documents/1000000w.txt";
        long startTime = System.currentTimeMillis();
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        AtomicInteger wordCount = new AtomicInteger(0);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                final String finalLine = line;
                executorService.execute(() -> {
                    String[] words = finalLine.split("\\s+");
                    wordCount.addAndGet(words.length);
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Conteo de palabras concurrente: " + wordCount);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
    }
}

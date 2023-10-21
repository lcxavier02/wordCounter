/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.xav.wordscounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Xavi
 */
public class WordsCounter {

    public static void main(String[] args) {
        String fileName = "C:/Users/Xavi/Documents/NetBeansProjects/WordsCounter/src/main/java/documents/1000000w.txt";
        long startTime = System.currentTimeMillis();
        int wordCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                wordCount += words.length;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Conteo de palabras secuencial: " + wordCount);
        System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
    }
}

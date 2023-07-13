package com.example.luckynumber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class retrieveFileText {

    public static String readLine(String filename, int lineNum) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNum) {
                    return line;
                }
                currentLine++;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String lineDay(int lineNum)
    {
        String results_Day_Report = "/Users/sachintanwar/Documents/LuckyNumber/src/main/resources/com/example/luckynumber/Results_Day_Report";
        String lineDay = readLine(results_Day_Report, lineNum);
        return lineDay;
    }
    public static String lineNumber(int lineNum)
    {
        String results_Number_Report = "/Users/sachintanwar/Documents/LuckyNumber/src/main/resources/com/example/luckynumber/Results_Number_Report";
        String lineNumber = readLine(results_Number_Report, lineNum);
        return lineNumber;
    }
    public static String lineName(int lineNum)
    {
        String results_Name_Report = "/Users/sachintanwar/Documents/LuckyNumber/src/main/resources/com/example/luckynumber/Results_Name_Report";
        String lineName = readLine(results_Name_Report, lineNum);
        return lineName;
    }
}

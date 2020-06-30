package gamesKraft;

import gamesKraft.impl.ProbabilityGeneratorImpl;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) throws IOException {

        System.out.println("enter the path of csv file containing strings and probabilities");
        Scanner scannerNormal = new Scanner(System.in);
        String path = scannerNormal.nextLine();

        System.out.println("reading CSV file...");

        Scanner scanCSV = new Scanner(new File(path));
        scanCSV.useDelimiter(",");

        Map<String,Double> map = new LinkedHashMap<>();
        Double lastVal = 0.0;



        while(scanCSV.hasNext()){
            String line = scanCSV.nextLine();
            String[] arr = line.split(",");

            map.put(arr[0],lastVal + Double.parseDouble(arr[1]));

            lastVal += Double.parseDouble(arr[1]);

            System.out.println(arr[0]+" "+lastVal);

        }
        scanCSV.close();

        System.out.println("Done reading file (:->)");

        System.out.println("*********************************************************");

        ProbabilityGenerator probabilityGenerator = new ProbabilityGeneratorImpl(map);

        System.out.println("enter no of times strings have to be generated: ");
        Integer enteredResponse = scannerNormal.nextInt();

        Map<String,Integer> resultMap = new LinkedHashMap<>();

        File resFile = new File("result.txt");
        if(resFile.createNewFile()){
            System.out.println("Creating result File");
        } else {
            System.out.println("result file already exists");
        }

        FileWriter fileWriter = new FileWriter(resFile.getAbsoluteFile(),false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


        while(enteredResponse-- > 0){
            String res = probabilityGenerator.getNextString();
            bufferedWriter.write(res+"\n");

            if(resultMap.containsKey(res)){
                int count = resultMap.get(res);
                resultMap.put(res,++count);
            } else{
                resultMap.put(res,1);
            }
        }

        bufferedWriter.write("\n\n results are as follows: \n\n");
        for(String str: resultMap.keySet()){
            System.out.println(str+" "+resultMap.get(str));
            bufferedWriter.write(str+" "+resultMap.get(str) + "\n");
        }

        bufferedWriter.close();
    }
}

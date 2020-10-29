package test;

import psn.base.exception.RunException;
import psn.base.utils.ReadCSV;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCSV {
   static String file = "C:\\Users\\Dell\\Desktop\\nc_oa.csv";

    public static void main(String[] args) throws IOException, RunException {
        //testCsvFirst();

        testCSVSecond();
    }
    private static void testCSVSecond() throws RunException {
        ReadCSV csv=new ReadCSV(file);
        while (csv.hasNext()){
            Map<String,String> row=csv.next();
            /*for(String key:row.keySet()){
                System.out.print(row.get(key)+" ");
            }*/
            System.out.println(row.get("OACODE"));
        }
    }

    private static void testCsvFirst() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),"GBK"));

        String line=null;
        List<String[]> list=new ArrayList<>();
        while ((line=bufferedReader.readLine())!=null){
            line=line.replace("\"","");
            String[] cols=line.split(",");
            list.add(cols);
        }
        for(String[] arr:list){
            for(String str:arr){
                System.out.print(str+" ");
            }
            System.out.println();
        }
        bufferedReader.close();
        System.out.println(list.get(0).length);
        for (String str:list.get(0)) {
            System.out.println(str);
        }
        System.out.println(list.get(0).length);
    }
}

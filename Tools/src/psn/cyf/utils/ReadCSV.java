package psn.cyf.utils;

import psn.cyf.exception.RunException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class  ReadCSV  implements Iterator<Map<String,String>> {
    private String fileName;
    private String[] colName;
    private int curr;
    private int size;
    private String[][]content;

    public ReadCSV(String fileName) throws RunException {
        this.fileName = fileName;
        try {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)),"GBK"));
            List<String[]> list=new ArrayList<>();
            String line=null;
            while ((line=bufferedReader.readLine())!=null){
                line=line.replace("\"","");
                String[] cols=line.split(",");
                for(String s:cols){
                    s=s.trim();
                }
                list.add(cols);
            }
            if(list.size()==0||list.get(0).length<2){
                return;
            }
            //数组第一行第一列是空值，整个CSV文件第一列是行号
             colName=new String[list.get(0).length-1];
            for(int i=0;i<list.get(0).length-1;i++){
                colName[i]=list.get(0)[i+1];
            }
            size=list.size()-1;
            curr=-1;
            content=new String[size][colName.length];
            for(int row=0;row<size;row++){
                for(int col=0;col<colName.length;col++){
                    content[row][col]=list.get(row+1)[col+1];
                }
            }


        } catch (Exception e) {
            throw new RunException(e);
        }
    }
    public void reset(){
        curr=-1;
        size=content.length;
    }

    @Override
    public boolean hasNext() {

        return (curr+1)<size;
    }

    @Override
    public Map<String,String> next() {
        curr++;
       Map<String,String>map=  new HashMap<String,String>();
       for(int i=0;i<colName.length;i++){
           map.put(colName[i],content[curr][i]);
       }
        return map;
    }
}

package test;

import java.io.*;

public class GetXmlStringForNC {
    public static void main(String[] args) throws IOException {
        String file="C:\\Users\\Dell\\Desktop\\voucher.xml";
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)),"GBK"));
        String line =null;
        while ((line=bufferedReader.readLine())!=null){
            String tmp=line.replace("\"","\\\"");
            System.out.println("sb.append(\""+tmp+"\");");
        }

    }
}

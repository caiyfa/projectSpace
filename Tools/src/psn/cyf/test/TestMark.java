package psn.cyf.test;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

public class TestMark {
    public static void main(String[] args) throws ParseException {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("rowMax",5000);
        jsonObject.put("currentPage",5);
        jsonObject.put("globalQuery",true);
        jsonObject.put("queryDate","2020-11-06");
        System.out.println(jsonObject.toString());

        JSONObject jsonObject2=new JSONObject();
        JSONArray array=new JSONArray();
        jsonObject2.put("totalPage",25);
        jsonObject2.put("currentPage",5);
        jsonObject2.put("data",array);
        jsonObject2.put("success",true);
        jsonObject2.put("msg","");
        JSONObject data1=new JSONObject();
        data1.put("bankName","中国银行上海市虹桥临空经济园区支行");
        data1.put("branchBankNum","104290030166");
        data1.put("openingCity","上海市");
        data1.put("bankKind","中国银行");
        data1.put("bankPk","1001SX10000000009KH9");
        data1.put("ts", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        array.put(data1);
        JSONObject data2=new JSONObject();
        data2.put("bankName","中国工商银行股份有限公司北京洋桥支行");
        data2.put("branchBankNum","102100022630");
        data2.put("openingCity","北京市");
        data2.put("bankKind","中国工商银行");
        data2.put("bankPk","1001SX10000000009KB9");
        data2.put("ts", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        array.put(data2);
        System.out.println(jsonObject2.toString());

        JSONObject jsonObject3=new JSONObject();

        jsonObject3.put("queryDate","2020-11-06");


        System.out.println(jsonObject3.toString());
        JSONObject jsonObject4=new JSONObject();
        JSONArray array1=new JSONArray();
        jsonObject4.put("deletePks",array1);
        jsonObject4.put("success",true);
        jsonObject4.put("msg","");
        array1.put("1001SX10000000009KB9");
        array1.put("1001SX10000000009KH9");
        System.out.println(jsonObject4.toString());
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        LinkedHashMap<String, Object> values = new LinkedHashMap<>();
        values.put("busidate","2020-11-08 18:10:09");
        String busiDate=isNullWithTrim(values.get("busidate"))?sdf.format(new Date()) :sdf.format(sdf.parse(values.get("busidate").toString().trim()));
        System.out.println(busiDate);
    }
    private static  boolean isNullWithTrim(Object value){
        return value==null||value.toString().trim().length()==0;
    }
}

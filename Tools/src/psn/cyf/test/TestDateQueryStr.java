package psn.cyf.test;

public class TestDateQueryStr {
    public static void main(String[] args) {
        String strWhereStr="((trade_date >= '2020-10-26' and trade_date <= '2020-10-26')) and (isnull(dr,0)=0)";
        String replacedStr=strWhereStr.substring(strWhereStr.indexOf("trade_date"),strWhereStr.lastIndexOf("trade_date")+26);
       //1.获取所选日期
            //缩小范围
        String firstQueryStr=strWhereStr.substring(strWhereStr.indexOf("trade_date"),strWhereStr.lastIndexOf("trade_date"));
        String date=firstQueryStr.substring(firstQueryStr.indexOf("'"),firstQueryStr.lastIndexOf("'")+1);
        String replaceStr="view_param.set_param("+date+")="+date;
         String finalQueySql=strWhereStr.replace(replacedStr,replaceStr);


    }
}

package psn.cyf.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSwitch {
    public static void main(String[] args) throws ParseException {
        String value="ABD";
        String formula="select * from sm_user where user_code=#";
        System.out.println(value.split("B").length);
        String time="2020-11-10 15:03:00";
        System.out.println(new SimpleDateFormat("MM").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time)));
    }
}

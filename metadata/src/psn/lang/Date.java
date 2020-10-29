package psn.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Date {
    private String value;
    public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public Date(String value) {
        try {
            sdf.parse(value);
            this.value = value;
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public Date(Object value) {

        try {
            if(value!=null){
                sdf.parse(value.toString());
                this.value = value.toString();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
    public Date(java.util.Date date){
        this.value=sdf.format(date);
    }

    @Override
    public String toString() {
        return value;
    }
}

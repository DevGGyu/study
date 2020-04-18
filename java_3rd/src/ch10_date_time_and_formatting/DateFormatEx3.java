package ch10_date_time_and_formatting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatEx3 {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy년 MM월 dd일");
        SimpleDateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");

        try {
            Date d = df.parse("2015년 11월 23일");
            System.out.println(df2.format(d));
        } catch (Exception e) { }
    }
}

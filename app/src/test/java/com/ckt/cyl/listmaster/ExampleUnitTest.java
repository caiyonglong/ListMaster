package com.ckt.cyl.listmaster;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.println(new Date().getTime()-10);
        System.out.println(new Date(new Date().getTime()+1000*60*60));
        System.out.println(new Date().getTime());
        System.out.println(DateFormat(new Date()));
        System.out.println("---" + DateFormat(DateFormat(new Date())));
        System.out.println("---" + TimeFormat(new Date()));

    }

    public static String TimeFormat(Date date) {
        DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL);

        return fullDateFormat.format(date);
    }

    public static String DateFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return dateFormat.format(date);
    }

    public static Date DateFormat(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.parse(date);
    }
}
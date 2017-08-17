package services;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateParser {
    public Date parseDate(HttpServletRequest request){
        String reqDate = request.getParameter("date");
        DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);  //change the format
        Date date = null;
        try {
            date = format.parse(reqDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

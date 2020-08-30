package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ACER
 */
public class DateUtil {

    public static Date convert(String date) {
        try {
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return simpleDateFormat.parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }
    
     public static String convertToString(Date date) {
      DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");  
     return dateFormat.format(date);  
    }
}
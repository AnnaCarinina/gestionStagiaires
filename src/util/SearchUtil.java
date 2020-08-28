package util;

public class SearchUtil {
    public static String addConstraint(String beanAbrev, String atributeName, String operator, Object value) {
        if (value != null && !(value+"").equals("")) {
            return " AND " + beanAbrev + "." + atributeName + " " + operator + " '" + value + "'";
        }
        return "";
    }
}
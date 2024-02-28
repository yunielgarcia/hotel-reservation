package utils;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Utils {
    public static <T> void printCollection(Collection<T> collection, String resourceName) {

        if (collection == null || collection.isEmpty()) {
            String msg = "No " + resourceName + " resources found.";
            System.out.println(msg);
        } else {
            for (T t : collection) {
                System.out.println(t);
            }
        }
    }

    public static Date modifyDate(Date d) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE, 7);
        return cal.getTime();
    }
}

package utils;
import java.util.Collection;

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
}

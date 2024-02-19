package utils;
import java.util.Collection;

public class Utils {
    public static <T> void printCollection(Collection<T> collection) {

        if (collection == null || collection.isEmpty()) {
            System.out.println("No resources found.");
        } else {
            for (T t : collection) {
                System.out.println(t);
            }
        }
    }
}

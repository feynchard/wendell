package wendell.util;

import java.util.List;

public class DAOUtils {

	public static Object uniqueResult(List<? extends Object> objects) {
		if (objects.size() == 0) {
			return null;
		} else if (objects.size() == 1) {
			return objects.get(0);
		} else {
			throw new RuntimeException("should be unique result");
		}
	}
}

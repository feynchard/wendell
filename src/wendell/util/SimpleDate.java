package wendell.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class SimpleDate {

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public static Date createDate(String dateString) {
		if (StringUtils.trimToNull(dateString) == null) {
			return null;
		}

		try {
			return sdf.parse(dateString);
		} catch (ParseException e) {
			return new Date(Long.valueOf(dateString) * 1000);
		}

	}

	public static String toDateString(Date date) {
		return date == null ? "" : sdf.format(date);
	}
}

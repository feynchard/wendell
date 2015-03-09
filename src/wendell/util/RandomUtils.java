package wendell.util;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

	public static Integer randomInteger(Integer max) {
		Random random = new Random();
		return random.nextInt(max);
	}

	public static String randomString(Integer length) {
		return RandomStringUtils.random(length);
	}

	public static Long randomLong(Integer length) {
		return (long) (Math.random() * Math.pow(10, length));
	}
}

package com.exercise.mercadolibre.helper;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeHelper {

	public static String getTimeFromTimezone(String timezone) {
		String timeColonPattern = "HH:mm:ss";
		DateTimeFormatter timeColonFormatter = DateTimeFormatter.ofPattern(timeColonPattern);
		ZoneId zoneId = ZoneId.of(timezone);
		LocalTime localTime = LocalTime.now(zoneId);
		return localTime.format(timeColonFormatter);
	}
}

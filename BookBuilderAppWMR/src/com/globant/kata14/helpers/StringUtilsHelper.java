package com.globant.kata14.helpers;

import java.util.regex.Pattern;

public class StringUtilsHelper {
	private static final Pattern UNDESIRABLES = Pattern.compile("[\",-.;!?(){}\\[\\]<>%]");
	public static String clean(String x) {
		return UNDESIRABLES.matcher(x).replaceAll("").trim();
	}
}

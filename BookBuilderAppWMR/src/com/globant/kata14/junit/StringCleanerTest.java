package com.globant.kata14.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.globant.kata14.helpers.StringUtilsHelper;

public class StringCleanerTest {

	@Test
	public void test() {
		String source = "Where shall thy be, in this forsaken land? ";
		String clean = StringUtilsHelper.clean(source).trim().toLowerCase();
		assertEquals(clean, "where shall thy be in this forsaken land");

	}

}

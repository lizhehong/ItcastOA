package cn.itcast.oa.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayToStringUtilTest {

	@Test
	public void testArrayToStringBySeparator() {
		Long ids[] = { 1L, 1L, 1L,1L, 1L, 1L,1L, 1L, 1L,1L, 1L, 1L, 1L, 1L };
		String separator = "---";
		String content = ArrayToStringUtil.arrayToStringBySeparator(ids, "?",
				separator);
		int len = content.split(separator).length;
		assertEquals(ids.length, len);
	}

}

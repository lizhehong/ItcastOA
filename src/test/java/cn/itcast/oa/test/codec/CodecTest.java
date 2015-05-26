package cn.itcast.oa.test.codec;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class CodecTest {
	@Test
	public void test(){
		System.out.println(DigestUtils.md5("123"));
	}
}

package cn.itcast.oa.util;

public class ArrayToStringUtil {
	/**
	 * aim:返回 "content"+"Separator"+"content"+"Separator"..效果
	 * @param ids	需要组成的数组大小
	 * @param content	正文
	 * @param Separator 分隔符
	 * @return String
	 */
	public static String arrayToStringBySeparator(Object[] ids,String content,String Separator) {
		StringBuffer sb = new StringBuffer();
		int length = ids.length;
		for (int i = 0; i < length; i++) {// ids长度转"?"+","的个数
			sb.append(content);
			if (i != length - 1)
				sb.append(Separator);
		}
		return sb.toString();
	}
}

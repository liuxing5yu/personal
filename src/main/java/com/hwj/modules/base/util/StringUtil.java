package com.hwj.modules.base.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil {

	static int DEFAULT_UUID_LENGTH = 32;

	private static Random randomer = new Random();

	private static char[] QUID_AlphaNumerArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z' };

	private static char[] QUID_AlphaArray = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 将一个字符串str 中的的 字符replaced 替换成replaceString replaced是被替换者,replaceString是替换者
	 */
	static public String replace(String str, String replaced, String replaceString) {
		if (str == null)
			return null;
		if (str.equals(""))
			return "";
		StringBuilder str_buf = new StringBuilder();
		int start = 0, i = 0;
		i = str.indexOf(replaced, start);
		while (i >= 0) {
			str_buf.append(str.substring(start, i));
			str_buf.append(replaceString);
			start = i + replaced.length();
			i = str.indexOf(replaced, start);
		}
		if (start < str.length())
			str_buf.append(str.substring(start));

		return str_buf.toString();
	}

	/**
	 * 将一个值Value的前面的位数补零 补齐后的总位数为count
	 */
	static public String space(int value, int count) {
		return space(value + "", '0', count);
	}

	/**
	 * 将一个值str的前面的位数补成spacer值 补齐后的总位数为count
	 */
	static public String space(String str, char spacer, int count) {
		if (count <= 0)
			return "";
		if (str.length() >= count)
			return str;
		StringBuffer str_buf = new StringBuffer();
		count = count - str.length();
		for (int i = 0; i < count; i++) {
			str_buf.append(spacer);
		} // for i
		str_buf.append(str);
		return str_buf.toString();
	}

	/**
	 * 
	 * @param length
	 * @return
	 */
	public static String createQUID(int length) {
		StringBuilder str_builer = new StringBuilder();
		char c = '\0';
		c = QUID_AlphaArray[randomer.nextInt(QUID_AlphaArray.length)];
		str_builer.append(c);
		for (int i = 0; i < length - 1; i++) {
			c = QUID_AlphaNumerArray[randomer.nextInt(QUID_AlphaNumerArray.length)];
			str_builer.append(c);
		}
		return str_builer.toString();
	}

	/**
	 * 创建32位UUID字符串
	 * 
	 * @param length
	 * @return
	 */
	public static String createUUID() {
		return createQUID(DEFAULT_UUID_LENGTH);
	}

	/**
	 * 判断字段是否为空
	 * 
	 * @param str
	 * @return
	 */
	static public boolean isEmpty(String str) {
		if (str == null)
			return true;
		if ("".equals(str.trim()))
			return true;
		// ----------------------------------------------------------------------------------
		return false;
	}

	/**
	 * 判断字符是否为非空字段
	 * 
	 * @param str
	 * @return
	 */
	static public boolean notEmpty(String str) {
		return !isEmpty(str);
	}

	static public boolean bool(String str) {
		if (str == null)
			return false;
		if (str.equalsIgnoreCase("Y"))
			return true;
		if (str.equalsIgnoreCase("TRUE"))
			return true;
		if (str.equalsIgnoreCase("YES"))
			return true;
		try {
			if (Integer.parseInt(str) > 0)
				return true;
		} catch (Exception e) {
			return false;
		}
		// ----------------------------------------------------------------------------------
		return false;
	}

	public static String assemblySqlInQueryString(String listStr, String separator) {

		String[] strs = listStr.split(separator);
		String strsId = "";
		int count = strs.length;
		for (int i = 0; i < count; i++) {
			String strId = strs[i];
			if (i == 0)
				strsId = strsId + "'" + strId + "'";
			else
				strsId = strsId + ",'" + strId + "'";
		}
		return strsId;
	}

	public static String getPdfHtmlTmpl() {
		StringBuffer html = new StringBuffer();
		html.append(
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		html.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		html.append("<head>");
		html.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
		html.append(
				"<style type=\"text/css\" mce_bogus=\"1\">body {font-family: SimSun;text-align：center;} .avanta {width: 180px;height: 180px;} @page{size:210mm 297mm;} </style>");
		html.append("</head>");
		html.append("<body style='margin:0;padding:0'>");
		html.append("<p align=\"center\">&nbsp;</p>");
		html.append("<center>");
		html.append("@HTML_PDF@");
		html.append("</center>");
		html.append("</body></html>");
		return html.toString();
	}

	public static String formatNumber(String pattern, Double value) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(value);
	}

	public static String formatNumber(String pattern, Float value) {
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(value);
	}

	static public String escape(String str) {
		return escape(str, "%");
	}

	static public String escape(String str, String prefix) {
		StringBuffer str_buf = new StringBuffer();
		String prefix_ext = prefix + "u";
		// -------------------------------------------------------------------------------------------------------------------------------
		str_buf.ensureCapacity(str.length() * 6);
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c) || Character.isLowerCase(c) || Character.isUpperCase(c)) {
				str_buf.append(c);
			} else if (c < 256) {
				str_buf.append(prefix);
				if (c < 16)
					str_buf.append("0");
				str_buf.append(Integer.toString(c, 16));
			} else {
				str_buf.append(prefix_ext);
				str_buf.append(Integer.toString(c, 16));
			}
		} // for i
		return str_buf.toString();
	}

	public static String unescape(String str) {
		return unescape(str, "%");
	}

	public static String unescape(String str, String prefix) {
		if (str.length() <= 0)
			return str;
		StringBuffer str_buf = new StringBuffer();
		int pos = 0, lastPos = 0;
		char c = '\0';
		// -------------------------------------------------------------------------------------------------------------------------------
		str_buf.ensureCapacity(str.length());
		while (lastPos < str.length()) {
			pos = str.indexOf(prefix, lastPos);
			// -------------------------------------------------------------------------------------------------------------------------------
			if (pos == lastPos) {
				if (str.charAt(pos + 1) == 'u') {
					c = (char) Integer.parseInt(str.substring(pos + 2, pos + 6), 16);
					str_buf.append(c);
					lastPos = pos + 6;
				} else {
					c = (char) Integer.parseInt(str.substring(pos + 1, pos + 3), 16);
					str_buf.append(c);
					lastPos = pos + 3;
				} // if src
			} else {
				if (pos == -1) {
					str_buf.append(str.substring(lastPos));
					lastPos = str.length();
				} else {
					str_buf.append(str.substring(lastPos, pos));
					lastPos = pos;
				} // if pos
			} // if pos
		} // while

		return str_buf.toString();
	}

	public static boolean isContainModel(long modelId, String modelListStr) {

		if (StringUtil.isEmpty(modelListStr))
			return false;
		String[] modelArray = modelListStr.split(",");
		for (int i = 0; i < modelArray.length; i++) {
			String modelStr = modelArray[i];
			if (StringUtil.isEmpty(modelStr))
				return false;
			if (modelId == Long.parseLong(modelStr))
				return true;
		}
		return false;
	}

	public static boolean isContainModel(String modelId, String modelListStr) {

		if (StringUtil.isEmpty(modelListStr))
			return false;
		String[] modelArray = modelListStr.split(",");
		for (int i = 0; i < modelArray.length; i++) {
			String modelStr = modelArray[i];
			if (StringUtil.isEmpty(modelStr))
				return false;
			if (modelId.equals(modelStr))
				return true;
		}
		return false;
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String decryptBASE64(String key) throws Exception {
		return new String((new BASE64Decoder()).decodeBuffer(key));
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(String key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key.getBytes());
	}

	/**
	 * 每隔固定数目字符串换行
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String toMultiLine(String str, int len) {
		StringBuffer result = new StringBuffer(str);
		int index;
		for (index = len; index < str.length(); index += len + 1) {
			result.insert(index, '\n');
		}
		return result.toString();
	}

	/**
	 * 判断是否是数字
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isContain(List<String> alls, String find) {
		if (alls == null || alls.size() <= 0)
			return false;
		for (int i = 0; i < alls.size(); i++) {
			String current = alls.get(i);
			if (current != null && current.equals(find))
				return true;
		}
		return false;
	}

	public static String encode(String encodeStr) throws UnsupportedEncodingException {
		return encode(encodeStr, DEFAULT_ENCODING);
	}

	public static String encode(String encodeStr, String encodeing) throws UnsupportedEncodingException {
		return URLEncoder.encode(encodeStr, encodeing);
	}

	public static String decode(String decodeStr) throws UnsupportedEncodingException {
		return decode(decodeStr, DEFAULT_ENCODING);
	}

	public static String decode(String decodeStr, String encodeing) throws UnsupportedEncodingException {
		return URLDecoder.decode(decodeStr, encodeing);
	}

	/**
	 * 判断sqlContent是否包含非SELECT关键字
	 * @param sqlContent
	 * @return
	 */
	public static boolean isNotContainSQLKeyword(String sqlContent) {
		boolean result = true;

		String[] sqlKeywords = { "CREATE", "ALTER", "DROP", "UPDATE", "DELETE", "INSERT", "GRANT", "REVOKE" };

		if (!StringUtil.isEmpty(sqlContent)) {
			String keyword;
			for (int i = 0; i < sqlKeywords.length; i++) {
				keyword = sqlKeywords[i];
				if (sqlContent.toUpperCase().startsWith(keyword)) {
					result = false;
				}
			}
		}
		return result;
	}

}

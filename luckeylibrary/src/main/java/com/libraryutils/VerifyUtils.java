package com.libraryutils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {
	/**
	 * 验证邮箱地址是否正确
	 * 
	 * @param email
	 * @return
	 */
	public static boolean verifyEmail(String email) {
		boolean flag = false;
		try {
			Pattern regex = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}

		return flag;
	}


	/**
	 * 判断是否是身份证，
	 *
	 * @param str
	 * @return
	 */
	public static boolean identity(String str) {
		Pattern pattern = Pattern
				.compile("^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 验证手机号码
	 * 
	 * @param
	 * @return [0-9]{5,9}
	 */
	public static boolean verifyPhone(String phone) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(17[0-9])|(18[0,5-9]))\\d{8}$");
			Matcher m = p.matcher(phone);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return !flag;
	}

	public static boolean verifyNumber(String number) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^(-?[0-9]*.?[0-9]*)$");
			Matcher m = p.matcher(number);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	public static boolean verifyServerIp(String ip) {
		boolean flag = false;
		try {
			Pattern p = Pattern.compile("^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})(\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})){3}$");
			Matcher m = p.matcher(ip);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
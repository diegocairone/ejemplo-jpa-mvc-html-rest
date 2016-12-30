package com.cairone.appexample.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class Md5Encoder {

	public static String encode(String rawData)
	{
		Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
		String encodedData = md5PasswordEncoder.encodePassword(rawData, null);
		
		return encodedData;
	}
}

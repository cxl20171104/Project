package com.alphasta.commons.utils;

import java.util.UUID;

public class GetIdUtil {
	public static String getId() {
		String uuid = UUID.randomUUID().toString();
		String id = uuid.replaceAll("-", "");
		return id;
	}

	// test
	public static void main(String[] args) {
		System.out.println(getId());
	}
}

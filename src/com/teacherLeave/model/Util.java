package com.teacherLeave.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

	public static Map<Integer, String> leaveReason = null;
	public static Map<Integer, String> leaveStatus = null;
	static {
		leaveReason = new LinkedHashMap<>();
		leaveReason.put(0, "病假");
		leaveReason.put(1, "事假");
		leaveReason.put(2, "喪假");
		leaveReason.put(3, "其它");

		leaveStatus = new LinkedHashMap<>();
		leaveStatus.put(0, "尚未審核");
		leaveStatus.put(1, "同意");
		leaveStatus.put(2, "不同意");
		leaveStatus.put(3, "全部");	
	}

	
}

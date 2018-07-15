package nl.bb.openb.util;

import java.util.List;

import nl.openb.pojo.Transaction;

public class CommonUtils {

	public static boolean isListNotNullAndNotEmpty(
			List<Transaction> transactions) {
		if(null != transactions && !transactions.isEmpty()){
			return true;
		}
		return false;
	}

	public static boolean isStringNullOrEmpty(String value) {
		if(null == value || value.isEmpty()){
			return true;
		}
		return false;
	}

}

package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final int ACC_PAGE_SECTIONS_COUNT = 4;
	public static final int PRODUCT_IMAGES_COUNT_MACBOOK = 4;
	public static final String ACC_CREATION_SUCCESS_MESG = "Your Account Has Been Created!";
	
	/********************SHEET NAMES************************/
	
	public static final String REGISTER_SHEET = "register";

	
	public static List<String> expectedAccSecList() {
		List<String> expSecList = new ArrayList<String>();
		expSecList.add("My Account");
		expSecList.add("My Orders");
		expSecList.add("My Affiliate Account");
		expSecList.add("Newsletter");
		return expSecList;
	}

}

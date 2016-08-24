package com.hong.ocp.dateNumbersCurrenciesAndLocales.date;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ClassDate {
	public static void main(String [] args) {
		
		// get the current date and time
		Date currentDate = new Date();
		String currentDateStr = currentDate.toString();
		System.out.println("current date : " + currentDateStr);
		
		// 
		Locale currentLocale =  new Locale("de");
		
		// get an object that let you perform date and time calculations in your locale
		Calendar currentCalendar = Calendar.getInstance(currentLocale);
		//currentCalendar.add(Calendar.DAY_OF_YEAR, 2);
		currentCalendar.add(Calendar.HOUR_OF_DAY, 2);
		currentCalendar.roll(Calendar.DAY_OF_YEAR, 2);
		
		//
		currentDate = currentCalendar.getTime();
		
		// 
		DateFormat currentDateFormat = DateFormat.getDateInstance(DateFormat.FULL, currentLocale);
		currentDateStr = currentDateFormat.format(currentDate);
		System.out.println("modified date : " + currentDateStr);
		
		//
		NumberFormat currentNumberFormat = NumberFormat.getInstance(currentLocale);
		NumberFormat currencyNumberFormat = NumberFormat.getCurrencyInstance(currentLocale);
		
		String numberStr1 = currentNumberFormat.format(10000);
		String numberStr2 = currencyNumberFormat.format(2900);
		
		System.out.println(numberStr1);
		System.out.println(numberStr2);
		
	}
}

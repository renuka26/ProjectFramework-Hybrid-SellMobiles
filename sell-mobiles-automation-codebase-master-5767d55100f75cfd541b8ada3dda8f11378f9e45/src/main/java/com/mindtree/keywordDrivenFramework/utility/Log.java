package com.mindtree.keywordDrivenFramework.utility;

		import org.apache.log4j.Logger;

	public class Log {
		private static Logger Log = Logger.getLogger(Log.class.getName());

		public static void startTestCase(String sTestCaseName){
			Log.info("TEST CASE STARTED " + sTestCaseName);
	   }

	public static void endTestCase(String sTestCaseName){
	   Log.info("TEST CASE ENDED ");
	   }

   
	public static void info(String message)
	{
		   Log.info(message);
	 }

	public static void warn(String message) {
	   Log.warn(message);
	   }

	public static void error(String message) {
	   Log.error(message);
	   }

	public static void fatal(String message) {
	   Log.fatal(message);
	   }

	public static void debug(String message) {
	   Log.debug(message);
	   }

	}
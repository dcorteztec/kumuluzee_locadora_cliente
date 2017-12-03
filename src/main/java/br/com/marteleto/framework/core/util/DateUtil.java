package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class DateUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	protected transient static Logger LOG = Logger.getLogger(DateUtil.class.getName());
	
	public static Integer YEAR_DIFF = null;
	public static Integer MONTH_DIFF = null;
	public static Integer DAY_DIFF = null;
	public static Integer HOUR_DIFF = null;
	
	public static boolean isDateStimulus() {
		if (
				DateUtil.YEAR_DIFF != null || DateUtil.YEAR_DIFF != 0 ||
				DateUtil.MONTH_DIFF != null || DateUtil.MONTH_DIFF != 0 ||
				DateUtil.DAY_DIFF != null || DateUtil.DAY_DIFF != 0 ||
				DateUtil.HOUR_DIFF != null || DateUtil.HOUR_DIFF != 0
			) {
			return true;
		}
		return false;
	}
	
	public static void clearDateStimulus() {
		DateUtil.YEAR_DIFF = null;
		DateUtil.MONTH_DIFF = null;
		DateUtil.DAY_DIFF = null;
		DateUtil.HOUR_DIFF = null;
	}
	
	public static String convertDateToString(String format, Date date){
        String result = null;
        try{
        	SimpleDateFormat df = new SimpleDateFormat(format, ResourceUtil.getLocale());
        	result = df.format(date);
        } catch (Exception ex) {
            LOG.severe("Fail: convertDateToString");
        }
        return result;
    }
    
    public static Date convertStringToDate(String format, String date){
    	Date result = null;
        try{
        	SimpleDateFormat df = new SimpleDateFormat(format, ResourceUtil.getLocale());
        	result = df.parse(date);
        } catch (Exception ex) {
        	LOG.severe("Fail: convertStringToDate");
        }
        return result;
    }
    
    public static Date formatDate(String format, Date date){
    	Date result = null;
        try{
        	SimpleDateFormat df = new SimpleDateFormat(format, ResourceUtil.getLocale());
        	result = df.parse(df.format(date));
        } catch (Exception ex) {
        	LOG.severe("Fail: formatDate");
        }
        return result;
    }
    
    public static Date currentDate(){
    	Calendar calendar = Calendar.getInstance(ResourceUtil.getLocale());
    	if (DateUtil.YEAR_DIFF != null && DateUtil.YEAR_DIFF != 0) {
    		calendar.add(Calendar.YEAR, DateUtil.YEAR_DIFF);
    	}
    	if (DateUtil.MONTH_DIFF != null && DateUtil.MONTH_DIFF != 0) {
    		calendar.add(Calendar.MONTH, DateUtil.MONTH_DIFF);
    	}
    	if (DateUtil.DAY_DIFF != null && DateUtil.DAY_DIFF != 0) {
    		calendar.add(Calendar.DAY_OF_MONTH, DateUtil.DAY_DIFF);
    	}
    	if (DateUtil.HOUR_DIFF != null && DateUtil.HOUR_DIFF != 0) {
    		calendar.add(Calendar.HOUR_OF_DAY, DateUtil.HOUR_DIFF);
    	}
        return calendar.getTime();
    }
    
    public static Long dateDiff(Date initialDate, Date finalDate) {
    	Long result = null;
    	try {
    		if (initialDate == null) {
    			throw new Exception("Start date not found.");
    		}
    		if (finalDate == null) {
    			throw new Exception("Finish date not found.");
    		}
	        Calendar c1 = Calendar.getInstance(ResourceUtil.getLocale());  
	        c1.setTime(initialDate);
	        Calendar c2 = Calendar.getInstance(ResourceUtil.getLocale());  
        	c2.setTime(finalDate);	
	        result = (c2.getTimeInMillis() - c1.getTimeInMillis());
    	 } catch (Exception ex) {
    		 LOG.severe("Fail: dateDiff");
         }
    	return result;
    }
    
    public static Date dateAdd(Date date, Integer day) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.DATE, day);
	    return calendar.getTime();
    }
}
package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.logging.Logger;

public class NumberUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	protected transient static Logger LOG = Logger.getLogger(NumberUtil.class.getName());
	
	public static String convertNumberToString(String format, Object number){
        String result = null;
        if (number != null && format != null && !"".equals(format.trim())) {
	        try {
	        	DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(ResourceUtil.getLocale());
	        	DecimalFormat df = new DecimalFormat(format,decimalFormatSymbols);
	        	result = df.format(number);
	        } catch (Exception ex) {
	            LOG.severe("Fail: convertNumberToString");
	        }
        }
        return result;
    }
	
	public static String formatCurrency(Object number){
        String result = null;
        if (number != null) {
	        try {
	        	DecimalFormat formatter = (DecimalFormat) NumberFormat.getCurrencyInstance(ResourceUtil.getLocale());
	        	DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
	        	formatter.setDecimalFormatSymbols(symbols);
	        	result = formatter.format(number);
	        } catch (Exception ex) {
	        	LOG.severe("Fail: formatCurrency");
	        }
        }
        return result;
    }
}

package br.com.marteleto.framework.core.util;


import java.io.Serializable;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConverterUtil implements Serializable {
	private static final long serialVersionUID = 1L;
    private static final Map<String, Method> CONVERTERS = new HashMap<String, Method>();

    static {
        Method[] methods = ConverterUtil.class.getDeclaredMethods();
        for (Method method : methods) {
            //if (method.getParameterTypes().length == 1) {
                CONVERTERS.put(method.getParameterTypes()[0].getName() + "_" + method.getReturnType().getName(), method);
            //}
        }
    }

    private ConverterUtil() {
    }

    /**
     * Convert the given object value to the given class.
     * @param from The object value to be converted.
     * @param to The type class which the given object should be converted to.
     * @return The converted object value.
     * @throws NullPointerException If 'to' is null.
     * @throws UnsupportedOperationException If no suitable converter can be found.
     * @throws RuntimeException If conversion failed somehow. This can be caused by at least
     * an ExceptionInInitializerError, IllegalAccessException or InvocationTargetException.
     */
    public static <T> T convert(Object from, Class<T> to) {
        if (from == null) {
            return null;
        }
        if (to.isAssignableFrom(from.getClass())) {
            return to.cast(from);
        }
        String converterId = null;
        if (to.isEnum()) {
        	converterId = from.getClass().getName() + "_java.lang.Enum";
        } else {
        	converterId = from.getClass().getName() + "_" + to.getName();
        }
        Method converter = CONVERTERS.get(converterId);
        if (converter == null) {
       		throw new UnsupportedOperationException("Cannot convert from " + from.getClass().getName() + " to " + to.getName() + ". Requested converter does not exist.");
        }
        try {
        	if (to.isEnum()) {
        		return to.cast(converter.invoke(to,from,to));
        	} else {
        		return to.cast(converter.invoke(to,from));
        	}
        } catch (Exception e) {
            throw new RuntimeException("Cannot convert from " + from.getClass().getName() + " to " + to.getName() + ". Conversion failed with " + e.getMessage(), e);
        }
    }

    // Converters ---------------------------------------------------------------------------------

    /**
     * Converts Integer to Boolean. If integer value is 0, then return FALSE, else return TRUE.
     * @param value The Integer to be converted.
     * @return The converted Boolean value.
     */
    public static Boolean integerToBoolean(Integer value) {
        return value.intValue() == 0 ? Boolean.FALSE : Boolean.TRUE;
    }

    /**
     * Converts Boolean to Integer. If boolean value is TRUE, then return 1, else return 0.
     * @param value The Boolean to be converted.
     * @return The converted Integer value.
     */
    public static Integer booleanToInteger(Boolean value) {
        return value.booleanValue() ? Integer.valueOf(1) : Integer.valueOf(0);
    }

    /**
     * Converts Double to BigDecimal.
     * @param value The Double to be converted.
     * @return The converted BigDecimal value.
     */
    public static BigDecimal doubleToBigDecimal(Double value) {
        return new BigDecimal(value);
    }
    
    /**
     * Converts Double to Long.
     * @param value The Double to be converted.
     * @return The converted Long value.
     */
    public static Long doubleToLong(Double value) {
        return value.longValue();
    }

    /**
     * Converts BigDecimal to Double.
     * @param value The BigDecimal to be converted.
     * @return The converted Double value.
     */
    public static Double bigDecimalToDouble(BigDecimal value) {
        return new Double(value.doubleValue());
    }
    
    /**
     * Converts BigDecimal to Long.
     * @param value The BigDecimal to be converted.
     * @return The converted Long value.
     */
    public static Long bigDecimalToLong(BigDecimal value) {
        return new Long(value.longValue());
    }
    
    /**
     * Converts BigDecimal to Integer.
     * @param value The BigDecimal to be converted.
     * @return The converted Integer value.
     */
    public static Integer bigDecimalToInteger(BigDecimal value) {
        return new Integer(value.intValue());
    }

    /**
     * Converts Integer to String.
     * @param value The Integer to be converted.
     * @return The converted String value.
     */
    public static String integerToString(Integer value) {
        return value.toString();
    }
    
    /**
     * Converts Integer to BigDecimal.
     * @param value The Integer to be converted.
     * @return The converted BigDecimal value.
     */
    public static BigDecimal integerToBigDecimal(Integer value) {
        return new BigDecimal(value.toString());
    }
    
    /**
     * Converts BigDecimal to BigDecimal.
     * @param value The BigDecimal to be converted.
     * @return The converted BigDecimal value.
     */
    public static BigDecimal bigDecimalToBigDecimal(BigDecimal value) {
        return value;
    }
    
    /**
     * Converts Integer to Long.
     * @param value The Integer to be converted.
     * @return The converted Long value.
     */
    public static Long integerToLong(Integer value) {
        return Long.valueOf(value.toString());
    }

    /**
     * Converts String to Integer.
     * @param value The String to be converted.
     * @return The converted Integer value.
     */
    public static Integer stringToInteger(String value) {
    	if (value != null && !"".equals(value.trim())) {
    		return Integer.valueOf(value.trim());
    	}
    	return null;
    }
    
    /**
     * Converts String to BigDecimal.
     * @param value The String to be converted.
     * @return The converted BigDecimal value.
     */
    public static BigDecimal stringToBigDecimal(String value) {
        if (value != null && !"".equals(value.trim())) {
        	return new BigDecimal(value.trim());
    	}
    	return null;
    }
    
    /**
     * Converts BigDecimal to String.
     * @param value The BigDecimal to be converted.
     * @return The converted String value.
     */
    public static String bigDecimalToString(BigDecimal value) {
        if (value != null ) {
        	return value.toString();
    	}
    	return null;
    }

    /**
     * Converts Boolean to String.
     * @param value The Boolean to be converted.
     * @return The converted String value.
     */
    public static String booleanToString(Boolean value) {
        return value.toString();
    }

    /**
     * Converts String to Boolean.
     * @param value The String to be converted.
     * @return The converted Boolean value.
     */
    public static Boolean stringToBoolean(String value) {
        return Boolean.valueOf(value);
    }
    
    /**
     * Converts Character to String.
     * @param value The Character to be converted.
     * @return The converted String value.
     */
	public static String characterToString(Character value) {
        return value.toString();
    }
	
	 /**
     * Converts Long to Integer.
     * @param value The Long to be converted.
     * @return The converted Integer value.
     */
	public static Integer longToInteger(Long value) {
        return Integer.valueOf(value.toString());
    }
	
	
	/**
     * Converts BigInteger to Integer.
     * @param value The BigInteger to be converted.
     * @return The converted Integer value.
     */
	public static Integer bigIntegerToInteger(BigInteger value) {
		return Integer.valueOf(value.toString());
    }
	
	/**
     * Converts BigInteger to Long.
     * @param value The BigInteger to be converted.
     * @return The converted Long value.
     */
	public static Long bigIntegerToLong(BigInteger value) {
		return Long.valueOf(value.toString());
    }
	
    /**
     * Converts String to Enum.
     * @param value The String to be converted.
     * * @param type The Type to convert.
     * @return The converted Enum value.
     */
	public static Enum stringToEnum(String value, Class type) {
        return Enum.valueOf((Class<Enum>) type, value);
    }
	
	/**
     * Converts Character to Enum.
     * @param value The Character to be converted.
     * @param type The Type to convert.
     * @return The converted Enum value.
     */
	public static Enum characterToEnum(Character value, Class type) {
        return Enum.valueOf((Class<Enum>) type, value.toString());
    }
}

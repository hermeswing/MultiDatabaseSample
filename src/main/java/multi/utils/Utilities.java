package multi.utils;

import java.io.File;
import java.lang.reflect.Method;
import java.util.*;

public class Utilities {
    /**
     * 문자 숫자로 고유값 생성
     *
     * @param nLength
     * @return
     */
    public static String getUniqID(int nLength) {
        return StringUtils.getUniqID(nLength);
    }

    /**
     * 빈객체가 아닌지 여부
     *
     * @param obj
     * @return
     */
    static public boolean isNotEmpty(Object obj) {
        return !Utilities.isEmpty(obj);
    }

    /**
     * 빈객체인가
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("rawtypes")
    static public boolean isEmpty(Object obj) {

        if (obj == null) return true;
        else if ((obj instanceof String)) {
            return ((String) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        } else if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        } else if (obj instanceof Object[]) {
            return (((Object[]) obj).length == 0);
        } else if (obj instanceof File) {
            return !((File) obj).exists();
        } else if (obj instanceof List) {
            return ((List) obj).size() == 0;
        }
        try {
            Method method = obj.getClass().getMethod("isEmpty", (Class<?>[]) null);
            return (Boolean) method.invoke(obj);
        } catch (Exception ex) {
            return false;
        }
    }


    /**
     * 카멜 형식으로 변환
     *
     * @param list
     * @return
     */
    public static List<Map<String, Object>> convert2CamelCase(List<Map<String, Object>> list) {
        List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < list.size(); i++) {
            ret.add(convert2CamelCase(list.get(i)));
        }
        return ret;
    }

    /**
     * 카멜 형식으로 변환
     *
     * @param map
     * @return
     */
    public static Map<String, Object> convert2CamelCase(Map<String, Object> map) {
        Map<String, Object> ret = new HashMap<String, Object>();
        for (String key : map.keySet()) {
            ret.put(convert2CamelCase(key), map.get(key));
        }
        return ret;
    }

    /**
     * 카멜 형식으로 변환
     *
     * @param underScore
     * @return
     */
    public static String convert2CamelCase(String underScore) {

        if (underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0))) {
            return underScore;
        }
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();

        for (int i = 0; i < len; i++) {
            char currentChar = underScore.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }

    /**
     * 문자열이 Null 일경우 "" 리턴
     *
     * @param strValue
     * @return
     */
    public static String nullCheck(String strValue) {
        return ((strValue == null) ? "" : strValue);
    }

    /**
     * 문자열이 Null 일경우 "" 리턴
     *
     * @param obj
     * @return
     */
    public static String nullCheck(Object obj) {
        return ((obj == null) ? "" : obj.toString());
    }


    /**
     * boolean 형식으로 변환 에러시 false 리턴
     *
     * @param strValue
     * @return
     */
    public static boolean parseBoolean(String strValue) {
        try {
            if (isEmpty(strValue)) return false;
            return Boolean.parseBoolean(strValue);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * boolean 형식으로 변환 에러시 false 리턴
     *
     * @param obj
     * @return
     */
    public static boolean parseBoolean(Object obj) {
        try {
            if (isEmpty(obj)) return false;
            return parseBoolean(obj.toString());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * int 형식으로 변환 에러시 0 리턴
     *
     * @param obj
     * @return
     */
    public static int parseInt(Object obj) {
        return NumberUtils.parseInt(obj);
    }

    /**
     * long 형식으로 변환 에러시 0 리턴
     *
     * @param strValue
     * @return
     */
    public static long parseLong(String strValue) {
        return NumberUtils.parseLong(strValue);
    }

    /**
     * long 형식으로 변환 에러시 0 리턴
     *
     * @param obj
     * @return
     */
    public static long parseLong(Object obj) {
        return NumberUtils.parseLong(obj);
    }

    /**
     * 콤마들어가는 형식
     *
     * @param nNumber
     * @return
     */
    public static String getNumberString(long nNumber) {
        return getNumberString(nNumber);
    }

    /**
     * 콤마 들어가는 형식
     *
     * @param number
     * @param num
     * @return
     */
    static public String getNumberString(double number, int num) {
        return getNumberString(number, num);
    }

    /**
     * 반올림
     *
     * @param number
     * @param num
     * @return
     */
    static public double round(double number, int num) {
        return NumberUtils.round(number, num);
    }

    /**
     * 반올림
     *
     * @param number
     * @param num
     * @return
     */
    static public double round(float number, int num) {
        return NumberUtils.round(number, num);
    }

}

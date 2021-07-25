package multi.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberUtils {
    
    private static Logger logger = LoggerFactory.getLogger( NumberUtils.class );
    
    /**
     * 반올림
     *
     * @param number
     * @param num
     * @return
     */
    static public double round( double number, int num ) {
        double pow   = Math.pow( 10, num );
        double tmp   = number * pow;
        double value = Math.round( tmp );
        return value / pow;
    }
    
    /**
     * 반올림
     *
     * @param number
     * @param num
     * @return
     */
    static public double round( float number, int num ) {
        double pow   = Math.pow( 10, num );
        double tmp   = number * pow;
        long   value = Math.round( tmp );
        return value / pow;
    }
    
    /**
     * 반올림 문자열
     *
     * @param number
     * @param num
     * @return
     */
    static public String roundString( double number, int num ) {
        double value = round( number, num );
        return Utilities.getNumberString( value, num );
    }
    
    /**
     * 반올림 문자열
     *
     * @param number
     * @param num
     * @return
     */
    static public String roundString( float number, int num ) {
        double value = Utilities.round( number, num );
        return Utilities.getNumberString( value, num );
    }
    
    /**
     * 콤마들어가는 형식
     *
     * @param nNumber
     * @return
     */
    public static String getMoneyString( long nNumber ) {
        return getNumberString( nNumber );
    }
    
    /**
     * 콤마들어가는 형식
     *
     * @param nNumber
     * @return
     */
    public static String getNumberString( long nNumber ) {
        String strMoney = "";
        while ( nNumber > 0 ) {
            if ( strMoney.length() > 0 ) strMoney = "," + strMoney;
            strMoney  = getNumberString( nNumber % 1000, 3 ) + strMoney;
            nNumber  /= 1000;
            if ( nNumber < 1 ) break;
        }
        strMoney = StringUtils.trimStart( strMoney, "0" );
        if ( strMoney.length() == 0 ) strMoney = "0";
        return strMoney;
    }
    
    /**
     * 콤마 들어가는 형식
     *
     * @param number
     * @param num
     * @return
     */
    static public String getNumberString( double number, int num ) {
        long   nVal = (long) number;
        String n    = getNumberString( nVal );
        if ( num < 1 ) return n;
        String format = String.format( "%" + num + "f", number );
        int    nIndex = format.indexOf( "." );
        
        String dbVal  = "";
        if ( nIndex > -1 ) dbVal = format.substring( nIndex + 1 );
        
        String zero = "";;
        for ( int i = 0; i < num; i++ ) {
            zero += "0";
        }
        String db = dbVal + zero;
        db = db.substring( 0, num );
        return n + "." + db;
    }
    
    /**
     * Double 형식으로 변환 에러시 0 리턴
     *
     * @param strValue
     * @return
     */
    public static double parseDouble( String strValue ) {
        try {
            return parseDouble( strValue );
        } catch ( Exception e ) {
            return 0.0;
        }
    }
    
    /**
     * Double 형식으로 변환 에러시 0 리턴
     *
     * @param obj
     * @return
     */
    public static double parseDouble( Object obj ) {
        try {
            if ( obj == null ) return 0.0;
            return Double.parseDouble( obj.toString() );
        } catch ( Exception e ) {
            return 0.0;
        }
    }
    
    /**
     * Float 형식으로 변환 에러시 0 리턴
     *
     * @param strValue
     * @return
     */
    public static float parseFloat( String strValue ) {
        try {
            return parseFloat( strValue );
        } catch ( Exception e ) {
            return 0.0f;
        }
    }
    
    /**
     * Float 형식으로 변환 에러시 0 리턴
     *
     * @param obj
     * @return
     */
    public static float parseFloat( Object obj ) {
        try {
            if ( Utilities.isEmpty( obj ) ) return 0.0f;
            return Float.parseFloat( obj.toString() );
        } catch ( Exception e ) {
            return 0.0f;
        }
    }
    
    /**
     * int 형식으로 변환 에러시 0 리턴
     *
     * @param obj
     * @return
     */
    public static int parseInt( Object obj ) {
        try {
            if ( Utilities.isEmpty( obj ) ) return 0;
            return (int) Double.parseDouble( obj.toString() );
        } catch ( Exception e ) {
            return 0;
        }
    }
    
    /**
     * long 형식으로 변환 에러시 0 리턴
     *
     * @param strValue
     * @return
     */
    public static long parseLong( String strValue ) {
        
        try {
            return Long.parseLong( strValue );
        } catch ( Exception e ) {
            return 0;
        }
    }
    
    /**
     * long 형식으로 변환 에러시 0 리턴
     *
     * @param obj
     * @return
     */
    public static long parseLong( Object obj ) {
        try {
            if ( obj == null ) return 0;
            return Utilities.parseLong( obj.toString() );
        } catch ( Exception e ) {
            logger.error( e.getMessage() );
            return 0;
        }
    }

    /**
     * 숫자인지 아닌지 확인.
     * @param input
     * @return
     */
    public static boolean isNumber( String input ) {
        try {
            Double.parseDouble( input );
            return true;
        } catch ( NumberFormatException e ) {
            return false;
        }
    }
}

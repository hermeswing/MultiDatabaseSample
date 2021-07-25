package multi.utils;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {
    
    private static Logger       logger    = LoggerFactory.getLogger( StringUtils.class );
    
    private static final String _ENC_LANG = "UTF-8";
        
    /**
     * <pre>
     * 고유한 문자열 리턴
     * 사용예) 176C3D44EA3CSVVUTVOJ
     * </pre>
     *
     * @return
     */
    public static String getPKCd() {
        return getPKCd( Long.toHexString( System.currentTimeMillis() ).toUpperCase() );
    }
    
    /**
     * 고유한 문자열 리턴
     *
     * @param prefix
     * @return
     */
    public static String getPKCd( String prefix ) {
        int length = prefix == null ? 20 : 20 - prefix.length();
        if ( length < 0 ) return prefix.substring( 0, 20 );
        return Utilities.nullCheck( prefix ) + Utilities.getUniqID( length );
    }
    
    /**
     * <pre>
     * 문자 숫자로 고유값 생성(문자로 길이만큼)
     * 생성예) 'HGXIWLLGJQVIH'
     * </pre>
     * 
     * @param nLength
     * @return
     */
    public static String getUniqID( int nLength ) {
        if ( nLength < 1 ) return "";
        Random     rd      = new Random();
        final char chAdded = 'A';
        final char chRange = (char) 26;
        char[]     chValue = new char[nLength];
        for ( int i = 0; i < nLength; i++ ) {
            chValue[i] = (char) ( rd.nextDouble() * chRange + chAdded );
            
        }
        return new String( chValue ).toUpperCase();
    }
    
    /**
     * 숫자로 고유값 생성
     *
     * @param nLength
     * @return
     */
    public static String getUniqNumberID( int nLength ) {
        if ( nLength < 1 ) return "";
        Random     rd      = new Random();
        
        final char chAdded = '0';
        final char chRange = (char) 9;
        char[]     chValue = new char[nLength];
        for ( int i = 0; i < nLength; i++ ) {
            chValue[i] = (char) ( rd.nextDouble() * chRange + chAdded + ( i == 0 ? 1 : 0 ) );
        }
        return new String( chValue );
    }
    
    /**
     * <pre>
     * uuid guid 생성
     * 생성예) 3051a8d7-aea7-1801-e0bf-bc539dd60cf3
     * </pre>
     *
     * @return
     */
    public static String getUUID() {
        return String.valueOf( UUID.randomUUID() );
    }
    
    
    /**
     * 문자열 앞부분 trim
     *
     * @param strSource
     * @param strTrim
     * @return
     */
    public static String trimStart( String strSource, String strTrim ) {
        if ( strTrim == null || strTrim.length() == 0 ) return strSource;
        while ( strSource.startsWith( strTrim ) ) {
            strSource = strSource.substring( strTrim.length() );
        }
        return strSource;
        
    }
    
    /**
     * 문자열 뒷부분 trim
     *
     * @param strSource
     * @param strTrim
     * @return
     */
    public static String trimEnd( String strSource, String strTrim ) {
        if ( strTrim == null || strTrim.length() == 0 ) return strSource;
        if ( strSource == null || strSource.length() == 0 ) return "";
        while ( strSource.endsWith( strTrim ) ) {
            int nIndex = strSource.length() - strTrim.length();
            strSource = strSource.substring( 0, nIndex );
        }
        return strSource;
        
    }
    
    /**
     * 왼쪽에 padding
     *
     * @param source
     * @param nSize
     * @param szPad
     * @return
     */
    public static String padLeft( Object source, int nSize, char szPad ) {
        String strSource = source == null ? "" : source.toString();
        
        int    nCnt      = nSize - strSource.length();
        if ( nCnt < 1 ) return strSource;
        for ( int i = 0; i < nCnt; i++ ) {
            strSource = szPad + strSource;
            
        }
        return strSource;
    }
    
    /**
     * 오른쪽 padding
     *
     * @param source
     * @param nSize
     * @param szPad
     * @return
     */
    public static String padRight( Object source, int nSize, char szPad ) {
        String strSource = source == null ? "" : source.toString();
        
        int    nCnt      = nSize - strSource.length();
        if ( nCnt < 1 ) return strSource;
        for ( int i = 0; i < nCnt; i++ ) {
            strSource = strSource + szPad;
        }
        return strSource;
    }
    
    /**
     * 숫자를 왼쪽에 0을 붙여 문자열로 리턴
     *
     * @param nNumber
     * @param nSize
     * @return
     */
    public static String getNumberString( long nNumber, int nSize ) {
        return getNumberString( nNumber, nSize, '0' );
    }
    
    /**
     * 숫자를 왼쪽에 @param chPad 을 붙여 문자열로 리턴
     *
     * @param nNumber
     * @param nSize
     * @param chPad
     * @return
     */
    public static String getNumberString( long nNumber, int nSize, char chPad ) {
        String strNumber = nNumber + "";
        return padLeft( strNumber, nSize, chPad );
    }
    
    /**
     * 자바스크립트 오류텍스트 변환
     *
     * @param strValue
     * @return
     */
    public static String getScriptText( String strValue ) {
        if ( strValue == null ) return "";
        return strValue.replace( "\"", "\\\"" ).replace( "\r\n", "\n" ).replace( "\n", "\\n" );
        
    }
    
    /**
     * 문자열의 byte 크기를 가져옴
     *
     * @param strText
     * @return
     * @throws Exception
     */
    
    public static byte[] getStringByte( String strText ) throws Exception {
        if ( strText == null || strText.length() == 0 ) return new byte[] {};
        return strText.getBytes( _ENC_LANG );
    }
}

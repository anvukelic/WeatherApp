package com.avukelic.weatherapp.util;

/**
 * Created by avukelic on 04-Oct-18.
 */
public class TextUtils {

    public static String stringFirstLetterUpper(String string){
        StringBuilder sb = new StringBuilder();
        sb.append(string.substring(0,1).toUpperCase());
        sb.append(string.substring(1).toLowerCase());
        return sb.toString();
    }
}

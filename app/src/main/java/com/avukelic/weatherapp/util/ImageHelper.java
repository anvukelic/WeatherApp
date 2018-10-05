package com.avukelic.weatherapp.util;

import com.avukelic.weatherapp.Consts;

/**
 * Created by avukelic on 04-Oct-18.
 */
public class ImageHelper {
    public static String createWeatherIconValue(String description) {
        if (description != null)
            switch (description) {
                case Consts.SNOW_CASE: {
                    return Consts.SNOW;
                }
                case Consts.RAIN_CASE: {
                    return Consts.RAIN;
                }
                case Consts.CLEAR_CASE: {
                    return Consts.SUN;
                }
                case Consts.MIST_CASE: {
                    return Consts.FOG;
                }
                case Consts.FOG_CASE: {
                    return Consts.FOG;
                }
                case Consts.HAZE_CASE: {
                    return Consts.FOG;
                }
                case Consts.CLOUD_CASE: {
                    return Consts.CLOUD;
                }
            }
        return "";
    }
}

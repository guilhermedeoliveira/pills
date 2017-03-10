package com.josenaves.pills.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}

package com.vn.chotot.configuration;

import java.util.Properties;

import static com.vn.chotot.helper.FileHelper.getProperties;

public class WaitTime {

    private static final Properties prop = getProperties("wait");
    public static final int minWaitTime = Integer.parseInt(prop.getProperty("minWaitTime"));
    public static final int maxWaitTime = Integer.parseInt(prop.getProperty("maxWaitTime"));
}

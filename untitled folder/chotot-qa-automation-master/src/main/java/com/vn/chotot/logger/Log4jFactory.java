package com.vn.chotot.logger;

import com.vn.chotot.exception.StepFailedException;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Log4jFactory {
    private static Log4jFactory log4jFactory;
    final Logger log = LogManager.getLogger(Log4jFactory.class);
    private final LinkedMap<Long, Integer> failedStorage = new LinkedMap<>();
    private final List<String> failedMessage = new ArrayList<>();

    private Log4jFactory() { }

    public static Log4jFactory instance() {
        if (log4jFactory == null) {
            log4jFactory = new Log4jFactory();
        }
        return log4jFactory;
    }

    public static Logger createClassLogger(Class<?> cls) {
        return LogManager.getLogger(cls);
    }

    public void logWarning(Throwable throwable) {
        boolean check = throwable instanceof Error;
        if (!check) log.warn(ExceptionUtils.getStackTrace(throwable));
        else log.error(ExceptionUtils.getStackTrace(throwable));
    }

    public void logFailedAndContinue(Throwable throwable) {
        instance().setFailedStorage();
        failedMessage.add(throwable.getMessage());
        log.error(ExceptionUtils.getStackTrace(throwable));
    }

    public void logFailedAndStop(Throwable throwable) {
        throw new StepFailedException(throwable);
    }

    private void setFailedStorage() {
        failedStorage.put(Thread.currentThread().getId(), 1);
    }

    public List<String> getFailedMessage() {
        return failedMessage;
    }

    public int getFailedStorage(Long l) {
        int countFailed = 0;
        if (failedStorage.containsKey(l)) countFailed = failedStorage.get(l);
        return countFailed;
    }

    public void removeFailedStorage() {
        failedMessage.clear();
        failedStorage.clear();
    }
}

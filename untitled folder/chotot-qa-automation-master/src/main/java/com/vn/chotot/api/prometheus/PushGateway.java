package com.vn.chotot.api.prometheus;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.vn.chotot.configuration.Constant.getStatusPrefixK8S;
import static com.vn.chotot.helper.Utils.getHostName;
import static com.vn.chotot.keywords.selenium.Utils.delayStep;

public class PushGateway {

    public static void pushExecutionTime(double executionTime) {
        CollectorRegistry registry = new CollectorRegistry();
        String job_name = "test_execution_time";
        String hostname = getHostName();
        hostname = hostname.replace(".", "_");
        String featureName = getStatusPrefixK8S().length() > 0 ? getStatusPrefixK8S() : "none";

        Map<String, String> groupingKey = new HashMap<String, String>();
        groupingKey.put("host_name", hostname);
        groupingKey.put("feature_name", featureName);

        Gauge message = Gauge.build().name(job_name).help("Total execution time for test suite").register(registry);
        message.set(executionTime);
        io.prometheus.client.exporter.PushGateway pg = new io.prometheus.client.exporter.PushGateway("10.60.3.253:9091");
        try {
            pg.pushAdd(registry, job_name, groupingKey);
            delayStep((int)Math.round(executionTime / 1000));
            pg.delete(job_name, groupingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

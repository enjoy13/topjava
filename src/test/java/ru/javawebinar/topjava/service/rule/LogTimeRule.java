package ru.javawebinar.topjava.service.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LogTimeRule implements TestRule {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String textLog = "Total test time: {} milliseconds, methodName: {}";
    private long startTime = 0L;
    private long endTime = 0L;
    private long totalTime = 0L;
    public static Map<String, Long> resultTestMap = new HashMap<>();

    private void beforeMethod() {
        startTime = System.currentTimeMillis();
    }

    private void afterMethod(Description description) {
        endTime = System.currentTimeMillis();
        totalTime = endTime - startTime;
        String methodName = description.getMethodName();
        resultTestMap.put(methodName, totalTime);
        log.info(textLog, totalTime, methodName);
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                beforeMethod();
                base.evaluate();
                afterMethod(description);
            }
        };
    }
}

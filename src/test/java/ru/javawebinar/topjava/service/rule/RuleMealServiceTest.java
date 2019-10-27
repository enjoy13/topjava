package ru.javawebinar.topjava.service.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.TreeMap;

public class RuleMealServiceTest implements TestRule {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String textLog = "Total test time: {} milliseconds, methodName: {}";
    public static Map<String, Long> methodInfoMap = new TreeMap<>();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                long startTest = System.currentTimeMillis();
                base.evaluate();
                long endTest = System.currentTimeMillis();
                long totalTimeTest = endTest - startTest;
                String methodName = description.getMethodName();
                methodInfoMap.put(methodName, totalTimeTest);
                log.info(textLog, totalTimeTest, methodName);
            }
        };
    }
}

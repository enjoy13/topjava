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
    private long startTest = 0;
    static Map<String, Long> methodInfoMap = new TreeMap<>();

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                startTest();
                base.evaluate();
                endTest(description);
            }
        };
    }

    private void startTest() {
        startTest = System.currentTimeMillis();
    }

    private void endTest(Description description) {
        long endTest = System.currentTimeMillis();
        long totalTimeTest = endTest - startTest;
        String methodName = description.getMethodName();
        methodInfoMap.put(methodName, totalTimeTest);
        log.info("Total test time: {} milliseconds, methodName: {}", totalTimeTest, methodName);
    }

    public static class RuleMealServiceTestClass implements TestRule {
        private final Logger log = LoggerFactory.getLogger(getClass());

        @Override
        public Statement apply(Statement base, Description description) {
            return new Statement() {
                @Override
                public void evaluate() throws Throwable {
                    base.evaluate();
                    RuleMealServiceTest.methodInfoMap.forEach((k, v) -> log.info("method name: {} - total time execute {} ms", k, v));
                }
            };
        }
    }
}

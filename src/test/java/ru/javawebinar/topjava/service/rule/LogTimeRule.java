package ru.javawebinar.topjava.service.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LogTimeRule implements TestRule {
    private static final Logger log = LoggerFactory.getLogger(LogTimeRule.class);
    private long startTime = 0L;
    private static Map<String, Long> resultTestMap = new HashMap<>();

    private LogTimeRule() {
    }

    public static LogTimeRule newInstance() {
        return new LogTimeRule();
    }

    private void beforeMethod() {
        startTime = System.currentTimeMillis();
    }

    private void afterMethod(Description description) {
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        String methodName = description.getMethodName();
        resultTestMap.put(methodName, totalTime);
        log.info("Total test time: {} milliseconds, methodName: {}", totalTime, methodName);
    }

    public static void createTotalTeble() {
        final StringBuilder text = new StringBuilder();
        String textFormat = "%n|    %-20s   |   %5d    |";
        String formattingChars = "+-----------------------------+-----------+";
        text.append("\n")
                .append(formattingChars)
                .append("\n")
                .append(String.format("|    %-20s   |   %5s    |%n", "Method name ", "Time"))
                .append(formattingChars);
        resultTestMap.forEach((k, v) -> text.append(String.format(textFormat, k, v)));
        text.append("\n")
                .append(formattingChars);
        log.info(String.valueOf(text));
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

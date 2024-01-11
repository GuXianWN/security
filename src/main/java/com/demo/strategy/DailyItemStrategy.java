package com.demo.strategy;

import java.util.List;

public interface DailyItemStrategy {
    List<?> getList(String sDate, String eDate);
}

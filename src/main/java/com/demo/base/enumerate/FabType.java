package com.demo.base.enumerate;

import cn.hutool.extra.spring.SpringUtil;
import com.demo.service.impl.g1.G1QTimeServiceImpl;
import com.demo.service.impl.g3.G3QTimeServiceImpl;
import com.demo.strategy.DailyItemStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum FabType {
    G1("G1", G1QTimeServiceImpl.class),
    G3("G3",G3QTimeServiceImpl .class);
    private final String value;
    private final Class<?> strategyService;
    private static final Map<FabType, Class<?>> STRATEGY_MAP = new HashMap<>();

    static {
        for (FabType fabType : FabType.values()) {
            STRATEGY_MAP.put(fabType, fabType.getStrategyService());
        }
    }

    public DailyItemStrategy getEnumStrategyService() {
        return (DailyItemStrategy) SpringUtil.getBean(STRATEGY_MAP.get(this));
    }
}

package com.demo.service.impl.g3;

import cn.hutool.core.collection.ListUtil;
import com.demo.base.enumerate.FabType;
import com.demo.entity.QTime;
import com.demo.service.QTimeService;
import com.demo.strategy.DailyItemStrategy;

import java.util.List;

public class G3QTimeServiceImpl implements DailyItemStrategy, QTimeService {
    @Override
    public List<QTime> getList(String sDate, String eDate) {
        return ListUtil.of(new QTime(FabType.G3));
    }
}

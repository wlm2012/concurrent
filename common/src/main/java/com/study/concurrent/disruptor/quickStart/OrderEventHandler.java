package com.study.concurrent.disruptor.quickStart;

import com.lmax.disruptor.EventHandler;
import com.study.concurrent.utils.ThreadUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(final OrderEvent orderEvent, final long l, final boolean b) throws Exception {
        ThreadUtils.sleep(1000);
        log.info(Thread.currentThread() + "  :" + orderEvent.getId() + "  :" + orderEvent.getAmount());
    }
}

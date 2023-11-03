package com.study.concurrent.disruptor.quickStart;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.math.BigDecimal;

public class OrderMain {

    public static void main(String[] args) {
        OrderEventFactory orderEventFactory = new OrderEventFactory();

        Disruptor<OrderEvent> disruptor = new Disruptor<>(orderEventFactory,
                1024 * 1024,
//                Thread.ofVirtual().factory(),
                Thread.ofPlatform().factory(),
                ProducerType.SINGLE,
                new BlockingWaitStrategy());

        disruptor.handleEventsWith(new OrderEventHandler());

        disruptor.start();

        RingBuffer<OrderEvent> ringBuffer = disruptor.getRingBuffer();

        for (int i = 0; i < 100; i++) {
            long sequence = ringBuffer.next();
            try {
                OrderEvent orderEvent = ringBuffer.get(sequence);
                orderEvent.setId(String.valueOf(i));
                orderEvent.setAmount(BigDecimal.valueOf(i));
            } finally {
                ringBuffer.publish(sequence);
            }
        }

        disruptor.shutdown();
    }
}

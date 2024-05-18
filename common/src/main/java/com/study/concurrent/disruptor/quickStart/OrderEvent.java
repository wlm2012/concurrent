package com.study.concurrent.disruptor.quickStart;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderEvent {

    private String id;

    private BigDecimal amount;
}

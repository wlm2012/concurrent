package com.study.concurrent.application.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class IdsRequest {

    private List<Long> ids;
}

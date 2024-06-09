package com.study.concurrent.dto.request;

import java.util.List;

public record WenXinRequest(
        List<WenXinMessagesRequest> messages) {

}

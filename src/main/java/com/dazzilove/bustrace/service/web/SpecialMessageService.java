package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.SpecialMessage;

import java.util.List;

public interface SpecialMessageService {
    void add(SpecialMessage specialMessage);
    List<SpecialMessage> getSpecialMessageList(String routeId);
}

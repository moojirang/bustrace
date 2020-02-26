package com.dazzilove.bustrace.service.web;

import com.dazzilove.bustrace.domain.SpecialMessage;
import com.dazzilove.bustrace.repository.SpecialMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SpecialMessageServiceImpl implements SpecialMessageService {

    @Autowired
    SpecialMessageRepository specialMessageRepository;

    @Override
    public void add(SpecialMessage specialMessage) {
        specialMessage.setId(UUID.randomUUID());
        specialMessage.setCreatedAt(LocalDateTime.now());
        specialMessageRepository.save(specialMessage);
    }

    @Override
    public List<SpecialMessage> getSpecialMessageList(String routeId) {
        List<SpecialMessage> specialMessageList = specialMessageRepository.findByRouteId(routeId);
        if (specialMessageList == null) {
            specialMessageList = new ArrayList<>();
        }
        return specialMessageList;
    }
}

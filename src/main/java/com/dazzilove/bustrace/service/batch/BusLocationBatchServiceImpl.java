package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.repository.BusLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BusLocationBatchServiceImpl implements BusLocationBatchService {
    @Autowired
    BusLocationRepository busLocationRepository;

    @Override
    public List<BusLocation> getBusLoactionsByCreatedAt(LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        return busLocationRepository.getBusLoactionsByCreatedAt(startDate, endDate);
    }
}

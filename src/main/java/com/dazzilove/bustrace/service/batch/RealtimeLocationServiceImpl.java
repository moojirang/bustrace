package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.domain.RealtimeLocation;
import com.dazzilove.bustrace.repository.RealtimeLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RealtimeLocationServiceImpl implements RealtimeLocationService {

    @Autowired
    RealtimeLocationRepository realtimeLocationRepository;

    @Autowired
    BusLocationBatchService busLocationBatchService;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void transFromBusLocationToRealTimeLocation() throws Exception {
        LocalDateTime startDate = LocalDateTime.now().minusMinutes(30);
        LocalDateTime endDate = LocalDateTime.now();
        List<BusLocation> busLocations = busLocationBatchService.getBusLoactionsByCreatedAt(startDate, endDate);

        busLocations.stream().forEach(busLocation -> {
            RealtimeLocation realtimeLocation = new RealtimeLocation();
            realtimeLocation.setId(busLocation.getId());
            realtimeLocation.setRouteId(busLocation.getRouteId());
            realtimeLocation.setStationId(busLocation.getStationId());
            realtimeLocation.setStationSeq(busLocation.getStationSeq());
            realtimeLocation.setEndBus(busLocation.getEndBus());
            realtimeLocation.setLowPlate(busLocation.getLowPlate());
            realtimeLocation.setPlateNo(busLocation.getPlateNo());
            realtimeLocation.setPlateType(busLocation.getPlateType());
            realtimeLocation.setRemainSeatCnt(busLocation.getRemainSeatCnt());
            realtimeLocation.setCreatedAt(busLocation.getCreatedAt());
            realtimeLocationRepository.save(realtimeLocation);
        });
    }

    @Override
    public void deletRealTimeLocationBeforeOneWeek() {
        LocalDateTime createdAt = LocalDateTime.now().minusDays(8);
        realtimeLocationRepository.deletRealTimeLocationBeforeOneWeek(createdAt);
    }
}

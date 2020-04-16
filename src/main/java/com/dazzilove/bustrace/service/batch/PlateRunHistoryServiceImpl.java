package com.dazzilove.bustrace.service.batch;

import com.dazzilove.bustrace.domain.PlateRunHistory;
import com.dazzilove.bustrace.domain.RealtimeLocation;
import com.dazzilove.bustrace.domain.TripPlan;
import com.dazzilove.bustrace.repository.PlateRunHistoryRepository;
import com.dazzilove.bustrace.service.web.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlateRunHistoryServiceImpl implements PlateRunHistoryService {

    @Autowired
    RealtimeLocationService realtimeLocationService;

    @Autowired
    RouteService routeService;

    @Autowired
    PlateRunHistoryRepository plateRunHistoryRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public void savePlateRunHistory(PlateRunHistory plateRunHistory) throws Exception {
        LocalDateTime startDate = getStartDate(plateRunHistory.getStartDate());
        LocalDateTime endDate = getEndDate(plateRunHistory.getEndDate());

        plateRunHistory.setStartDate(startDate);
        plateRunHistory.setEndDate(endDate);

        LocalDateTime targetDate = startDate;
        while (targetDate.isBefore(endDate)) {
            plateRunHistory.setCreatedAt(targetDate);
            savePlateRunHistoryOneDay(plateRunHistory);
            targetDate = targetDate.plusDays(1);
        }
    }

    private void savePlateRunHistoryOneDay(PlateRunHistory plateRunHistory) throws Exception {
        List<PlateRunHistory> resultPlateRunHistoryList = new ArrayList<>();

        String routeId = plateRunHistory.getRouteId();

        List<TripPlan> tripPlans = routeService.getTripPlans(routeId);
        for (TripPlan tripPlan: tripPlans) {
            RealtimeLocation param = new RealtimeLocation();
            param.setRouteId(tripPlan.getRouteId());
            param.setPlateNo(tripPlan.getPlateNo());
            param.setStartCreatedAt(getStartDate(plateRunHistory.getCreatedAt()));
            param.setEndCreatedAt(getEndDate(plateRunHistory.getCreatedAt()));

            List<RealtimeLocation> locations = realtimeLocationService.getLocationsForPlateAndSortByCreatedAtAsc(param);
            if (!locations.isEmpty() && locations.size() > 0) {
                RealtimeLocation realtimeLocation = locations.get(0);

                PlateRunHistory result = new PlateRunHistory();
                result.setId(UUID.randomUUID());
                result.setRouteId(realtimeLocation.getRouteId());
                result.setPlateNo(realtimeLocation.getPlateNo());
                result.setRunDay(getRunDay(realtimeLocation.getCreatedAt()));
                result.setCreatedAt(realtimeLocation.getCreatedAt());

                resultPlateRunHistoryList.add(result);
            }
        }

        List<PlateRunHistory> existsPlateRunHistoryList = plateRunHistoryRepository.findByRunDay(routeId, getRunDay(plateRunHistory.getStartDate()));
        plateRunHistoryRepository.deleteAll(existsPlateRunHistoryList);

        List<PlateRunHistory> solredResultPlateRunHistoryList = resultPlateRunHistoryList.stream().sorted((plate1, plate2) -> plate1.compareTo(plate2)).collect(Collectors.toList());
        int sortNumber = 1;
        for(PlateRunHistory temp: solredResultPlateRunHistoryList) {
            temp.setSortNumber(sortNumber++);
        }
        plateRunHistoryRepository.saveAll(solredResultPlateRunHistoryList);
    }

    private String getRunDay(LocalDateTime createdAt) {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    private LocalDateTime getStartDate(LocalDateTime startDate) {
        return LocalDateTime.of(startDate.getYear(),
                                startDate.getMonthValue(),
                                startDate.getDayOfMonth(),
                                5,
                                0,
                                0);
    }

    private LocalDateTime getEndDate(LocalDateTime endDate) {
        return LocalDateTime.of(endDate.getYear(),
                                endDate.getMonthValue(),
                                endDate.getDayOfMonth(),
                                23,
                                59,
                                59);
    }
}

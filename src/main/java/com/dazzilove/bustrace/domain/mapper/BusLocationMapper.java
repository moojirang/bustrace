package com.dazzilove.bustrace.domain.mapper;

import com.dazzilove.bustrace.domain.BusLocation;
import com.dazzilove.bustrace.service.ws.BusLocationList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusLocationMapper {

    BusLocation toEntity(BusLocationList vo);
}

package com.example.smartagenda.mapper;

import com.example.smartagenda.dto.LocationDto;
import com.example.smartagenda.enums.LocationType;
import com.example.smartagenda.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {
    public Location fromLocationDto(LocationDto locationDto){
        return new Location(locationDto.getName(),
                locationDto.getAddress(),
                LocationType.valueOf(locationDto.getType()),
                locationDto.isOffice());
    }

    public LocationDto toLocationDto(Location location){
        return new LocationDto(location.getName(),
                location.getAddress(),
                location.getType().toString(),
                location.isOffice());
    }
}

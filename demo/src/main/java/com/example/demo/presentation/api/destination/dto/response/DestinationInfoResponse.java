package com.example.demo.presentation.api.destination.dto.response;

import com.example.demo.domain.model.Destination;

import java.math.BigDecimal;

public record DestinationInfoResponse(
        String name,
        String description,
        BigDecimal longitude,
        BigDecimal latitude
) {
    public static DestinationInfoResponse of(Destination destination) {
        return new DestinationInfoResponse(
                destination.getName(),
                destination.getDescription(),
                BigDecimal.valueOf(destination.getPoint().getX()),
                BigDecimal.valueOf(destination.getPoint().getY())
        );
    }
}

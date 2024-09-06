package com.example.demo.service;

import com.example.demo.domain.model.Destination;
import com.example.demo.domain.repository.DestinationRepository;
import com.example.demo.presentation.api.destination.dto.response.DestinationInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    /**
     * params: OO도 OO시/군/구 || OO광역시/특별시 OO구
     * return: [Dto(이름, 설명, 위도, 경도)]
     */

    public List<DestinationInfoResponse> getDestinationsByCity(String ctPrvnName, String siGunGuNam) {
        List<Destination> destinations = destinationRepository.findAllByCtPrvnNameAndSiGunGuName(ctPrvnName, siGunGuNam);

        return destinations.stream()
                .map(DestinationInfoResponse::of)
                .toList();
    }

}

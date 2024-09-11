package com.example.demo.service;

import com.example.demo.domain.model.Destination;
import com.example.demo.domain.repository.DestinationRepository;
import com.example.demo.presentation.api.destination.dto.response.DestinationInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    /**
     * params: OO도 OO시/군/구 || OO광역시/특별시 OO구
     * return: [Dto(이름, 설명, 위도, 경도)]
     */

    public Page<DestinationInfoResponse> getDestinationsByCity(String ctPrvnName, String siGunGuNam, Pageable pageable) {
        Page<Destination> destinationsPage =
                destinationRepository.findAllByCtPrvnNameAndSiGunGuName(ctPrvnName, siGunGuNam, pageable);

        return destinationsPage.map(DestinationInfoResponse::of);
    }

}

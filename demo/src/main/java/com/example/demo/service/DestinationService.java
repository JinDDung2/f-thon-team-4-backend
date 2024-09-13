package com.example.demo.service;

import com.example.demo.domain.model.Destination;
import com.example.demo.domain.repository.DestinationRepository;
import com.example.demo.infrastructure.clova.http.ClovaRestClient;
import com.example.demo.infrastructure.clova.http.model.ClovaResponseModel;
import com.example.demo.presentation.api.destination.dto.response.DestinationInfoResponse;
import com.example.demo.service.data.DestinationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class DestinationService {

    private final DestinationRepository destinationRepository;

    private final ClovaRestClient clovaRestClient;

    private final ThreadPoolTaskExecutor asyncThreadPoolExecutor;

    /**
     * params: OO도 OO시/군/구 || OO광역시/특별시 OO구
     * return: [Dto(이름, 설명, 위도, 경도)]
     */

    @Transactional
    public Page<DestinationInfoResponse> getDestinationsByCity(String ctPrvnName, String siGunGuNam, Pageable pageable) {
        Page<Destination> destinationsPage =
                destinationRepository.findAllByCtPrvnNameAndSiGunGuName(ctPrvnName, siGunGuNam, pageable);

        List<Destination> destinationList = destinationsPage.getContent();

        List<CompletableFuture<DestinationResponse>> completableFutures = new ArrayList<>();
        for (Destination destination : destinationList) {
            if (destination.getDescription() == null || destination.getDescription().isEmpty()) {
                completableFutures.add(CompletableFuture.supplyAsync(() -> {
                    ClovaResponseModel response = clovaRestClient.execute(destination);
                    return new DestinationResponse(destination.getId(), response.getContent());
                }, asyncThreadPoolExecutor));
            }
        }

        List<DestinationResponse> result = new ArrayList<>();
        for (CompletableFuture<DestinationResponse> completableFuture : completableFutures) {
            result.add(completableFuture.join());
        }

        for (DestinationResponse destinationResponse : result) {
            destinationList.stream().filter(destination -> Objects.equals(destination.getId(), destinationResponse.id()))
                    .findFirst()
                    .ifPresent(destination -> {
                        destination.updateDescription(destinationResponse.description());
                    });
        }

        destinationRepository.saveAll(destinationList);
        return destinationsPage.map(DestinationInfoResponse::of);
    }
}

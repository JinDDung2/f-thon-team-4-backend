package com.example.demo.presentation.api.destination;

import com.example.demo.presentation.PagedApiResponse;
import com.example.demo.presentation.api.destination.dto.response.DestinationInfoResponse;
import com.example.demo.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping("/destinations")
    public PagedApiResponse<List<DestinationInfoResponse>> getDestinationsByCity(
            @RequestParam String ctPrvnName,
            @RequestParam String siGunGuNam,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        Page<DestinationInfoResponse> destinationsInfoPage =
                destinationService.getDestinationsByCity(ctPrvnName, siGunGuNam, pageable);

        return PagedApiResponse.ok(destinationsInfoPage.getContent(),
                destinationsInfoPage.getNumber(),
                destinationsInfoPage.getSize());
    }
}

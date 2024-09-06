package com.example.demo.presentation.api.destination;

import com.example.demo.presentation.ApiResponse;
import com.example.demo.presentation.api.destination.dto.request.GetDestinationRequest;
import com.example.demo.presentation.api.destination.dto.response.DestinationInfoResponse;
import com.example.demo.service.DestinationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DestinationController {

    private final DestinationService destinationService;

    @GetMapping("/destinations")
    public ApiResponse<List<DestinationInfoResponse>> getDestinationsByCity(
            @RequestBody GetDestinationRequest request
    ) {
        List<DestinationInfoResponse> responses = destinationService.getDestinationsByCity(request.ctPrvnName(), request.siGunGuNam());

        return ApiResponse.ok(responses);
    }
}

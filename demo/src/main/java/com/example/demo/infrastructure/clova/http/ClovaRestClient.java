package com.example.demo.infrastructure.clova.http;

import com.example.demo.domain.model.Destination;
import com.example.demo.infrastructure.clova.http.model.ClovaRequestModel;
import com.example.demo.infrastructure.clova.http.model.ClovaResponseModel;
import com.example.demo.infrastructure.clova.http.model.MessageModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ClovaRestClient {
    private final RestTemplate restTemplate;

    public ClovaRestClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ClovaResponseModel execute(Destination destination) {
        MessageModel messageModel = new MessageModel("user",  String.format("%s %s의 %s에 대해서 설명해주세요.",
                destination.getCtPrvnName(), destination.getSiGunGuName(), destination.getName()));

        List<MessageModel> messageModelList = new ArrayList<>();
        messageModelList.add(messageModel);

        ClovaRequestModel requestModel = new ClovaRequestModel(
                "0.8", "0", "256", "0.5", "5.0", new ArrayList<>(),
                true, "0", messageModelList
        );

        ResponseEntity<ClovaResponseModel> responseEntity = restTemplate.postForEntity("", requestModel, ClovaResponseModel.class);
        return responseEntity.getBody();
    }
}

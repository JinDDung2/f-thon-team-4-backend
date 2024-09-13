package com.example.demo.parse;

import com.example.demo.domain.model.Destination;
import com.example.demo.domain.repository.DestinationRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DestinationTest {

    @Autowired
    private DestinationRepository destinationRepository;

    @Test
    @DisplayName("국내 관광 명소 데이터를 넣는다.")
    void LegalCodeParse() {
        List<Destination> destinationList = new ArrayList<>();
        String filePath = "/Users/jinhyuck/Downloads/domestic_destination.csv";
        String splitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(splitBy);

                String name = values[4];  // POI_NM
                double longitude = Double.parseDouble(values[20]);  // LC_LO
                double latitude = Double.parseDouble(values[21]);   // LC_LA
                String ctPrvnName = values[10];  // CTPRVN_NM
                String siGunGuName = values[11]; // SIGNGU_NM

                Destination destination = Destination.builder()
                        .name(name)
                        .longitude(longitude)
                        .latitude(latitude)
                        .ctPrvnName(ctPrvnName)
                        .siGunGuName(siGunGuName)
                        .build();

                destinationList.add(destination);

                if (destinationList.size() == 1000) {
                    destinationRepository.saveAll(destinationList);
                    destinationList.clear();
                }
            }

            if (!destinationList.isEmpty()) {
                destinationRepository.saveAll(destinationList);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 용인시 수지구 -> 용인시
    @Test
    @DisplayName("시군구에 두 단어 이상이 있으면 하나의 단어로 바꾼다.")
    void sigungu_name_make_one_word(){
        // 레포에서 전부 다 갖고오기
        List<Destination> destinationList = destinationRepository.findAll();
        // sigungu.split(" ") -> size() > 1만 변경
        for (Destination destination : destinationList) {
            String[] sigungu = destination.getSiGunGuName().split(" ");
            if (sigungu.length > 1) {
                destination.extractCityName();
            }
        }

        destinationRepository.saveAll(destinationList);
    }

}

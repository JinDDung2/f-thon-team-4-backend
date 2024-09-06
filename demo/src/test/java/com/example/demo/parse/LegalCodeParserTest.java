package com.example.demo.parse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LegalCodeParserTest {

    @Test
    @DisplayName("법정동 코드를 시/도와 시/군/구 코드로 변환시킨다.")
    void LegalCodeParse(){
        String csvFile = "/Users/jinhyuck/Downloads/legal_code.csv";
        String line;
        String splitBy = ",";

        Map<String, String> legalCodeMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(splitBy);

                // 법정동코드 앞 5자리 추출
                String codePrefix = data[0].substring(0, 5);

                // 도/시(ctPrvnName) + 시/군/구(siGunGuName) (서울특별시 종로구)
                String legalName = data[2] + " " + data[3];

                legalCodeMap.putIfAbsent(codePrefix, legalName);
            }

            String outputFilePath = "/Users/jinhyuck/Desktop/legal_codes.json";
            writeMapToJsonFile(legalCodeMap, outputFilePath);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to write the map to a JSON file
    private static void writeMapToJsonFile(Map<String, String> map, String fileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Write the map as a JSON object into the file
            objectMapper.writeValue(new File(fileName), map);
            System.out.println("Map data successfully written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

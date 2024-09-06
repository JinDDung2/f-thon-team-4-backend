package com.example.demo.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Entity
@NoArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String name;
    private Point point;
    private String ctPrvnName;
    private String siGunGuName;
    private String description;

    @Builder
    public Destination(String name, double longitude, double latitude, String ctPrvnName, String siGunGuName, String description) {
        this.name = name;
        point = new Point(longitude, latitude);
        this.ctPrvnName = ctPrvnName;
        this.siGunGuName = siGunGuName;
        this.description = description;
    }

}

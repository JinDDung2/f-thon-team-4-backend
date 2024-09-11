package com.example.demo.domain.repository;

import com.example.demo.domain.model.Destination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Page<Destination> findAllByCtPrvnNameAndSiGunGuName(String ctPrvnName, String siGunGuNam, Pageable pageable);
}

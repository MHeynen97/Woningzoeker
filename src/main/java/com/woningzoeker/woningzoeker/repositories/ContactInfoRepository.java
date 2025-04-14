package com.woningzoeker.woningzoeker.repositories;

import com.woningzoeker.woningzoeker.models.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Integer> {
    List<ContactInfo> findByProfielId(long profielId);
    List<ContactInfo> findByGebruikerId(long gebruikerId);
}

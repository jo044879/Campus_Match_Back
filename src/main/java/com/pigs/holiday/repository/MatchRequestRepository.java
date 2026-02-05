package com.pigs.holiday.repository;

import com.pigs.holiday.domain.MatchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRequestRepository extends JpaRepository<MatchRequest, Long> {

}

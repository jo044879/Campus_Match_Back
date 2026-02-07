package com.pigs.holiday.repository;

import com.pigs.holiday.domain.MatchPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchPostRepository extends JpaRepository<MatchPost, Long> {
}

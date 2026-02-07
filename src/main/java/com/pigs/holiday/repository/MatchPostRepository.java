package com.pigs.holiday.repository;

import com.pigs.holiday.domain.MatchPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchPostRepository extends JpaRepository<MatchPost, Long> {
    Optional<List<MatchPost>> findByDeleted(Boolean deleted);
}

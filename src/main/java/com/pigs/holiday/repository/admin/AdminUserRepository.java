package com.pigs.holiday.repository.admin;

import com.pigs.holiday.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUserRepository extends JpaRepository<Club, Long> {
}

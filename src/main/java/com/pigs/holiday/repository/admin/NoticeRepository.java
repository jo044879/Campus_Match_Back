package com.pigs.holiday.repository.admin;

import com.pigs.holiday.domain.admin.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}

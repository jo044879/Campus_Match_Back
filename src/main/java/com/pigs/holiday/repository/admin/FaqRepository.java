package com.pigs.holiday.repository.admin;

import com.pigs.holiday.domain.admin.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
}

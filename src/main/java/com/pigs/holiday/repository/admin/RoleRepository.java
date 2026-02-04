package com.pigs.holiday.repository.admin;

import com.pigs.holiday.domain.admin.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}

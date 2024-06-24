package com.codeElevate.ServiceBookingSystem.repository.club;

import com.codeElevate.ServiceBookingSystem.entity.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}

package com.codeElevate.ServiceBookingSystem.repository.club;

import com.codeElevate.ServiceBookingSystem.entity.club.ClubInscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubInscriptionRepository extends JpaRepository<ClubInscription, Long> {
    List<ClubInscription> findAllByClubId(Long clubId);
}

package com.codeElevate.ServiceBookingSystem.services.company;

import com.codeElevate.ServiceBookingSystem.dto.club.ClubInscriptionDTO;
import com.codeElevate.ServiceBookingSystem.entity.User;
import com.codeElevate.ServiceBookingSystem.entity.club.Club;
import com.codeElevate.ServiceBookingSystem.entity.club.ClubInscription;
import com.codeElevate.ServiceBookingSystem.repository.UserRepository;
import com.codeElevate.ServiceBookingSystem.repository.club.ClubInscriptionRepository;
import com.codeElevate.ServiceBookingSystem.repository.club.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;

    @Autowired
    private ClubInscriptionRepository clubInscriptionRepository;

    @Override
    public boolean postClubUser(ClubInscriptionDTO clubInscriptionDTO){
        Optional<User> optionalUser = userRepository.findById(clubInscriptionDTO.getIdUser());
        if(optionalUser.isPresent()){
            Optional<Club> optionalClub = clubRepository.findById(clubInscriptionDTO.getIdClub());
            if(optionalClub.isPresent()){
                ClubInscription clubInscription = new ClubInscription();
                clubInscription.setUser(optionalUser.get());
                clubInscription.setClub(optionalClub.get());
                clubInscription.setDateInscription(clubInscriptionDTO.getDateInscription());
                clubInscriptionRepository.save(clubInscription);
                return true;
            }
        }
        return false;
    }

    public List<ClubInscriptionDTO> getClubInscriptions(){
        return clubInscriptionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(ClubInscription::getDateInscription).reversed())
                .map(ClubInscription::getDto).collect(Collectors.toList());
    }



}

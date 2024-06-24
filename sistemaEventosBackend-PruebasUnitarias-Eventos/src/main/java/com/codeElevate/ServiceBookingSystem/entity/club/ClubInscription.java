package com.codeElevate.ServiceBookingSystem.entity.club;

import com.codeElevate.ServiceBookingSystem.dto.club.ClubInscriptionDTO;
import com.codeElevate.ServiceBookingSystem.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Table(name = "ClubInscription")
@Data
public class ClubInscription {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idClub")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Club club;

    private Date dateInscription;

    public ClubInscriptionDTO getDto(){
        ClubInscriptionDTO clubInscriptionDTO = new ClubInscriptionDTO();
        clubInscriptionDTO.setId(id);
        clubInscriptionDTO.setIdUser(user.getId());
        clubInscriptionDTO.setIdClub(club.getId());
        clubInscriptionDTO.setDateInscription(dateInscription);
        return clubInscriptionDTO;
    }


}

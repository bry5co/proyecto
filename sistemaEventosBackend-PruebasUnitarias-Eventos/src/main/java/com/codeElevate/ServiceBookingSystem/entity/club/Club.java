package com.codeElevate.ServiceBookingSystem.entity.club;

import com.codeElevate.ServiceBookingSystem.dto.club.ClubDTO;
import com.codeElevate.ServiceBookingSystem.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "Club")
@Data
public class Club {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idAdminUser")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private String name;

    private String description;

    public ClubDTO getClubDto(){
        ClubDTO clubDTO = new ClubDTO();
        clubDTO.setId(id);
        clubDTO.setIdUserAdmin(user.getId());
        clubDTO.setName(name);
        clubDTO.setDescription(description);
        return clubDTO;
    }
}

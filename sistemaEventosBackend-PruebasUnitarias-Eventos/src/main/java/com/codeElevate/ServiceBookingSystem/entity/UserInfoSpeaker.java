package com.codeElevate.ServiceBookingSystem.entity;

import com.codeElevate.ServiceBookingSystem.dto.UserInfoSpeakerDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
@Data
public class UserInfoSpeaker {

        @Id
        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "idUser")
        @OnDelete(action = OnDeleteAction.CASCADE)
        private User user;

        private Date birthDate;

        private String province;

        private String city;

        private String university;

        public UserInfoSpeakerDTO getDto(){
            UserInfoSpeakerDTO userInfoSpeakerDTO = new UserInfoSpeakerDTO(user.getEmail(), birthDate, province, city, university);
            return userInfoSpeakerDTO;
        }

}

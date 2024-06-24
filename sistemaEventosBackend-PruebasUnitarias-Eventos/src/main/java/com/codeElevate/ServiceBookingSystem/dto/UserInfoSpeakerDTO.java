package com.codeElevate.ServiceBookingSystem.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoSpeakerDTO {

        private String idUser;

        private Date birthDate;

        private String province;

        private String city;

        private String university;

        public UserInfoSpeakerDTO(String idUser, Date birthDate, String province, String city, String university) {
            this.idUser = idUser;
            this.birthDate = birthDate;
            this.province = province;
            this.city = city;
            this.university = university;
        }
}

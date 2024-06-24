package com.codeElevate.ServiceBookingSystem.dto.event;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AdDTO {

    private Long id;

    private String serviceName;

    private String description;

    private Double price;

    private MultipartFile img;
    private byte[] returnedImg;

    private Long userId;

    private String companyName;

    public boolean isEmpty() {
        return serviceName == null || serviceName.isEmpty() ||
                description == null || description.isEmpty() ||
                img == null || img.isEmpty() ||  // Esto puede variar dependiendo de la implementación de MultipartFile
                price == null;  // Otra posible validación dependiendo de tus requisitos
    }
}

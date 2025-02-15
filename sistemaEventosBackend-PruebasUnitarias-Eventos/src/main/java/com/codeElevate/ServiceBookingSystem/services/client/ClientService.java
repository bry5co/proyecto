package com.codeElevate.ServiceBookingSystem.services.client;

import com.codeElevate.ServiceBookingSystem.dto.event.AdDTO;
import com.codeElevate.ServiceBookingSystem.dto.event.AdDetailsForClientDTO;
import com.codeElevate.ServiceBookingSystem.dto.event.ReservationDTO;
import com.codeElevate.ServiceBookingSystem.dto.event.ReviewDTO;

import java.util.List;

public interface ClientService {

    List<AdDTO> getAllAds();

    List<AdDTO> searchAdByName(String name);

    boolean bookService(ReservationDTO reservationDTO);

    AdDetailsForClientDTO getAdDetailsByAdId(Long adId);

    List<ReservationDTO> getAllBookingsByUserId(Long userId);

    Boolean giveReview(ReviewDTO reviewDTO);
}

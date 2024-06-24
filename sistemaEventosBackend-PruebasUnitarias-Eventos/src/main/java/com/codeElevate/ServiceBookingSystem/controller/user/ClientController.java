package com.codeElevate.ServiceBookingSystem.controller.user;

import com.codeElevate.ServiceBookingSystem.dto.event.ReservationDTO;
import com.codeElevate.ServiceBookingSystem.dto.event.ReviewDTO;
import com.codeElevate.ServiceBookingSystem.dto.SignupRequestDTO;
import com.codeElevate.ServiceBookingSystem.dto.UserDto;
import com.codeElevate.ServiceBookingSystem.services.authentication.AuthService;
import com.codeElevate.ServiceBookingSystem.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/ads")
    public ResponseEntity<?> getAllAds(){
        return ResponseEntity.ok(clientService.getAllAds());
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchAdByService(@PathVariable String name){
        return ResponseEntity.ok(clientService.searchAdByName(name));
    }

    @PostMapping("/book-service")
    public ResponseEntity<?> bookService(@RequestBody ReservationDTO reservationDTO){
        boolean success = clientService.bookService(reservationDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/ad/{adId}")
    public ResponseEntity<?> getAdDetailsByAdId(@PathVariable Long adId){
        return ResponseEntity.ok(clientService.getAdDetailsByAdId(adId));
    }

    @GetMapping("/my-bookings/{userId}")
    public ResponseEntity<?> getAllBookingsByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(clientService.getAllBookingsByUserId(userId));
    }

    @PostMapping("/review")
    public ResponseEntity<?> giveReview(@RequestBody ReviewDTO reviewDTO){
        Boolean success = clientService.giveReview(reviewDTO);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequestDTO requestDTO) {
        if (requestDTO == null || areFieldsNullOrEmpty(requestDTO)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required");
        }

        UserDto createdUser = authService.signupClient(requestDTO);
        return ResponseEntity.ok(createdUser);

    }

    private boolean areFieldsNullOrEmpty(SignupRequestDTO requestDTO) {
        return requestDTO.getEmail() == null || requestDTO.getPassword() == null ||
                requestDTO.getName() == null || requestDTO.getLastname() == null ||
                requestDTO.getPhone() == null || requestDTO.getEmail().isEmpty() ||
                requestDTO.getPassword().isEmpty() || requestDTO.getName().isEmpty() ||
                requestDTO.getLastname().isEmpty() || requestDTO.getPhone().isEmpty();
    }
}

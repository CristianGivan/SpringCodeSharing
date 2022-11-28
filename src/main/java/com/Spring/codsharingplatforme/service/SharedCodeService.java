package com.Spring.codsharingplatforme.service;

import com.Spring.codsharingplatforme.DTO.SharedCodeResponseDTO;
import com.Spring.codsharingplatforme.model.SharedCode;
import com.Spring.codsharingplatforme.repository.SharedCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class SharedCodeService {
    private SharedCodeRepository sharedCodeRepository;

    @Autowired
    public SharedCodeService(SharedCodeRepository sharedCodeRepository) {
        this.sharedCodeRepository = sharedCodeRepository;
    }

    public SharedCode addCode(SharedCode sharedCode) {
        sharedCode.setCreatedDate(LocalDateTime.now());
        return sharedCodeRepository.save(sharedCode);
    }

    public SharedCodeResponseDTO getSharedCode(Long id) {//todo o sa mai verific la views ca nu merge

        SharedCodeResponseDTO sharedCodeResponseDTO;

        SharedCode sharedCode = sharedCodeRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found"));
        if (sharedCode.isPublic()){
            sharedCodeResponseDTO = new SharedCodeResponseDTO(sharedCode, 0L,true);
            return sharedCodeResponseDTO;
        }else {
            long diff = sharedCode.getAllowViewTime();
            if (sharedCode.isExpired()) {
                sharedCodeRepository.delete(sharedCode);
                throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Shared code cannot be viewed anymore");
            }
            sharedCode.setViews(sharedCode.getViews() - 1);
            sharedCodeResponseDTO = new SharedCodeResponseDTO(sharedCode, sharedCode.getTimeLeft(), false);
            sharedCodeRepository.save(sharedCode);
            return sharedCodeResponseDTO;
        }
}
}

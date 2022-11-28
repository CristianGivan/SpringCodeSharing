package com.Spring.codsharingplatforme.controller;

import com.Spring.codsharingplatforme.DTO.SharedCodeResponseDTO;
import com.Spring.codsharingplatforme.model.SharedCode;
import com.Spring.codsharingplatforme.service.SharedCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/code")
public class SharedCodeController {
    private SharedCodeService sharedCodeService;

    @Autowired
    public SharedCodeController(SharedCodeService sharedCodeService) {
        this.sharedCodeService = sharedCodeService;
    }
    @PostMapping("/add")
    public SharedCode addCode(@RequestBody SharedCode sharedCode){
        return sharedCodeService.addCode(sharedCode);
    }
    @GetMapping("/{id}")
    public SharedCodeResponseDTO getSharedCode(@PathVariable Long id){
        return sharedCodeService.getSharedCode(id);
    }

}

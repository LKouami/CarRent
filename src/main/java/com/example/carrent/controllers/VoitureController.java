package com.example.carrent.controllers;

import com.example.carrent.entities.Voiture;
import com.example.carrent.repositories.VoitureRepository;
import com.example.carrent.utils.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class VoitureController {
    private VoitureRepository voitureRepository;

    // Endpoint pour récupérer tous les employés
    @GetMapping("/voitures")
    public List<Voiture> getAllVoitures() {
        return voitureRepository.findAll();
    }

    @PostMapping(path = "/voitures")
    public Voiture createVoiture(@RequestBody Voiture voiture) {
        return voitureRepository.save(voiture);
    }

    // Endpoint pour récupérer un employé par son ID
    @GetMapping("/voitures/{id}")
    public Optional<Voiture> getVoitureById(@PathVariable(value = "id") Long voitureId) {
        return voitureRepository.findById(voitureId);
    }

    // Endpoint pour mettre à jour un employé
    @PutMapping("/voitures/{id}")
    public Voiture updateVoiture(@PathVariable(value = "id") Long voitureId, @RequestBody Voiture voitureDetails) {
        Voiture voiture = voitureRepository.findById(voitureId).orElseThrow(() -> new ResourceNotFoundException("Voiture", "id", voitureId));
        voiture.setDateAcquisition(voitureDetails.getDateAcquisition());
        voiture.setImmatriculation(voitureDetails.getImmatriculation());
        voiture.setMarque(voitureDetails.getMarque());
        Voiture updatedVoiture = voitureRepository.save(voiture);
        return updatedVoiture;
    }

    // Endpoint pour supprimer un employé
    @DeleteMapping("/voitures/{id}")
    public ResponseEntity<?> deleteVoiture(@PathVariable(value = "id") Long voitureId) {
        Voiture voiture = voitureRepository.findById(voitureId).orElseThrow(() -> new ResourceNotFoundException("Voiture", "id", voitureId));
        voitureRepository.delete(voiture);
        return ResponseEntity.ok().build();
    }


}

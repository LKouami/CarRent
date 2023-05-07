package com.example.carrent.controllers;

import com.example.carrent.entities.*;
import com.example.carrent.repositories.ClientRepository;
import com.example.carrent.repositories.EmployeRepository;
import com.example.carrent.repositories.LocationRepository;
import com.example.carrent.repositories.VoitureRepository;
import com.example.carrent.utils.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class LocationController {
    private LocationRepository locationRepository;
    private EmployeRepository employeRepository;
    private ClientRepository clientRepository;
    private VoitureRepository voitureRepository;
    // Endpoint pour récupérer tous les employés
    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @PostMapping("/locations")
    public Location createLocation(@RequestBody Location location) {
        return locationRepository.save(location);
    }

    // Endpoint pour récupérer un employé par son ID
    @GetMapping("/locations/{id}")
    public Optional<Location> getLocationById(@PathVariable(value = "id") Long locationId) {
        return locationRepository.findById(locationId);
    }

    // Endpoint pour mettre à jour un employé
    @PutMapping("/locations/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
        return locationRepository.findById(id)
                .map(location -> {
                    location.setEmploye(updatedLocation.getEmploye());
                    location.setVoiture(updatedLocation.getVoiture());
                    location.setClient(updatedLocation.getClient());
                    location.setDateDebut(updatedLocation.getDateDebut());
                    location.setDuree(updatedLocation.getDuree());
                    Location savedLocation = locationRepository.save(location);
                    return ResponseEntity.ok(savedLocation);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer un employé
    @DeleteMapping("/locations/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable(value = "id") Long locationId) {
        Location location = locationRepository.findById(locationId).orElseThrow(() -> new ResourceNotFoundException("Location", "id", locationId));
        locationRepository.delete(location);
        return ResponseEntity.ok().build();
    }


}

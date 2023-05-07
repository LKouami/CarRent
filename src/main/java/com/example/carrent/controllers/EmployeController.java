package com.example.carrent.controllers;

import com.example.carrent.entities.Employe;
import com.example.carrent.repositories.EmployeRepository;
import com.example.carrent.utils.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class EmployeController {
    private EmployeRepository employeRepository;

    // Endpoint pour récupérer tous les employés
    @GetMapping("/employes")
    public List<Employe> getAllEmployes() {
        return employeRepository.findAll();
    }

    @PostMapping(path = "/employes")
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeRepository.save(employe);
    }

    // Endpoint pour récupérer un employé par son ID
    @GetMapping("/employes/{id}")
    public Optional<Employe> getEmployeById(@PathVariable(value = "id") Long employeId) {
        return employeRepository.findById(employeId);
    }

    // Endpoint pour mettre à jour un employé
    @PutMapping("/employes/{id}")
    public Employe updateEmploye(@PathVariable(value = "id") Long employeId, @RequestBody Employe employeDetails) {
        Employe employe = employeRepository.findById(employeId).orElseThrow(() -> new ResourceNotFoundException("Employe", "id", employeId));
        employe.setNom(employeDetails.getNom());
        employe.setPoste(employeDetails.getPoste());
        employe.setAge(employeDetails.getAge());
        employe.setAdresse(employeDetails.getAdresse());
        Employe updatedEmploye = employeRepository.save(employe);
        return updatedEmploye;
    }

    // Endpoint pour supprimer un employé
    @DeleteMapping("/employes/{id}")
    public ResponseEntity<?> deleteEmploye(@PathVariable(value = "id") Long employeId) {
        Employe employe = employeRepository.findById(employeId).orElseThrow(() -> new ResourceNotFoundException("Employe", "id", employeId));
        employeRepository.delete(employe);
        return ResponseEntity.ok().build();
    }


}

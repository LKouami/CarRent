package com.example.carrent.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private Voiture voiture;

    @ManyToOne
    private Client client;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    private int duree;

    @Column(nullable = false)
    private LocalDate dateLocation;

    @PrePersist
    public void setDateLocation() {
        this.dateLocation = LocalDate.now();
    }

}

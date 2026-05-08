package com.springapp.F1manager.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "drivers")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {
    //Elsődlges kulcs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private int carNumber;
    private String nationality;
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team; // Ez köti össze a két táblát
}

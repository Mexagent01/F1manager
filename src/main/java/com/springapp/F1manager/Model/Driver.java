package com.springapp.F1manager.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "drivers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Driver {

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
    private Team team;
}
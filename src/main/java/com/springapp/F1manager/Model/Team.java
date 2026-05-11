package com.springapp.F1manager.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "teams")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    //Elsődleges kulcs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //A csapat tábla adatai
    @Column(nullable = false)
    private String name;
    private String country;
    private Integer foundedYear;

    @OneToMany(mappedBy = "team")
    private List<Driver> drivers;
}
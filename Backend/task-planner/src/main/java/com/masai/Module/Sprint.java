package com.masai.Module;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToOne
    private AppUser sprintCreator;

    @OneToMany
    List<AppUser> users = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();
}

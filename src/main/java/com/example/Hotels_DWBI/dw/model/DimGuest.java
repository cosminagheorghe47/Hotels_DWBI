package com.example.Hotels_DWBI.dw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "DIM_GUEST")
@Data
public class DimGuest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "guest_key", nullable = false)
    private Integer guestKey;

    @Column(name = "guest_id_oltp", nullable = false)
    private Integer guestIdOltp;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "nationality")
    private String nationality;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "email")
    private String email;
}

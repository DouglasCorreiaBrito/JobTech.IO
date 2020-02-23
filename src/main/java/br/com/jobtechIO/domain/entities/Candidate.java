package br.com.jobtechIO.domain.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate extends BaseEntity {

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 250)
    private String CPF;

    @Column(nullable = false, length = 250)
    private String email;

    @Column(nullable = false, length = 250)
    private String telephone;

    @Column(nullable = false, length = 250)
    private String endereco;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Transient
    private List<Skills> skillsList;

}
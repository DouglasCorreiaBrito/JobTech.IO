package br.com.jobtechIO.domain.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

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

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.MERGE
            })
    @JoinTable(name = "candidateSkill",
            joinColumns = { @JoinColumn(name = "candidateId") },
            inverseJoinColumns = { @JoinColumn(name = "skillId") })
    private List<Skill> skills;

}
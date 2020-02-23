package br.com.jobtechIO.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skills extends BaseEntity {

    @Column(nullable = false, length = 250)
    private String description;

    @ManyToOne
    @JoinColumn(name = "idCandidate", nullable = false)
    private Candidate idCandidate;
}
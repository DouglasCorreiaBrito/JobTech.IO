package br.com.jobtechIO.domain.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.jobtechIO.domain.enumerations.VacantStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobApplication extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "idjobOpportunity", nullable = false)
    public JobOpportunity jobOpportunity;
    
    @ManyToOne
    @JoinColumn(name = "idcandidate", nullable = false)
    public Candidate candidate;

    @Enumerated(EnumType.STRING)
    private VacantStatus status;
    
   
}
package br.com.jobtechIO.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Deficiency extends BaseEntity {

    @Column(nullable = false, length = 250)
    private String description;

}

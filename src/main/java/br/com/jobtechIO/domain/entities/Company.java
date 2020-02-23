package br.com.jobtechIO.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Company extends BaseEntity {

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false, length = 250)
    private String telephone;

    @Column(nullable = false, length = 250)
    private String email;

    @Column(nullable = false, length = 250)
    private String adres;

    @Column(nullable = false, length = 18)
    private String cnpj;

}

package br.com.jobtechIO.domain.dto.response;

import java.time.LocalDate;
import java.util.List;

import br.com.jobtechIO.domain.entities.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateResponse {

	private Integer id;

	private String name;

	private String CPF;

	private String email;

	private String telephone;

	private String address;

	private LocalDate birthdate;

	private List<Skill> skills;

}
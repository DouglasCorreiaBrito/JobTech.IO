package br.com.jobtechIO.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyResponse {

	private Integer id;

	private String name;

	private String telephone;

	private String email;

	private String address;

	private String cnpj;
}
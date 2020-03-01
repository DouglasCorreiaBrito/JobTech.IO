package br.com.jobtechIO.domain.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillRequest {

	@NotEmpty(message = "title is required")
	@NotBlank(message = "title is not blank")
	@Size(max = 200)
	private String description;

}
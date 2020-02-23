package br.com.jobtechIO.domain.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeficiencyCreateRequest  {

    @NotEmpty(message = "description is required")
    @NotBlank(message = "description is not blank")
    @Size(max = 200)
    private String description;

}

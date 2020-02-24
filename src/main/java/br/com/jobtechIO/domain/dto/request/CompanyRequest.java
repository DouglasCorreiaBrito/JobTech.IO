package br.com.jobtechIO.domain.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import br.com.jobtechIO.domain.validators.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyRequest {


    @NotEmpty(message = "name is required")
    @NotBlank(message = "name is not blank")
    @Size(max = 200)
    private String name;

    @Phone(message = "the number received is not a phone")
    private String telephone;

    @Email(message = "the string received is not a email")
    private String email;

    @NotEmpty(message = "name is required")
    @NotBlank(message = "name is not blank")
    @Size(max = 200)
    private String address;

    @CNPJ
    private String cnpj;
}
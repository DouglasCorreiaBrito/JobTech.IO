package br.com.jobtechIO.domain.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneValidator.class)
@Documented
public @interface Phone {

	String message() default "Telefone inválido, deve ter entre 8 e 11 digítos numéricos.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
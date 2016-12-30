package com.cairone.appexample.dtos.validators;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.cairone.appexample.dtos.PrestamoFrmDto;

@Component
public class PrestamoFrmDtoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return (PrestamoFrmDto.class).isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		PrestamoFrmDto frmDto = (PrestamoFrmDto) target;
		
		ValidationUtils.rejectIfEmpty(errors, "prestamo", "required", new Object[] {"IMPORTE DEL PRESTAMO"});
		ValidationUtils.rejectIfEmpty(errors, "tipoTasa", "required", new Object[] {"TIPO DE TASA"});
		ValidationUtils.rejectIfEmpty(errors, "tasa", "required", new Object[] {"TASA"});
		ValidationUtils.rejectIfEmpty(errors, "cuotas", "required", new Object[] {"CANTIDAD DE CUOTAS"});
		ValidationUtils.rejectIfEmpty(errors, "alicuota", "required", new Object[] {"ALICUOTA DE IVA"});
		
		if(!errors.hasFieldErrors("prestamo") && (frmDto.getPrestamo().compareTo(BigDecimal.valueOf(10000)) < 0 || frmDto.getPrestamo().compareTo(BigDecimal.valueOf(50000)) > 0)) {
			errors.rejectValue("prestamo", "invalid", new Object[] {"IMPORTE DEL PRESTAMO"}, null);
		}
		
		if(!errors.hasFieldErrors("tasa") && (frmDto.getTasa().compareTo(BigDecimal.valueOf(10)) < 0 || frmDto.getTasa().compareTo(BigDecimal.valueOf(30)) > 0)) {
			errors.rejectValue("tasa", "invalid", new Object[] {"TASA"}, null);
		}
		
		if(!errors.hasFieldErrors("cuotas") && frmDto.getCuotas() < 1) {
			errors.rejectValue("cuotas", "invalid", new Object[] {"CANTIDAD DE CUOTAS"}, null);
		}
	}
}

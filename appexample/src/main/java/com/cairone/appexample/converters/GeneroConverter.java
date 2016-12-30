package com.cairone.appexample.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.cairone.appexample.enums.GeneroEnum;

@Converter(autoApply = true)
public class GeneroConverter implements AttributeConverter<GeneroEnum, Character> {

	@Override
	public Character convertToDatabaseColumn(GeneroEnum attribute) {
		return attribute.getValor();
	}

	@Override
	public GeneroEnum convertToEntityAttribute(Character dbData) {
		switch(dbData) {
		case 'F':
			return GeneroEnum.FEMENINO;
		case 'M':
		default:
			return GeneroEnum.MASCULINO;
		}
	}

}

package com.cairone.appexample.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cairone.appexample.dtos.PrestamoCuotaDto;
import com.cairone.appexample.dtos.PrestamoFrmDto;
import com.cairone.appexample.dtos.validators.PrestamoFrmDtoValidator;
import com.cairone.appexample.processes.DesarrolloPrestamoProcess;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

@Controller
public class PrestamoCtrl 
{
	@Autowired private DesarrolloPrestamoProcess desarrolloPrestamoProcess = null;
	@Autowired private PrestamoFrmDtoValidator prestamoFrmDtoValidator = null;

	@Autowired private HazelcastInstance hazelcastInstance = null;
	
	@InitBinder("prestamoFrmDto")
	public void initBinderPlantaFrmDto(WebDataBinder binder) {
		binder.setValidator(prestamoFrmDtoValidator);
	}
	
	@RequestMapping(value = "/prestamo", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public String datosIniciales(PrestamoFrmDto prestamoFrmDto, ModelMap modelo) {
		
		prestamoFrmDto.setPrestamo(BigDecimal.valueOf(15000));
		prestamoFrmDto.setTipoTasa("E");
		prestamoFrmDto.setTasa(BigDecimal.valueOf(15));
		prestamoFrmDto.setCuotas(12);
		prestamoFrmDto.setAlicuota(BigDecimal.valueOf(21));
		
		return "prestamoFrm";
	}
	
	@RequestMapping(value = "/prestamo", method = RequestMethod.POST)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public String desarrollar(@Valid PrestamoFrmDto prestamoFrmDto, BindingResult bindingResult, ModelMap modelo) {
		
		if(bindingResult.hasErrors()) {
			return "prestamoFrm";
		}
		
		Map<Long, Iterable<PrestamoCuotaDto>> map = hazelcastInstance.getMap("desarrollos");
		
		IdGenerator idGenerator = hazelcastInstance.getIdGenerator("newId");
		long key = idGenerator.newId();
		
		Iterable<PrestamoCuotaDto> prestamoCuotaDtos = desarrolloPrestamoProcess.desarrollar(prestamoFrmDto);
		map.put(key, prestamoCuotaDtos);
		
		return String.format("redirect:/amortizaciones/%s", key);
	}
	

	@RequestMapping(value = "/amortizaciones/{KEY}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public String mostrarTablaAmortizacion(@PathVariable("KEY") Long key, ModelMap modelo) {
		
		Map<Long, Iterable<PrestamoCuotaDto>> map = hazelcastInstance.getMap("desarrollos");
		Iterable<PrestamoCuotaDto> prestamoCuotaDtos = map.get(key);
		
		modelo.addAttribute("cuotas", prestamoCuotaDtos);
		
		return "prestamoAmortizaciones";
	}
	
	@RequestMapping(value = "/amortizaciones/listar", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public String amortizaciones(ModelMap modelo) {
		
		Map<Long, Iterable<PrestamoCuotaDto>> map = hazelcastInstance.getMap("desarrollos");
		List<Long> amortizaciones = new ArrayList<Long>();
		
		for(Map.Entry<Long, Iterable<PrestamoCuotaDto>> entry : map.entrySet()) {
			amortizaciones.add(entry.getKey());
		}
		
		modelo.addAttribute("amortizaciones", amortizaciones);
		
		return "listarAmortizaciones";
	}
}

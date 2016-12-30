package com.cairone.appexample.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cairone.appexample.dtos.PrestamoCuotaDto;
import com.cairone.appexample.dtos.PrestamoFrmDto;
import com.cairone.appexample.dtos.ResultadoDto;
import com.cairone.appexample.dtos.validators.PrestamoFrmDtoValidator;
import com.cairone.appexample.processes.DesarrolloPrestamoProcess;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IdGenerator;

@RestController
public class PrestamoApiCtrl {

	@Autowired private DesarrolloPrestamoProcess desarrolloPrestamoProcess = null;
	@Autowired private PrestamoFrmDtoValidator prestamoFrmDtoValidator = null;

	@Autowired private HazelcastInstance hazelcastInstance = null;

	@InitBinder("prestamoFrmDto")
	public void initBinderPlantaFrmDto(WebDataBinder binder) {
		binder.setValidator(prestamoFrmDtoValidator);
	}

	@RequestMapping(value = "/api/prestamo", method = RequestMethod.POST)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public ResultadoDto desarrollar(@Valid @RequestBody PrestamoFrmDto prestamoFrmDto, BindingResult bindingResult, HttpServletResponse response) {
		
		if(bindingResult.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
		
		Map<Long, Iterable<PrestamoCuotaDto>> map = hazelcastInstance.getMap("desarrollos");
		
		IdGenerator idGenerator = hazelcastInstance.getIdGenerator("newId");
		long key = idGenerator.newId();
		
		Iterable<PrestamoCuotaDto> prestamoCuotaDtos = desarrolloPrestamoProcess.desarrollar(prestamoFrmDto);
		map.put(key, prestamoCuotaDtos);
		
		return new ResultadoDto(key, prestamoCuotaDtos);
	}
		
	@RequestMapping(value = "/api/amortizaciones/{KEY}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public Iterable<PrestamoCuotaDto> mostrarTablaAmortizacionJSON(@PathVariable("KEY") Long key) {
		
		Map<Long, Iterable<PrestamoCuotaDto>> map = hazelcastInstance.getMap("desarrollos");
		Iterable<PrestamoCuotaDto> prestamoCuotaDtos = map.get(key);
		
		return prestamoCuotaDtos;
	}
	
	@RequestMapping(value = "/api/amortizaciones/listar", method = RequestMethod.GET)
	@PreAuthorize("hasRole('DESARROLLO_CUOTAS')")
	public List<Long> amortizaciones() {
		
		Map<Long, Iterable<PrestamoCuotaDto>> map = hazelcastInstance.getMap("desarrollos");
		List<Long> amortizaciones = new ArrayList<Long>();
		
		for(Map.Entry<Long, Iterable<PrestamoCuotaDto>> entry : map.entrySet()) {
			amortizaciones.add(entry.getKey());
		}
		
		return amortizaciones;
	}
}

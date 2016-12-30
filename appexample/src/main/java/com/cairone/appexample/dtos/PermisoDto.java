package com.cairone.appexample.dtos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.cairone.appexample.entities.PermisoEntity;

public class PermisoDto implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	
	private String permiso = null;
	
	public PermisoDto() {}

	public PermisoDto(PermisoEntity permisoEntity) {
		this.permiso = String.format("ROLE_%s", permisoEntity.getNombre());
	}
	
	@Override
	public String getAuthority() {
		return permiso;
	}

	public PermisoDto(String permiso) {
		super();
		this.permiso = permiso;
	}

	public String getPermiso() {
		return permiso;
	}

	public static final List<PermisoDto> crearLista(Iterable<PermisoEntity> permisoEntities) {
		
		List<PermisoDto> lista = new ArrayList<PermisoDto>();
		
		for(PermisoEntity permisoEntity : permisoEntities) {
			lista.add(new PermisoDto(permisoEntity));
		}
		
		return lista;
	}
}

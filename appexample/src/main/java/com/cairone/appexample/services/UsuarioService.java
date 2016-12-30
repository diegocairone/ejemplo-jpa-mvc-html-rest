package com.cairone.appexample.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cairone.appexample.dtos.PermisoDto;
import com.cairone.appexample.dtos.UserDetailsImplDto;
import com.cairone.appexample.entities.PermisoEntity;
import com.cairone.appexample.entities.QUsuarioEntity;
import com.cairone.appexample.entities.QUsuarioPermisoEntity;
import com.cairone.appexample.entities.UsuarioEntity;
import com.cairone.appexample.entities.UsuarioPermisoEntity;
import com.cairone.appexample.repositories.UsuarioPermisoRepository;
import com.cairone.appexample.repositories.UsuarioRepository;
import com.mysema.query.types.expr.BooleanExpression;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired private UsuarioRepository usuarioRepository = null;
	@Autowired private UsuarioPermisoRepository usuarioPermisoRepository = null;
	
	public Iterable<PermisoEntity> buscarPermisos(UsuarioEntity usuarioEntity) {

		List<PermisoEntity> permisoEntities = new ArrayList<PermisoEntity>();
		
		QUsuarioPermisoEntity qUsuarioPermiso = QUsuarioPermisoEntity.usuarioPermisoEntity;
		BooleanExpression expUsuarioPermiso = qUsuarioPermiso.usuario.eq(usuarioEntity);
		
		Iterable<UsuarioPermisoEntity> usuarioPermisoEntities = usuarioPermisoRepository.findAll(expUsuarioPermiso);
		
		for(UsuarioPermisoEntity usuarioPermisoEntity : usuarioPermisoEntities) {
			permisoEntities.add(usuarioPermisoEntity.getPermiso());
		}
		
		return permisoEntities;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		QUsuarioEntity q = QUsuarioEntity.usuarioEntity;
		BooleanExpression exp = q.nombreUsuario.eq(username);
		
		UsuarioEntity usuarioEntity = usuarioRepository.findOne(exp);
		
		if(usuarioEntity == null) {
			throw new UsernameNotFoundException("EL USUARIO NO EXISTE");
		}
		
		Iterable<PermisoEntity> permisoEntities = buscarPermisos(usuarioEntity);
		List<PermisoDto> permisoDtos = PermisoDto.crearLista(permisoEntities);
		
		UserDetailsImplDto userDetailsImplDto = new UserDetailsImplDto(usuarioEntity, permisoDtos);
				
		return userDetailsImplDto;
	}
}

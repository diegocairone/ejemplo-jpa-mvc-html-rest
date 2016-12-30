package com.cairone.appexample.dtos;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cairone.appexample.entities.UsuarioEntity;

public class UserDetailsImplDto implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private String nombreUsuario = null;
	private String clave = null;
	private Boolean cuentaVencida = null;
	private Boolean claveVencida = null;
	private Boolean cuentaBloqueada = null;
	private Boolean usuarioHabilitado = null;
	private List<PermisoDto> permisos = null;

	public UserDetailsImplDto(UsuarioEntity usuarioEntity) {
		this.nombreUsuario = usuarioEntity.getNombreUsuario();
		this.clave = usuarioEntity.getClave();
		this.cuentaVencida = usuarioEntity.getCuentaVencida();
		this.claveVencida = usuarioEntity.getClaveVencida();
		this.cuentaBloqueada = usuarioEntity.getCuentaBloqueada();
		this.usuarioHabilitado = usuarioEntity.getUsuarioHabilitado();
	}
	
	public UserDetailsImplDto(UsuarioEntity usuarioEntity, List<PermisoDto> permisoDtos) {
		this(usuarioEntity);
		this.permisos = permisoDtos;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return permisos;
	}

	@Override
	public String getPassword() {
		return clave;
	}

	@Override
	public String getUsername() {
		return nombreUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return !cuentaVencida;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return !cuentaBloqueada;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !claveVencida;
	}

	@Override
	public boolean isEnabled() {
		return usuarioHabilitado;
	}

}

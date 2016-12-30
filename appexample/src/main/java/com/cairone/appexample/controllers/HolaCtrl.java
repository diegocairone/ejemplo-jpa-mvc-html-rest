package com.cairone.appexample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HolaCtrl {

	@RequestMapping("/")
	public String inicio() {
		return "hola";
	}

	@RequestMapping("/login")
	public String entrar() {
		return "login";
	}
}

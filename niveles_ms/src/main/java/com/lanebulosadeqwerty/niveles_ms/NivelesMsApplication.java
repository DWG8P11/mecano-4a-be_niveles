package com.lanebulosadeqwerty.niveles_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class NivelesMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NivelesMsApplication.class, args);
	}

	@GetMapping("/")
	public String saludo() {
		return """
		<h1>La Nebulosa de Qwerty - Backend Gestión de Niveles </h1> 
		Esta es una aplicación para aprender mecanografía de forma gradual, utilizando como material de aprendizaje y de motivación textos sobre la gran variedad de cuerpos celestes. \
		Este repositorio corresponde al módulo de backend de <i>Gestión de Niveles</i> de esta aplicación, desarrollada como proyecto del Ciclo 4 del programa de formación MisiónTIC 2022 Cohorte 2021, por el <b>Equipo 8 del Grupo 11 de Desarrollo Web</b>.</br>\
		Se hace uso del framework Sprint Boot para el desarrollo de este módulo, también se emplea una API Gateway que permite habilitar la conexión entre los servicios y los clientes, realizando peticiones a distintos microservicios. Estos microservicios poseen una API de tipo REST, así que, la comunicación se realiza a través de peticiones HTTP.""";
	}

}

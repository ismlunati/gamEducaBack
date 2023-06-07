package com.gameduca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.entity.Compra;
import com.gameduca.service.LogroService;

@RestController
public class CompraController {
	
	@Autowired
	LogroService logroService;
	
//    @GetMapping("/asignaturas/{idAsignatura}/compras")
//	public List<Compra> obtenerTodasComprasUsuario(){
//		
//	}
	
//  @PostMapping("/asignaturas/{idAsignatura}/compras/{idArtefacto}")
//	public List<Compra> crearCompra(@PathVariable Long idAsignatura, @PathVariable Long idArtefacto){
//		
//	}

}

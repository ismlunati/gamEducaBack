package com.gameduca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gameduca.entity.Compra;
import com.gameduca.service.CompraService;
import com.gameduca.service.LogroService;

@RestController
public class CompraController {
	
	@Autowired
	CompraService compraService;
	
    @GetMapping("/asignaturas/{idAsignatura}/compras")
	public List<Compra> obtenerTodasComprasUsuario(@PathVariable Long idAsignatura){
		return compraService.obtenerTodasComprasUsuario(idAsignatura);
	}
    
    @GetMapping("/asignaturas/{idAsignatura}/compras/{idCompra}")
	public Compra obtenerCompra(@PathVariable Long idCompra){
		return compraService.obtenerCompra(idCompra);
	}
	
    @PostMapping("/asignaturas/{idAsignatura}/compras/{idArtefacto}")
	public Compra crearCompra(@PathVariable Long idArtefacto, @PathVariable Long idAsignatura) throws Exception{
		return compraService.crearCompra(idArtefacto, idAsignatura);
	}
	

}

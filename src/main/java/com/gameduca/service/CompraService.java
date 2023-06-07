package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.repository.CompraRepository;
import com.gameduca.repository.LogroRepository;

@Service
@Transactional
public class CompraService {
	
    @Autowired
    CompraRepository compraRepository;

}

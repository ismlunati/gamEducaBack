package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.repository.RespuestaRepository;

@Service
@Transactional
public class RespuestaService {
	
    @Autowired
    RespuestaRepository respuestaService;

}

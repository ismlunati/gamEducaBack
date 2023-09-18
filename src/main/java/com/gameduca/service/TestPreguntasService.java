package com.gameduca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gameduca.repository.TestPreguntasRepository;

@Service
@Transactional
public class TestPreguntasService {
	
    @Autowired
    TestPreguntasRepository testPreguntasRepository;

}

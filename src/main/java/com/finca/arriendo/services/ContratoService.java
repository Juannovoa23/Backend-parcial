package com.finca.arriendo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finca.arriendo.dto.ContratoDto;
import com.finca.arriendo.model.Contrato;
import com.finca.arriendo.repository.ContratoRepository;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ContratoDto createContrato(ContratoDto contratoDto) {
        Contrato contrato = modelMapper.map(contratoDto, Contrato.class);
        contrato = contratoRepository.save(contrato);
        return modelMapper.map(contrato, ContratoDto.class);
    }

    public List<ContratoDto> getAllContratos() {
        return contratoRepository.findAll().stream()
                .map(contrato -> modelMapper.map(contrato, ContratoDto.class))
                .collect(Collectors.toList());
    }

    public ContratoDto getContratoById(Long id) {
        return contratoRepository.findById(id)
                .map(contrato -> modelMapper.map(contrato, ContratoDto.class))
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
    }

    public ContratoDto updateContrato(Long id, ContratoDto contratoDto) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
        modelMapper.map(contratoDto, contrato);
        contrato = contratoRepository.save(contrato);
        return modelMapper.map(contrato, ContratoDto.class);
    }

    public void deleteContrato(Long id) {
        contratoRepository.deleteById(id);
    }
}

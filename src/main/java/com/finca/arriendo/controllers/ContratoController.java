package com.finca.arriendo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finca.arriendo.dto.ContratoDto;
import com.finca.arriendo.services.ContratoService;

@RestController
@RequestMapping("/api/contratos")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    /**
     * Creates a new Contrato with the given information.
     * @param contratoDto The information of the Contrato to be created.
     * @return The created Contrato.
     */
    @PostMapping
    public ContratoDto createContrato(@RequestBody ContratoDto contratoDto) {
        return contratoService.createContrato(contratoDto);
    }

    @GetMapping
    public List<ContratoDto> getAllContratos() {
        return contratoService.getAllContratos();
    }

    @GetMapping("/{id}")
    public ContratoDto getContratoById(@PathVariable Long id) {
        return contratoService.getContratoById(id);
    }

    @PutMapping("/{id}")
    public ContratoDto updateContrato(@PathVariable Long id, @RequestBody ContratoDto contratoDto) {
        return contratoService.updateContrato(id, contratoDto);
    }

    @DeleteMapping("/{id}")
    public void deleteContrato(@PathVariable Long id) {
        contratoService.deleteContrato(id);
    }
}

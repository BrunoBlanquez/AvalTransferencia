package com.example.AgendamentoTransf.controller;

import com.example.AgendamentoTransf.dto.TransferRequest;
import com.example.AgendamentoTransf.dto.TransferResponse;
import com.example.AgendamentoTransf.model.Transfer;
import com.example.AgendamentoTransf.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
@CrossOrigin(origins = "http://localhost:4200")
public class TransferController {

    @Autowired
    private TransferService service;

    @GetMapping
    public List<TransferResponse> listar() {
        return service.listarTodasTransf();
    }

    @PostMapping
    public ResponseEntity<?> agendar(@Valid @RequestBody TransferRequest request) {
        try {
            TransferResponse response = service.agendar(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

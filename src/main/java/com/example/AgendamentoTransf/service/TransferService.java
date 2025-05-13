package com.example.AgendamentoTransf.service;

import com.example.AgendamentoTransf.model.Transfer;
import com.example.AgendamentoTransf.repository.TransferRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransferService {
    private final TransferRepository repository;

    public TransferService(TransferRepository repository) {
        this.repository = repository;
    }

    public Transfer salvar(Transfer transferencia) {
        return repository.save(transferencia);
    }

    public List<Transfer> listarTodas() {
        return repository.findAll();
    }

}

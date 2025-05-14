package com.example.AgendamentoTransf.service;

import com.example.AgendamentoTransf.dto.TransferRequest;
import com.example.AgendamentoTransf.dto.TransferResponse;
import com.example.AgendamentoTransf.model.Transfer;
import com.example.AgendamentoTransf.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransferService {

    @Autowired
    private TransferRepository repository;

    public TransferResponse agendar(TransferRequest dto) {
        LocalDate hoje = LocalDate.now();
        long dias = ChronoUnit.DAYS.between(LocalDate.now(), dto.getDataTransferencia());

        if (dias > 50) {
            throw new IllegalArgumentException("Nenhuma taxa aplicável para esse intervalo de dias.");
        }

        Transfer transfer = new Transfer();
        transfer.setContaOrigem(dto.getContaOrigem());
        transfer.setContaDestino(dto.getContaDestino());
        transfer.setValor(dto.getValor());
        transfer.setDataAgendamento(hoje);
        transfer.setDataTransferencia(dto.getDataTransferencia());

        transfer.definirTaxa(dias);

        Transfer novaTransferencia = repository.save(transfer);
        return new TransferResponse(novaTransferencia);
    }

    public List<TransferResponse> listarTodasTransf() {
        return repository.findAll().stream()
                .map(TransferResponse::new)
                .collect(Collectors.toList());
    }

}

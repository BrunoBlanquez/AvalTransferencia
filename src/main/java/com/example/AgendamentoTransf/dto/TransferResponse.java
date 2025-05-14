package com.example.AgendamentoTransf.dto;

import com.example.AgendamentoTransf.model.Transfer;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransferResponse {
    private Long id;
    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;

    // Construtor ou Builder (manual ou via Lombok, se preferir)
    public TransferResponse(Transfer transfer) {
        this.id = transfer.getId();
        this.contaOrigem = transfer.getContaOrigem();
        this.contaDestino = transfer.getContaDestino();
        this.valor = transfer.getValor();
        this.taxa = transfer.getTaxa();
        this.dataAgendamento = transfer.getDataAgendamento();
        this.dataTransferencia = transfer.getDataTransferencia();
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }
}

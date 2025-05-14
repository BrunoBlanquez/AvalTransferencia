package com.example.AgendamentoTransf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contaOrigem;
    private String contaDestino;
    private BigDecimal valor;
    private BigDecimal taxa;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;

    public Transfer() {
    }

    public Transfer(String contaOrigem, String contaDestino, BigDecimal valor, BigDecimal taxa, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.taxa = taxa;
        this.dataAgendamento = dataAgendamento;
        this.dataTransferencia = dataTransferencia;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(LocalDate dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    private BigDecimal calcularTaxa(long dias, BigDecimal valor) {
        switch (definirIntervaloParaTaxas(dias)) {
            case 0:
                return new BigDecimal("3.00").add(valor.multiply(new BigDecimal("0.025")));
            case 1:
                return new BigDecimal("12.00");
            case 2:
                return valor.multiply(new BigDecimal("0.082"));
            case 3:
                return valor.multiply(new BigDecimal("0.069"));
            case 4:
                return valor.multiply(new BigDecimal("0.047"));
            case 5:
                return valor.multiply(new BigDecimal("0.017"));
            default:
                return null;
        }
    }

    private int definirIntervaloParaTaxas(long dias) {
        if (dias == 0) return 0;
        if (dias <= 10) return 1;
        if (dias <= 20) return 2;
        if (dias <= 30) return 3;
        if (dias <= 40) return 4;
        if (dias <= 50) return 5;
        return -1;
    }
}

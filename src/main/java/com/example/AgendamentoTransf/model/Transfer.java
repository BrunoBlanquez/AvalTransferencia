package com.example.AgendamentoTransf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Conta de origem é obrigatória")
    private Long contaOrigem;

    @NotNull(message = "Conta de destino é obrigatória")
    private Long contaDestino;

    @NotNull(message = "Valor é obrigatório")
    @Digits(integer = 10, fraction = 2, message = "Valor deve ter no máximo 10 dígitos inteiros e 2 decimais")
    private BigDecimal valor;

    private BigDecimal taxa;
    private LocalDate dataAgendamento;
    private LocalDate dataTransferencia;

    public Transfer() {
    }

    public Transfer(Long contaOrigem, Long contaDestino, BigDecimal valor, BigDecimal taxa, LocalDate dataAgendamento, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.taxa = taxa;
        this.dataAgendamento = dataAgendamento;
        this.dataTransferencia = dataTransferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Long contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Long getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Long contaDestino) {
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

    public void definirTaxa(long dias) {
        switch (definirIntervaloParaTaxas(dias)) {
            case 0:
                this.taxa = new BigDecimal("3.00").add(this.valor.multiply(new BigDecimal("0.025")));
                break;
            case 1:
                this.taxa = new BigDecimal("12.00");
                break;
            case 2:
                this.taxa = valor.multiply(new BigDecimal("0.082"));
                break;
            case 3:
                this.taxa = valor.multiply(new BigDecimal("0.069"));
                break;
            case 4:
                this.taxa = valor.multiply(new BigDecimal("0.047"));
                break;
            case 5:
                this.taxa = valor.multiply(new BigDecimal("0.017"));
                break;
            default:
                throw new IllegalArgumentException("Nenhuma taxa aplicável para esse intervalo de dias.");
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

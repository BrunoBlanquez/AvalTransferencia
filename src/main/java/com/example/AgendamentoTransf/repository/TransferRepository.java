package com.example.AgendamentoTransf.repository;

import com.example.AgendamentoTransf.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}

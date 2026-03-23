package com.gestao_academia.repository;

import com.gestao_academia.model.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface CheckInRepository extends JpaRepository<CheckIn, UUID> {
    List<CheckIn> findByAlunoIdAndDataCheckinAfter(UUID alunoId, LocalDateTime data);
}
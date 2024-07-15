package com.codelab.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@Entity(name = "bolsa")
@Table(name = "bolsa")
@AllArgsConstructor
@NoArgsConstructor
public class Bolsa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    Integer anoConcessao;
    Integer codigoEmec;
    String nomeIes;
    String tipoBolsa;
    String modalidadeEnsino;
    String nomeCurso;
    String nomeTurnoCurso;
    String cpfBeneficiario;
    String sexoBeneficiario;
    String racaBeneficiario;
    LocalDate dataNascimento;
    String beneficiarioDeficienteFisico;
    String regiaoBeneficiario;
    String ufBeneficiario;
    String municipioBeneficiario;
}

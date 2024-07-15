CREATE TABLE bolsa (
    id VARCHAR(255) PRIMARY KEY,
    ano_concessao INTEGER,
    codigo_emec INTEGER,
    nome_ies VARCHAR(255),
    tipo_bolsa VARCHAR(50),
    modalidade_ensino VARCHAR(50),
    nome_curso VARCHAR(255),
    nome_turno_curso VARCHAR(50),
    cpf_beneficiario VARCHAR(14),
    sexo_beneficiario CHAR(1),
    raca_beneficiario VARCHAR(50),
    data_nascimento DATE,
    beneficiario_deficiente_fisico CHAR(1),
    regiao_beneficiario VARCHAR(50),
    uf_beneficiario CHAR(2),
    municipio_beneficiario VARCHAR(255)
);
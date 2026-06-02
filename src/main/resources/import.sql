INSERT INTO tb_regiao (nome, coordenadas, populacao_afetada, data_mapeamento) VALUES ('Vale do Itajaí', '-26.9166, -49.0666', 150000, '2026-01-15');
INSERT INTO tb_regiao (nome, coordenadas, populacao_afetada, data_mapeamento) VALUES ('Encosta da Serra', '-29.1666, -51.1833', 85000, '2026-02-20');
INSERT INTO tb_regiao (nome, coordenadas, populacao_afetada, data_mapeamento) VALUES ('Baixada Santista', '-23.9533, -46.3333', 300000, '2026-03-10');
INSERT INTO tb_regiao (nome, coordenadas, populacao_afetada, data_mapeamento) VALUES ('Regiao Serrana RJ', '-22.2833, -42.5333', 120000, '2026-04-05');

INSERT INTO tb_alerta_evento (tipo_alerta, probabilidade_tragedia, nivel_risco, data_emissao, regiao_id) VALUES ('ENCHENTE', 85.5, 'EXTREMO', '2026-06-01', 1);
INSERT INTO tb_alerta_evento (tipo_alerta, probabilidade_tragedia, nivel_risco, data_emissao, regiao_id) VALUES ('DESLIZAMENTO', 45.0, 'MEDIO', '2026-06-02', 2);
INSERT INTO tb_alerta_evento (tipo_alerta, probabilidade_tragedia, nivel_risco, data_emissao, regiao_id) VALUES ('TEMPESTADE', 65.0, 'GRANDE', '2026-06-03', 1);
INSERT INTO tb_alerta_evento (tipo_alerta, probabilidade_tragedia, nivel_risco, data_emissao, regiao_id) VALUES ('FUMAÇA', 15.0, 'LEVE', '2026-06-04', 3);
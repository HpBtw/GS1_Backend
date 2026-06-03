# 🛡️ GeoShield API - Global Solution FIAP

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5+-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)

API RESTful desenvolvida como parte da avaliação **Global Solution** da FIAP. O GeoShield é um microsserviço focado no monitoramento ambiental e gestão de crises, permitindo o cadastro de regiões vulneráveis e a emissão de alertas de eventos climáticos ou desastres.

## 📋 Sobre o Projeto

O sistema foi arquitetado utilizando as melhores práticas de desenvolvimento back-end, incluindo a separação rigorosa em camadas (Controller, Service, Repository, Entity e DTO) e a proteção de dados através do mapeamento unidirecional de transferência de dados (Data Transfer Objects).

### 🎯 Principais Funcionalidades e Regras de Negócio
* **CRUD Completo:** Gerenciamento total de `Regiões` e `Alertas de Eventos`.
* **Cálculo Automático de Risco:** A API não recebe o nível de risco do usuário. Ao enviar a "Probabilidade da Tragédia" (%), a camada de serviço calcula e classifica automaticamente o alerta como `LEVE`, `MEDIO`, `GRANDE` ou `EXTREMO`.
* **Proteção de Integridade:** Impede a exclusão de uma região caso existam alertas ativos vinculados a ela, retornando uma exceção tratada de banco de dados (`DataIntegrityViolationException`).
* **Validação de Dados:** Utilização do *Jakarta Bean Validation* para garantir que coordenadas, tamanhos de string, valores numéricos e datas (ex: não permitir datas no futuro) estejam estritamente corretos antes de tocar no banco de dados.

## 🛠️ Tecnologias Utilizadas

* **Java 17**
* **Spring Boot 3.5+** (Web, Data JPA, Validation)
* **H2 Database** (Banco de dados em memória para o Profile de Testes)
* **Lombok** (Redução de boilerplate de código)
* **Maven** (Gerenciamento de dependências)

## 🏗️ Arquitetura e Modelagem de Dados

O projeto segue a arquitetura de **Relacionamento 1:N (One-To-Many)** onde **1 Região** pode possuir **N Alertas**, mas 1 Alerta pertence a apenas 1 Região.

*A ocultação de entidades (Information Hiding) é garantida no tráfego HTTP pelo uso de DTOs, evitando o erro de StackOverflow (Loop Infinito Bidirecional) durante a serialização do JSON.*

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Java 17** e o **Maven** instalados na sua máquina.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/HpBtw/GeoShield.git](https://github.com/HpBtw/GeoShield.git)


3. Acesse a pasta do projeto:
```bash
cd GeoShield

```


4. Baixe as dependências e inicie a aplicação:
```bash
mvn spring-boot:run

```


*Nota: A aplicação subirá na porta `8080` utilizando o `spring.profiles.active=test`, e o banco de dados será populado automaticamente com os registros iniciais do arquivo `import.sql`.*
5. Acesso ao Console do Banco de Dados H2:
* **URL:** `http://localhost:8080/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **User:** `sa`
* **Password:** *(deixar em branco)*



## 📡 Endpoints da API

Abaixo estão as rotas disponíveis no sistema. O payload e as respostas seguem o formato `application/json`.

### Regiões (`/regioes`)

| Método | Rota | Descrição | Status de Sucesso |
| --- | --- | --- | --- |
| `POST` | `/regioes` | Cadastra uma nova região | `201 Created` |
| `GET` | `/regioes` | Lista todas as regiões cadastradas | `200 OK` |
| `GET` | `/regioes/{id}` | Busca uma região específica pelo ID | `200 OK` |
| `PUT` | `/regioes/{id}` | Atualiza os dados de uma região | `200 OK` |
| `DELETE` | `/regioes/{id}` | Deleta uma região (se não houver alertas vinculados) | `204 No Content` |

### Alertas de Eventos (`/alertas`)

| Método | Rota | Descrição | Status de Sucesso |
| --- | --- | --- | --- |
| `POST` | `/alertas` | Cadastra um novo alerta para uma região | `201 Created` |
| `GET` | `/alertas` | Lista todos os alertas emitidos | `200 OK` |
| `GET` | `/alertas/{id}` | Busca um alerta específico pelo ID | `200 OK` |
| `PUT` | `/alertas/{id}` | Atualiza um alerta (incluindo cálculo de novo risco) | `200 OK` |
| `DELETE` | `/alertas/{id}` | Cancela/Deleta um alerta | `204 No Content` |

## 🚨 Tratamento de Erros (Exceptions Customizadas)

O sistema possui um `GlobalExceptionHandler` que captura e formata exceções em respostas JSON amigáveis:

* **404 Not Found:** Disparado pelo `ResourceNotFoundException` (ex: buscar um ID que não existe).
* **422 Unprocessable Entity:** Disparado quando as validações do Bean Validation falham (ex: enviar data vazia ou probabilidade negativa).
* **409 Conflict / 400 Bad Request:** Disparado pelo `DatabaseException` em falhas de integridade (ex: excluir região com alertas).

---

**Desenvolvido por:** hp - Estudante de Sistemas de Informação na FIAP.


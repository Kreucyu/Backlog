BackLog – Sistema de Gerenciamento de Biblioteca de Jogos

Este projeto tem como objetivo aplicar e consolidar os conhecimentos que venho adquirindo durante minha graduação e estudos pessoais em desenvolvimento de software.

O BackLog é um sistema para gerenciamento de uma biblioteca de jogos, permitindo organizar jogos finalizados, em andamento e títulos que ainda fazem parte do backlog.

Além de funcionar como uma ferramenta prática, o foco principal do projeto é aprendizado e experimentação com tecnologias modernas, principalmente no desenvolvimento back-end e arquitetura de sistemas distribuídos.

---

Objetivos do Projeto

O projeto foca na expansão do meu conhecimento técnico, explorando:

```
Arquitetura de microserviços

Desenvolvimento de APIs REST com Spring Boot

Containerização com Docker

Integração entre serviços (conceitos de redes)

Segurança e criptografia de dados

Filas e mensageria com RabbitMQ

Cache com Redis

Desenvolvimento front-end com Angular
```

---

Arquitetura do Sistema

O sistema segue uma arquitetura baseada em microserviços, onde cada serviço é responsável por um domínio específico.

API Gateway

```
Responsável por receber e rotear as requisições para os serviços corretos.

Status: planejado
```

Serviço de Usuários

```
Responsável pelo gerenciamento de usuários:

- Cadastro

- Autenticação

- Criptografia de senha

Status: em desenvolvimento (lógica implementada e testes unitários concluídos)
```

Serviço de Jogos

```
Responsável pelo catálogo de jogos:

- Cadastro

- Informações

- Sistema de busca

Status: planejado
```

Serviço de Backlog

```
Responsável pela biblioteca do usuário:

- Adicionar jogos

- Marcar progresso

- Finalizar jogos

Status: planejado
```

---

Infraestrutura

Banco de Dados

```
Cada serviço possui seu próprio banco, garantindo independência.

Status: parcialmente implementado (User Service)
```

Docker

```
Utilizado para containerizar os serviços.

Status: em planejamento
```

RabbitMQ

```
Utilizado para comunicação assíncrona entre serviços.

Status: planejado
```

Redis

```
Utilizado para cache e otimização de consultas.

Status: planejado
```

---

Evolução do Projeto

Este projeto é uma evolução dos meus estudos anteriores (CRUD com JDBC), agora utilizando ferramentas e arquiteturas mais modernas.

---

Desenvolvimento

O projeto está sendo desenvolvido de forma incremental.

Progresso atual

```
Lógica do User Service implementada

Regras de negócio definidas (validação, duplicidade, criptografia)

Testes unitários cobrindo cenários principais

Refatoração do service (remoção de ResponseEntity)

Uso de exceções para controle de fluxo
```

Próximos passos

```
Refinar testes e validações do User Service

Implementar Game Service e Backlog Service

Introduzir Docker no projeto

Integrar RabbitMQ para comunicação entre serviços

Implementar Redis para cache

Desenvolver front-end com Angular

Criar API Gateway
```

---

Objetivo

Este repositório funciona como meu principal projeto de aprendizado, onde aplico novos conceitos, melhoro minha arquitetura e evoluo na qualidade do código.

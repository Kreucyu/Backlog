BackLog – Game Library Management System (English)

This project aims to apply and consolidate the knowledge I have been acquiring during my university studies and personal software development learning.

BackLog is designed to be a system for managing a personal game library, allowing users to organize played games, games currently being played, and games that are still part of their backlog.

Beyond being a functional tool, the main purpose of this project is learning and experimentation with new technologies, especially those related to backend development and distributed system architecture.

--- 

Project Goals

In this project, I will work with technologies and concepts that are not yet part of my usual development stack, aiming to expand my technical knowledge.

The main topics explored in this project include:

    Microservices architecture to distribute system responsibilities

    Development of REST APIs using Spring Boot

    Docker for application containerization

    A React.js front-end, to better understand frontend-backend integration

    Applying Computer Networks concepts for service communication

    Implementing data security and encryption for user authentication

    Using advanced database queries to enable more complex search capabilities within the system

---

System Architecture

The project will follow a microservices architecture, where each service is responsible for a specific domain of the application.

The system will initially be composed of the following services:

API Gateway

    Responsible for routing requests from the client to the appropriate microservice.

User Service

    Handles user management, including:

    -User registration

    -Authentication

    -Password encryption

Game Service

    Responsible for managing the game catalog:

    -Game registration

    -Game information

    -Search functionality

Backlog Service

    Manages the user's personal library:

    -Add games to backlog

    -Track playing status

    -Mark games as completed

Database Layer

    Each microservice may have its own database to ensure service independence, following microservices best practices.

Containerization

    All services will run inside Docker containers, allowing easier setup and scalability.

---

Learning Purpose

This project is also an evolution of my previous work in the repository Studies-v2, where I focused primarily on CRUD operations using JDBC and relational databases.

In BackLog, I aim to rebuild a similar type of system from scratch while applying more modern technologies and architectures.

---

Development

The project will be updated gradually as I continue my studies and balance development alongside my university projects.

The goal is for this repository to become my main personal learning project, serving as a space to experiment with new technologies, architectural ideas, and development practices.

---

BackLog – Sistema de Gerenciamento de Biblioteca de Jogos (Português)

Este projeto tem como objetivo aplicar e consolidar os conhecimentos que venho adquirindo durante minha graduação e estudos pessoais em desenvolvimento de software.

O BackLog foi pensado como um sistema para gerenciamento de uma biblioteca de jogos, permitindo organizar jogos já finalizados, jogos em andamento e títulos que ainda fazem parte do backlog.

Além de funcionar como uma ferramenta prática, o principal objetivo deste projeto é experimentação e aprendizado de novas tecnologias, especialmente voltadas ao desenvolvimento back-end e arquiteturas de sistemas distribuídos.

---

Objetivos do Projeto

Neste projeto buscarei trabalhar com tecnologias e conceitos que ainda não fazem parte do meu domínio habitual, com o objetivo de expandir meu conhecimento técnico.

Entre os principais pontos que pretendo explorar estão:

    Arquitetura de microserviços para distribuir as responsabilidades do sistema

    Desenvolvimento de APIs REST utilizando Spring Boot

    Utilização de Docker para containerização das aplicações

    Desenvolvimento de um front-end com React.js, buscando compreender melhor a integração entre front-end e back-end

    Aplicação de conceitos de Redes de Computadores na comunicação entre serviços

    Implementação de criptografia e segurança de dados no cadastro e autenticação de usuários

    Utilização de consultas avançadas em banco de dados para permitir pesquisas mais completas dentro do sistema

---

Arquitetura do Sistema

O projeto seguirá uma arquitetura baseada em microserviços, onde cada serviço será responsável por um domínio específico da aplicação.

Inicialmente, o sistema será composto pelos seguintes serviços:

API Gateway

    Responsável por receber as requisições do cliente e direcioná-las para o microserviço correto.

Serviço de Usuários

    Responsável pelo gerenciamento de usuários:

    -Cadastro de usuários

    -Autenticação

    -Criptografia de senha

Serviço de Jogos

    Responsável pelo catálogo de jogos:

    -Cadastro de jogos

    -Armazenamento de informações

    -Sistema de busca

Serviço de Backlog

    Responsável pela biblioteca pessoal do usuário:

    -Adicionar jogos ao backlog

    -Marcar jogos como em andamento

    -Marcar jogos como finalizados

Camada de Banco de Dados

Cada microserviço poderá possuir seu próprio banco de dados, garantindo independência entre serviços, seguindo boas práticas de arquitetura de microserviços.

Containerização

Todos os serviços serão executados em containers Docker, permitindo maior facilidade de configuração e escalabilidade do sistema.

---

Objetivo de Aprendizado

Este projeto representa uma evolução dos meus estudos anteriores presentes no repositório Studies-v2, no qual foquei principalmente em operações CRUD utilizando JDBC e banco de dados relacionais.

No BackLog, pretendo reconstruir esse tipo de sistema desde o início, aplicando ferramentas e arquiteturas mais modernas.

---

Desenvolvimento

O projeto será atualizado gradualmente conforme avanço nos estudos e concilio seu desenvolvimento com os projetos da faculdade.

A ideia é que ele se torne meu principal projeto pessoal de aprendizado, servindo como um ambiente para testar novas tecnologias, arquiteturas e boas práticas de desenvolvimento.
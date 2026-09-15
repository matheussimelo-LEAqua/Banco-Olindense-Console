# Banco Olindense - Sistema Bancário via Console

Projeto de um sistema bancário de terminal construído em Java puro. O objetivo principal deste projeto foi colocar em prática conceitos de Programação Orientada a Objetos (POO), encapsulamento e validações de regras de negócio antes de migrar o sistema para o ecossistema Spring Boot.

## Funcionalidades

O sistema roda via console e permite:

* Cadastro de clientes com opções de Conta Corrente e Conta Poupança.
* Validação de dados de entrada via Regex (CPF, telefone de 11 dígitos, senha e formatação do número da conta).
* Sistema de login verificando credenciais em memória.
* Painel de usuário logado com operações de saque e depósito.
* Ficha de exibição de dados do cliente.

## Conceitos Aplicados

* Java 21
* Herança e Polimorfismo (utilizados na relação entre Conta, Conta Corrente e Conta Poupança).
* Tratamento de Exceções (uso de IllegalArgumentException para impedir a instanciação de objetos com dados inválidos).
* Separação de responsabilidades: os dados (como CPF e número da conta) são armazenados em seu formato bruto na classe e formatados apenas no momento da exibição.

## Como executar

1. Clone o repositório:
   git clone https://github.com/matheussimelo-LEAqua/Banco-Olindense-Console.git

2. Abra o projeto na sua IDE (IntelliJ, Eclipse, etc).
3. Rode o método main localizado no arquivo `src/banco/app/Main.java`.

## Próximos passos

O projeto cumpriu seu objetivo como aplicação de console. A próxima etapa é utilizar a lógica de negócio estruturada aqui (Models e validações) para criar uma API RESTful utilizando Spring Boot, substituindo o armazenamento em memória por um banco de dados relacional.
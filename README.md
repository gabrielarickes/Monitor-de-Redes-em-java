# Monitor de Rede em Java

Projeto desenvolvido em Java para coletar informações de rede da máquina e realizar testes simples de conectividade.

O programa funciona em **Windows e Linux**, identificando automaticamente o sistema operacional para utilizar os parâmetros corretos do comando `ping`.

## Funcionalidades

O programa permite:

* Coletar os endereços IP das interfaces de rede;
* Identificar endereços IPv4 e IPv6;
* Medir a latência da rede;
* Calcular a taxa de pacotes perdidos;
* Executar todos os testes por meio de um menu;
* Utilizar o mesmo programa no Windows e no Linux.

## Estrutura do projeto

O projeto está dividido em quatro classes:

```text
MonitorRede.java
Menu.java
ColetorDeEnderecos.java
TestadorDeRede.java
```

### MonitorRede.java

É a classe principal do programa. Possui o método `main` e controla a execução das opções escolhidas pelo usuário.

### Menu.java

Responsável por exibir o menu de opções e solicitar ao usuário o endereço IP ou nome do host que será testado.

### ColetorDeEnderecos.java

Responsável por coletar os endereços IP das interfaces de rede da máquina e identificar se cada endereço utiliza IPv4 ou IPv6.

### TestadorDeRede.java

Responsável por realizar os testes de rede, medindo a latência e calculando a taxa de pacotes perdidos por meio do comando `ping`.

A classe identifica automaticamente se o programa está sendo executado no Windows ou no Linux.

## Requisitos

Para executar o projeto é necessário:

* Java JDK instalado;
* Terminal, Prompt de Comando ou PowerShell;
* Comando `ping` disponível no sistema operacional.

## Como compilar

Abra o terminal na pasta onde estão os arquivos e execute:

```bash
javac MonitorRede.java Menu.java ColetorDeEnderecos.java TestadorDeRede.java
```

Também é possível compilar todos os arquivos `.java` da pasta com:

```bash
javac *.java
```

## Como executar

Após a compilação, execute a classe principal:

```bash
java MonitorRede
```

## Menu do programa

Ao iniciar o programa, será apresentado o seguinte menu:

```text
=== MENU DE OPCOES ===
1. Coletar enderecos IP e verificar o tipo (IPv4/IPv6)
2. Medir a latencia de rede
3. Calcular a taxa de pacotes perdidos
4. Executar tudo (opcoes 1, 2 e 3)
5. Sair
```

Nas opções de teste de rede, o usuário deve informar um endereço IP ou nome de host.

Exemplo:

```text
8.8.8.8
```

## Compatibilidade

O programa identifica automaticamente o sistema operacional.

No **Windows**, utiliza:

```text
ping -n 1 -w 2000 host
```

No **Linux**, utiliza:

```text
ping -c 1 -W 2 host
```

Dessa forma, não é necessário modificar o código para utilizar o programa nos dois sistemas.

## Tecnologias utilizadas

* Java;
* Biblioteca `java.net`;
* `ProcessBuilder`;
* Expressões regulares;
* Comando `ping`.

## Objetivo

O objetivo do projeto é aplicar conceitos de redes de computadores utilizando Java, permitindo visualizar os endereços IP da máquina, identificar o tipo de endereço e analisar informações básicas de conectividade, como latência e perda de pacotes.

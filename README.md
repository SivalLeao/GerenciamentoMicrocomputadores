<h1 align="center">

  <p>Sistema de Gerenciamento de Microcomputadores</p>
  
</h1>
<blockquote> Projeto utilizado como ferramenta de avaliação para a disciplina de 
MI - Programação do curso de Engenharia de Computação da UEFS.</blockquote>

## :blue_book: Sobre

O **Sistema de Gerenciamento de 
Microcomputadores** é um software de
organização de recursos e tarefas 
para uma empresa de assistência 
técnica.

## :scroll: Descrição do projeto

- Registro de clientes: Armazenar informações dos clientes que solicitarem 
os serviços da assistência técnica. Os dados armazenados devem incluir:
nome, endereço, número de telefone, entre outros;
- Gerenciamento de ordens de serviço: O técnico da assistência deve poder
criar e gerenciar ordens de serviço. Os tipos de serviço incluem: 
montagem/instalação de peças. formatação e instalação de sistemas. e limpeza;
- Agenda de atendimento: Os serviços devem ser realizados por ordem de
chegada. Cada técnico possui sua própria lista de serviços que devem 
ser realizados. Cada ordem possui o seu status atual de desenvolvimento, 
indicando se está em espera, em andamento, finalizada, ou cancelada;
- Gerenciamento de peças e estoque: O técnico pode gerenciar o estoque de 
peças necessárias para a realização dos serviços. Incluindo controle do
estoque, reabastecimento, e controle de custos;
- Faturamento e pagamento: É possível pagamento para os serviços prestados e 
aceitar diversos tipos de pagamentos;
- Relatório: É possível gerar relatórios para os serviços prestados;

## :computer: Status do projeto

- :heavy_check_mark: Fase 1: Análise dos requisitos. Montagem do Diagrama de Casos de Uso e Diagrama de 
Classes.
- :heavy_check_mark: Fase 2: Montagem do CRUD com o padrão de projeto DAO. Criação do Model e 
desenvolvimento dos testes de unidade.
- :x: Fase 3: Persistência de dados. Garantir que as informações sejam armazenadas e 
recuperadas de forma consistente.
- :x: Fase 4: Implementação da interface gráfica.

## :gear: Atuais funcionalidades

- Gerenciamento de clientes e técnicos:
<blockquote> Cadastrar</blockquote>
<blockquote> Atualizar número de ID automaticamente</blockquote>
<blockquote> Atualizar informações</blockquote>
<blockquote> Consultar cadastros por número de ID e CPF</blockquote>
<blockquote> Retornar toda a lista de cadastros do armazenamento</blockquote>
<blockquote> Checar cadastro por número de ID e CPF</blockquote>
<blockquote> Deletar cadastro por número de ID</blockquote>
<blockquote> Deletar toda a lista de armazenamento</blockquote>

- Gerenciamento de ordens de serviço:
<blockquote> Cadastrar</blockquote>
<blockquote> Atualizar número de ID automaticamente</blockquote>
<blockquote> Atualizar informações</blockquote>
<blockquote> Consultar dados por número de ID</blockquote>
<blockquote> Retornar toda a lista de cadastros do armazenamento</blockquote>
<blockquote> Retornar lista de ordens de serviço de um técnico</blockquote>
<blockquote> Retornar lista de ordens de serviço em aberto de um técnico</blockquote>
<blockquote> Checar cadastro por número de ID</blockquote>
<blockquote> Checar se o técnico possui alguma ordem de serviço em andamento</blockquote>
<blockquote> Deletar cadastro por número de ID</blockquote>
<blockquote> Deletar toda a lista de armazenamento</blockquote>

- Gerenciamento de peças:
<blockquote> Inserir</blockquote>
<blockquote> Inicializar as peças básicas dos requisitos</blockquote>
<blockquote> Atualizar informações</blockquote>
<blockquote> Remover e adicionar quantidades</blockquote>
<blockquote> Consultar dados pelo nome</blockquote>
<blockquote> Checagem de existência por nome</blockquote>
<blockquote> Checagem da quantidade de determinada peça</blockquote>
<blockquote> Retornar toda a lista do armazenamento</blockquote>
<blockquote> Retornar lista com as peças com quantidade abaixo do limite de alerta</blockquote>
<blockquote> Devolver peças de uma ordem de serviço cancelada</blockquote>
<blockquote> Deletar peça por nome</blockquote>
<blockquote> Deletar toda a lista de armazenamento</blockquote>
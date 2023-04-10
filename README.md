<h1 align="center">

  <p>Sistema de Gerenciamento de Microcomputadores</p>
  
</h1>

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
Classes
- :heavy_check_mark: Fase 2: Montagem do CRUD com o padrão de projeto DAO. Criação do Model e 
desenvolvimento dos testes de unidade
- :x: Fase 3: Persistência de dados. Garantir que as informações sejam armazenadas e 
recuperadas de forma consistente
- :x: Fase 4: Implementação da interface gráfica
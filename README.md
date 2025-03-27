# MedQuery

MedQuery é um sistema de gestão de consultas médicas para clinicas, que tem como objetivo disponibilizar funcionalidades que em conjunto possam gerenciar de forma efetiva um cenário real de uma clinica e suas filiais. O projeto visa fornecer funcionalidades como cadastro de clinicas, médicos e gestores, permitindo administrar da melhor forma possiveis os usuarios de cada clinica. O paciente poderá efetuar o proprio cadastro na plataforma para que possa ser realizado os agendamentos das consultas médicas.

## Funcionalidades

* CRUD de Usuários: Gestão de três tipos de usuários (paciente, médico e gestor), com funcionalidades completas de criação, leitura, atualização e exclusão.
* CRUD de Clínica: Permite o gerenciamento das clínicas cadastradas no sistema.
* CRUD de Agendamento: Facilita o agendamento de consultas entre pacientes e médicos.
* Relacionamento de Sintomas e Especialidades: Sistema de associação entre sintomas de um agendamento e as especialidades dos médicos cadastrados.
* Roles de Usuários: Cada usuário tem um papel específico (Gestor, Médico, Paciente) com permissões definidas pelo Spring Security.

## Tecnologias Utilizadas

* Java 17: Linguagem de programação.
* Spring Boot: Framework para desenvolvimento rápido de aplicações Java.
* Spring Security: Para gerenciamento de segurança e autenticação.
* Hibernate (JPA): ORM para facilitar a interação com o banco de dados.
* H2 Database: Banco de dados em memória utilizado para o armazenamento de dados.
* Thymeleaf: Template engine para renderização de páginas HTML.
* Maven: Gerenciador de dependências.

## Funcionalidades do Sistema

1- Login/ Cadastro

É possivel efetura login ou realizar o cadastro para acessar a plataforma.

Login:
![image](https://github.com/user-attachments/assets/f73755b5-9ac7-4305-8bdc-f20acdc45dd2)

Cadastro:
![image](https://github.com/user-attachments/assets/c77e5bd5-65f4-4481-9ab9-42729c2a1b35)

2- Gestão de Usuários

* Cadastro, edição e exclusão de pacientes, médicos, gestores e clinicas.
Atribuição de roles e permissões de acordo com o tipo de usuário.

![image](https://github.com/user-attachments/assets/df43d174-f88c-4e82-9838-771f71e7b412)

Cadastro de Clinicas:
![image](https://github.com/user-attachments/assets/43dccfcf-40fb-4975-ba3e-fafd0dd12b4f)

Listagem de Clinicas:
![image](https://github.com/user-attachments/assets/22da4c90-0f0e-40f5-8e58-6fdec2c165f2)

Cadastro de Médicos:
![image](https://github.com/user-attachments/assets/22a213d9-e9f2-4b8e-bd8d-126e349b874e)

Listagem de Médicos:
![image](https://github.com/user-attachments/assets/d7820823-3d0a-483f-8266-766040717e64)

Cadastro de Gestor:
![image](https://github.com/user-attachments/assets/86464acf-38b7-4f58-9789-530eef4e3e55)

Listagem de Gestores:
![image](https://github.com/user-attachments/assets/190e277a-78ca-47be-b7be-6bc9f6e06e97)

3- Gestão de Agendamentos

* Agendamento de consultas entre médicos e pacientes.
* Relacionamento entre sintomas e especialidades dos médicos.

Preencher Autoavaliação:
![image](https://github.com/user-attachments/assets/e869f290-ab23-4f95-b250-1adcc2916f21)

Selecionar Clinica disponivel:
![image](https://github.com/user-attachments/assets/b034e0c7-0c1c-47c5-8df5-f7fe37a93b31)

Selecionar Médico disponivel:
![image](https://github.com/user-attachments/assets/ed31b01d-4ece-4b45-a029-b1a322ffefee)

Confirmar data do Agendamento:
![image](https://github.com/user-attachments/assets/9903dc30-6d42-453e-a6ad-e0665b93b180)

Listagem de Agendamentos:
![image](https://github.com/user-attachments/assets/2aacf41e-6cf2-48aa-8d9d-ec3f3c1cf0b1)

4- Autenticação e Autorização

* Controle de acesso por meio do Spring Security, com páginas e funcionalidades restritas conforme o papel do usuário.

## Conclusão

Projeto desenvolvido em Java 17 utilizando o framework Spring Boot. O projeto utiliza H2 como banco de dados, Hibernate como ORM e possui funcionalidades para gerenciamento de usuários, clínicas, agendamentos e especialidades médicas. A aplicação conta com a integração de Spring Security para segurança e Thymeleaf para renderização de páginas HTML.

## Considerações 

Projeto desenvolvido para N2, Faculdade Engenheiro Salvador Arena, proposto pela disciplina:

- Linguagem de Progamação II (Prof. Israel Florentino).

## Integrantes

- Danilo Miranda - 081220021- linkedIn: https://www.linkedin.com/in/tognettidm/
- Hugo Victor Lima - 081220009 - linkedIn:  https://www.linkedin.com/in/hugo-victor-lima-9b5046247/
- Matheus Martins - 081220026 - linkedIn: https://www.linkedin.com/in/matheus-martins-70b955196/
- Matheus Pedroza - 081220002 - linkedIn: https://www.linkedin.com/in/matheus-pedroza/
- Thiago Souza - 081220013 - linkedIn: https://www.linkedin.com/in/thiagocicero/

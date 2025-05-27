# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** Natan José França
- **Matrícula:** 241011537
- **Curso:** Engenharia de Software
- **Turma:** 06

---
## Como obter o projeto

Você pode obter o projeto de duas formas:

### 1. Usando Git

Se você tem o Git instalado, basta clonar o repositório com o comando:

```sh
git clone https://github.com/Natan8643/Trabalho1-OO-Sistema-Academico.git
```

Depois, abra a pasta do projeto no Visual Studio Code.

### 2. Baixando o arquivo .zip

- Acesse o repositório no GitHub.
- Clique em **Code** > **Download ZIP**.
- Extraia o arquivo `.zip` em uma pasta de sua preferência.
- Abra a pasta extraída no Visual Studio Code, caso tenha o edito instalado.

---

## Instruções para Compilação e Execução

### 1. Compilação

Há duas formas para se compilar:

### 1.1 Visual Studio (VS Code) 

- Caso tenha o VS Code instalado, recomenda-se instalar a extensão **"Extension Pack for Java"** (ID: `vscjava.vscode-java-pack`) para facilitar a edição, compilação e execução de projetos Java.
    - Para instalar:  
      1. Abra o VS Code  
      2. Vá em **Extensões** (ícone de quadrados na barra lateral ou `Ctrl+Shift+X`)  
      3. Pesquise por `Extension Pack for Java`  
      4. Clique em **Instalar**

- Após instalar a extensão, você pode compilar o projeto diretamente pelo VS Code.

### 1.2 Terminal

Se preferir compilar pelo terminal, siga os passos abaixo:

1. Abra o terminal na pasta raiz do projeto.
2. Compile todos os arquivos `.java` da pasta `src` para a pasta `bin` com o comando:

   ```sh
   javac -d bin src\*.java
   ```

### 2. Execução

### 2.1 Visua Studio Code (VS Code)

- Caso tenha optado pelo VS Code:
   1. Abra o arquivo `App.java`
   2. Clique no botão de play ▶️ no canto superior direito para executar o programa.

### 2.2 Terminal

- Caso tenha optado pelo terminal:

Após a compilação, execute o programa principal (por exemplo, `App.java`) com:

   ```sh
   java -cp bin App
   ```

### 3. Observações

- É necessário ter o Java instalado (recomendado Java 21 ou superior).
- As principais pastas do projetao são as pastas `data` e `src`.

---

## Vídeo de Demonstração

- O vídeo de demonstração está disponível na pasta `media` do projeto.
- Para assistir, abra o arquivo `media/video-demo.mp4` (ou o nome do seu vídeo) com seu player de vídeo favorito.

---

## Prints da Execução

1. Menu Principal:  
   ![Print 1](media/Captura%20de%20tela%202025-05-26%20230035.png)

2. Cadastro de Aluno:  
   ![Print 2](media/Captura%20de%20tela%202025-05-26%20231828.png)

3. Relatório de Frequência/Notas:  
   ![Print 3](media/Captura%20de%20tela%202025-05-26%20232227.png)

---

## Principais Funcionalidades Implementadas

- [x] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [x] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [x] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [x] Lançamento de notas e controle de presença
- [x] Cálculo de média final e verificação de aprovação/reprovação
- [x] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [x] Persistência de dados em arquivos (.txt ou .csv)
- [x] Tratamento de duplicidade de matrículas
- [x] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- Foram adicionadas funções como trancamento de semestre e curso que impedem o usuário de cadastrar o aluno em uma disciplina.
- As dificuldades enfrentadas foram a persistência de dados e o aprendizado da linguagem Java, por ter vária funções desconhecidas, porém foi uma ótima linguagem para o aprendizado em Orientação a Objetos.

---

## Contato

- natan.j.franca@gmail.com

# Projeto 2 - Organograma Interativo de Empresa 🏢🌳

**Disciplina:** Estruturas de Dados  
**Instituição:** IFRN - Campus Parnamirim  
**Equipe:** [Filipe Conrado e Luiz Diogo]  

## 📌 Sobre o Projeto
Este projeto simula um sistema de gestão de Recursos Humanos (RH) focado no mapeamento visual de cargos e hierarquias de uma empresa. O sistema foi desenvolvido em **Java**, utilizando a estrutura de dados **Árvore N-ária (Árvore Genérica)**, já que no mundo corporativo um gestor pode ter múltiplos subordinados, não se limitando a apenas dois (como numa árvore binária).

O projeto é executado via console (terminal) de forma interativa através de um menu de opções.

## 🧠 Fundamentação Teórica: A Árvore N-ária

Para resolver o problema de mapeamento hierárquico corporativo, optamos por descartar a Árvore Binária tradicional e implementar uma **Árvore N-ária** (ou Árvore Genérica). No mundo real, um gestor raramente possui apenas dois subordinados. A Árvore N-ária resolve essa limitação permitindo que cada nó tenha "N" filhos, crescendo horizontalmente conforme a necessidade da empresa.

Mapeamento dos conceitos de Árvore para o nosso cenário de Negócios:

* **Nó (Node):** Representado pela classe `Funcionario`. É a unidade básica que armazena os dados (Nome e Cargo).
* **Raiz (Root):** O nível mais alto da hierarquia. No nosso projeto, é o **CEO**. É o único nó da árvore que não possui um "Nó Pai".
* **Arestas (Edges):** As ligações entre os nós. Aqui, representam a **relação de subordinação** (quem responde a quem).
* **Nós Filhos (Children):** Os subordinados diretos. Implementamos isso encapsulando uma Lista Dinâmica (`List<Funcionario>`) dentro de cada Nó, permitindo múltiplos subordinados.
* **Nós Folha (Leaves):** Os nós nas extremidades inferiores da árvore. Representam os **funcionários operacionais** (aqueles cuja lista de subordinados está vazia).
* **Profundidade/Nível:** Representa o "poder" hierárquico na empresa (quanto menor o nível numérico, mais próximo da Raiz/CEO, indicando maior autoridade).
---

## 🚀 Funcionalidades Implementadas

O sistema cumpre e expande os requisitos básicos solicitados, oferecendo as seguintes operações:

1. **Exibir Organograma (Percurso):** Navega pela árvore de forma recursiva e exibe a estrutura hierárquica completa utilizando indentação visual (`|--`) para representar os níveis/cargos.
2. **Adicionar Funcionário (Inserção):** Permite inserir um novo funcionário especificando quem será o seu chefe direto. O nó é adicionado na lista de subordinados do nó pai correspondente.
3. **Remover Funcionário (Remoção com Reestruturação):** Ao demitir um gestor, a árvore é reestruturada automaticamente. A equipe do gestor demitido passa a responder diretamente ao chefe superior imediato. A raiz (CEO) não pode ser removida.
4. **Buscar Cadeia Hierárquica (Busca em Profundidade):** Rastreia e exibe o caminho exato desde o CEO até o funcionário buscado, destacando o funcionário alvo na tela.
5. **Promover/Rebaixar (Movimentação de Nós):** Permite trocar o chefe direto de um funcionário (movendo a sub-árvore inteira da qual ele faz parte) e atualizar o seu cargo.
6. **Comparar Hierarquia:** Avalia o nível de profundidade de dois funcionários na árvore e informa qual deles possui maior poder hierárquico (está mais próximo da raiz).

---

## 💻 Como Compilar e Executar no NetBeans

Siga os passos abaixo para testar o sistema na sua máquina usando a IDE NetBeans:

1. **Importar o Projeto:**
   * Extraia o arquivo `.zip` do projeto (ou clone o repositório).
   * Abra o NetBeans.
   * Vá no menu superior e clique em `Arquivo` > `Abrir Projeto...` (ou `File` > `Open Project...`).
   * Navegue até a pasta extraída, selecione o projeto e clique em "Abrir Projeto".

2. **Limpar e Construir (Recomendado):**
   * Na barra de ferramentas superior do NetBeans, clique no ícone de **Vassoura com Martelo** (`Limpar e Construir Projeto` / `Clean and Build`). 
   * Aguarde aparecer a mensagem `BUILD SUCCESSFUL` na janela de saída (Output) na parte inferior. Isso garante que o código mais recente será executado.

3. **Executar o Sistema:**
   * Na aba "Projetos" (lado esquerdo), expanda as pastas até encontrar os arquivos fonte (`.java`).
   * Clique com o **botão direito** sobre o arquivo **`TesteOrganograma.java`**.
   * Selecione a opção **`Executar Arquivo`** (ou pressione `Shift + F6`).
   * O menu interativo aparecerá na janela de saída (Output) na parte inferior da tela. Clique dentro dessa janela para começar a digitar os comandos.

---

## 📖 Exemplos de Uso

Ao iniciar o programa, uma estrutura base (com CEO, Diretores e Gerentes) já é carregada para facilitar os testes. Abaixo um exemplo de fluxo de interação:

**Exemplo 1: Visualizando a Cadeia de Comando**
* No menu, digite `4` e pressione Enter.
* Digite o nome `Fernando` e pressione Enter.
* **Saída esperada:**
  ```text
  Cadeia Hierárquica para Fernando:
   -> Alice (CEO)
   -> Beto (Diretor de TI)
   -> Eduarda (Gerente de Software)
   -> ⭐ >>> FERNANDO (DESENVOLVEDOR SÊNIOR) <<< ⭐

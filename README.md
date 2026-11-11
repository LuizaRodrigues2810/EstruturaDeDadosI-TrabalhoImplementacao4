# Tarefa de Implementação 4: Map, Set e List

Estrutura de Dados I — SI-2026 — UNIFESSPA
Docente: Adam D. F. dos Santos
Linguagem: Java

## Estrutura do repositório

```
.
├── src/
│   ├── ex1/          # Relatório de Vendas
│   ├── ex2/          # Alunos em comum
│   ├── ex3/          # Enquete da turma
│   ├── ex4/          # Playlist sem repetições
│   ├── ex5/          # Inscrições em evento
│   └── ex6/          # Fila do RU
├── saida/            # saídas esperadas, para conferir o resultado dos programas
├── vendas.csv        # entrada do Ex. 1
├── votos.txt         # entrada do Ex. 3
├── playlist.txt      # entrada do Ex. 4
├── inscricoes.txt    # entrada do Ex. 5
├── fila.txt          # entrada do Ex. 6
├── .gitignore
└── README.md
```

Cada exercício fica na sua pasta `src/exN/`, dentro do pacote `exN`, com a classe que contém o `main`.
O Ex. 2 não usa arquivo, porque lê as matrículas pelo teclado.

## Como executar

O enunciado pede que os arquivos de entrada fiquem **na pasta onde o programa é executado**.
Por isso os arquivos de entrada ficam na **raiz do projeto**, que é a pasta de execução padrão do IntelliJ.

**Pelo IntelliJ:** abra a classe do exercício e clique no botão verde de play ao lado do `main`.
Confirme que a pasta `src` está marcada como Sources Root (ela aparece em azul). Se não estiver, clique com o
botão direito em `src` e escolha **Mark Directory as → Sources Root**.

**Pelo terminal (Git Bash), a partir da raiz do projeto:**

```bash
mkdir -p out
javac -encoding UTF-8 -d out src/ex1/*.java
java -cp out ex1.RelatorioVendas
```

Troque `ex1` e o nome da classe pelos do exercício que quiser rodar.

## Exercícios

| # | Tema | Entrada | Estruturas | Classe própria | Dificuldade |
|---|------|---------|------------|----------------|-------------|
| 1 | Relatório de Vendas | arquivo `vendas.csv` | `Map` (`TreeMap`), `Map` aninhado (desafio extra) | não | média |
| 2 | Alunos em comum | teclado | `ArrayList` + `Set` (união, interseção, diferença) | não | fácil |
| 3 | Enquete da turma | arquivo `votos.txt` + teclado (caminho) | `Set` + `Map` ordenado + `List` | não | fácil |
| 4 | Playlist sem repetições | arquivo `playlist.txt` + teclado | `List` + `Set` + `Map`, `equals`/`hashCode`/`compareTo`, `Collections.sort` | `Musica` | média |
| 5 | Inscrições em evento | arquivo `inscricoes.txt` + teclado | `Set` + `List` + `Map`, `equals`/`hashCode`/`compareTo` | `Participante` | média |
| 6 | Fila do RU | arquivo `fila.txt` + teclado | `LinkedList` + `HashSet` + `ArrayList` | não | média |

### O que cada exercício faz

1. **Relatório de Vendas** — lê `vendas.csv` (data;vendedor;produto;qtd;preço), acumula faturamento por vendedor
   e quantidade por produto usando `containsKey/get/put`, e exibe: faturamento ordenado, método
   `consultarVendedor`, total, média, melhor vendedor, participação %, acima da média e produto mais vendido.
   *Desafio extra:* `Map<String, Map<String, Integer>>` com o detalhamento por vendedor.
2. **Alunos em comum** — lê matrículas de ED e BD pelo teclado em `ArrayList` (ignorando repetidas com aviso, via
   `contains`), cria `Set`s e calcula: total distinto, interseção, só ED, só BD e % de alunos de ED que cursam BD.
3. **Enquete da turma** — lê o caminho do arquivo pelo teclado; só o primeiro voto de cada eleitor vale. Guarda os
   votos desconsiderados em ordem, conta votos válidos por opção em `Map` ordenado e exibe percentuais e vencedora.
4. **Playlist sem repetições** — classe `Musica` (`equals`/`hashCode` por título+artista, `compareTo` por título,
   `toString` com duração `m:ss`). Lê o arquivo sem repetições, mostra playlist numerada, duração total, música mais
   longa, tempo por artista, busca com `indexOf` e ordenação com `Collections.sort`.
5. **Inscrições em evento** — classe `Participante` (`equals`/`hashCode` só por e-mail, `compareTo` por nome). Remove
   duplicadas, ordena, conta inscritos por curso, informa o curso com mais inscritos e consulta e-mail com
   `contains`. Inclui a questão para reflexão (por que não usar só um set para remover duplicadas e ordenar?).
6. **Fila do RU** — simula eventos `CHEGA nome` / `ATENDE` com `LinkedList` (fila), `HashSet` (quem já almoçou) e
   `ArrayList` (ordem de atendimento). Mostra atendidos, fila final com espera estimada (posição × 3 min), entradas
   recusadas e consulta por nome até digitar `sair`.

## Equipe

| Discente | Exercícios |
|----------|------------|
| Maria Luiza O. Rodrigues | 1 e 2 |
| Karine Francisca Pardim Messias | 3 e 4 |
| João Paulo Sousa Mesquita | 5 e 6 |

## Divisão entre a equipe

Cada pessoa ficou com **2 exercícios**. Abram um branch por exercício (`ex1-nome`, `ex2-nome`, ...) e
atualizem a coluna "Status" quando concluírem (✅ concluído, ⬜ pendente).

| # | Exercício | Responsável | Branch | Status |
|---|-----------|-------------|--------|--------|
| 1 | Relatório de Vendas | Maria Luiza O. Rodrigues | ex1-luiza | ✅ |
| 2 | Alunos em comum | Maria Luiza O. Rodrigues | ex2-luiza | ⬜ |
| 3 | Enquete da turma | Karine Francisca Pardim Messias | ex3-karine | ✅ |
| 4 | Playlist sem repetições | Karine Francisca Pardim Messias | ex4-karine | ✅ |
| 5 | Inscrições em evento | João Paulo Sousa Mesquita | ex5-joao | ⬜ |
| 6 | Fila do RU | João Paulo Sousa Mesquita | ex6-joao | ⬜ |

## Convenções

- Cada exercício fica na sua pasta (`src/exN/`) e no seu pacote (`exN`), para evitar conflitos de merge.
- Classes auxiliares (`Musica`, `Participante`) ficam na pasta do exercício que as usa.
- Usar `Locale.setDefault(Locale.US)` no início do `main` (decimais com ponto).
- Mensagens e saídas devem seguir **exatamente** a "Saída esperada" do enunciado.
- Não versionar `out/` nem `.idea/` (já estão no `.gitignore`).(Locale.US)` no início do `main` (decimais com ponto).
- Mensagens e saídas devem seguir **exatamente** a "Saída esperada" do enunciado.

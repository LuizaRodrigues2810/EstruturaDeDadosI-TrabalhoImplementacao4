# Tarefa de Implementação 4: Map, Set e List

Estrutura de Dados I — SI-2026 — UNIFESSPA
Docente: Adam D. F. dos Santos
Linguagem: Java

## Estrutura do repositório

```
.
├── entrada/   # arquivos de entrada (vendas.csv, votos.txt, playlist.txt, inscricoes.txt, fila.txt)
├── saida/     # saídas esperadas / geradas para conferência
├── src/
│   ├── ex1/   # Relatório de Vendas
│   ├── ex2/   # Alunos em comum
│   ├── ex3/   # Enquete da turma
│   ├── ex4/   # Playlist sem repetições
│   ├── ex5/   # Inscrições em evento
│   └── ex6/   # Fila do RU
├── .gitignore
└── README.md
```

> **Atenção:** o enunciado pede que os arquivos de entrada fiquem **na pasta onde o programa é executado**
> (`new File("vendas.csv")`). Por isso, rode cada programa de dentro de `entrada/`, por exemplo:
>
> ```bash
> cd entrada
> javac -d ../out ../src/ex1/*.java
> java -cp ../out ex1.Main
> ```
>
> (cada exercício em seu próprio pacote `exN`, com a classe `Main`).

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

## Divisão entre a equipe

Cada pessoa escolhe **2 exercícios**. Preencham a coluna "Responsável" e abram um branch por exercício
(`ex1-nome`, `ex2-nome`, ...).

| # | Exercício | Responsável | Branch | Status |
|---|-----------|-------------|--------|--------|
| 1 | Relatório de Vendas | | | ⬜ |
| 2 | Alunos em comum | | | ⬜ |
| 3 | Enquete da turma | | | ⬜ |
| 4 | Playlist sem repetições | | | ⬜ |
| 5 | Inscrições em evento | | | ⬜ |
| 6 | Fila do RU | | | ⬜ |

## Convenções

- Cada exercício fica no seu pacote (`src/exN/`), para evitar conflitos de merge.
- Usar `Locale.setDefault(Locale.US)` no início do `main` (decimais com ponto).
- Mensagens e saídas devem seguir **exatamente** a "Saída esperada" do enunciado.

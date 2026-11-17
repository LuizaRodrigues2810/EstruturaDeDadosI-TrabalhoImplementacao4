package ex5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Inscricoes {
    void main() {
        Locale.setDefault(Locale.US);
        Set<Participante> participantes = new LinkedHashSet<>();
        int recebidas = 0;
        // 1. leitura do arquivo e remoção de duplicatas
        try (Scanner leitorArquivo = new Scanner(new File("inscricoes.txt"), "UTF-8")) {
            while (leitorArquivo.hasNextLine()) {
                String linha = leitorArquivo.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                recebidas++;
                String[] partes = linha.split(";");
                Participante p = new Participante(partes[0], partes[1], partes[2]);
                participantes.add(p);
            }
        } catch (FileNotFoundException e) {
            IO.println("Arquivo inscricoes.txt não encontrado.");
            return;
        }
        IO.println("Inscrições recebidas: " + recebidas);   
        IO.println("Participantes únicos: " + participantes.size());
        IO.println("Inscrições duplicadas: " + (recebidas - participantes.size()));
        // 2. copia para arraylist e ordena pelo nome
        List<Participante> lista = new ArrayList<>(participantes);
        Collections.sort(lista);
        IO.println("\nParticipantes em ordem alfabética:");
        for (Participante p : lista) {
            IO.println(p);
        }
        // 3. contagem por curso
        Map<String, Integer> porCurso = new TreeMap<>();
        for (Participante p : lista) {
            String curso = p.getCurso();
            if (porCurso.containsKey(curso)) {
                porCurso.put(curso, porCurso.get(curso) + 1);
            } else {
                porCurso.put(curso, 1);
            }
        }
        IO.println("\nInscritos por curso:");
        String cursoMaisInscritos = "";
        int maiorQtd = -1;
        for (String curso : porCurso.keySet()) {
            int qtd = porCurso.get(curso);
            IO.println(curso + ": " + qtd);
            if (qtd > maiorQtd) {
                maiorQtd = qtd;
                cursoMaisInscritos = curso;
            }
        }
        IO.println("Curso com mais inscritos: " + cursoMaisInscritos);
        // 4. consulta por email
        Scanner teclado = new Scanner(System.in);
        IO.print("\nConsultar e-mail: ");
        String emailBusca = teclado.nextLine().trim();
        Participante busca = new Participante("", emailBusca, "");
        if (participantes.contains(busca)) {
            IO.println(emailBusca + " está inscrito(a).");
        } else {
            IO.println(emailBusca + " não está inscrito(a).");
        }
        teclado.close();
    }
}
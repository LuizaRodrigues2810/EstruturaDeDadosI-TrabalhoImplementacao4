package ex3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Enquete {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        Scanner teclado = new Scanner(System.in);
        System.out.print("Informe o caminho do arquivo: ");
        String caminho = teclado.nextLine().trim();
        System.out.println("Pasta de trabalho: " + System.getProperty("user.dir"));
        System.out.println("Procurando em: " + new File(caminho).getAbsolutePath());


        Set<String> jaVotaram = new HashSet<>();
        List<String> desconsiderados = new ArrayList<>();
        Map<String, Integer> votos = new TreeMap<>();
        int totalValidos = 0;

        try (Scanner arquivo = new Scanner(new File(caminho), "UTF-8")) {
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                String[] campos = linha.split(";");
                String eleitor = campos[0].trim();
                String opcao = campos[1].trim();

                if (jaVotaram.add(eleitor)) {
                   
                    if (votos.containsKey(opcao)) {
                        votos.put(opcao, votos.get(opcao) + 1);
                    } else {
                        votos.put(opcao, 1);
                    }
                    totalValidos++;
                } else {
                    
                    desconsiderados.add(eleitor);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + caminho);
            return;
        }

        System.out.println("Votos desconsiderados (eleitor repetido): " + desconsiderados);
        System.out.println("Total de votos válidos: " + totalValidos);

        System.out.println("Resultado:");
        String vencedora = null;
        int maior = -1;
        for (String opcao : votos.keySet()) {
            int qtd = votos.get(opcao);
            double percentual = 100.0 * qtd / totalValidos;
            System.out.printf("%s: %d votos (%.2f%%)%n", opcao, qtd, percentual);
            if (qtd > maior) { // em caso de empate, vence a primeira em ordem alfabética
                maior = qtd;
                vencedora = opcao;
            }
        }
        System.out.println("Vencedora: " + vencedora);
    }
}


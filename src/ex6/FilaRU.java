package ex6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class FilaRU {
    void main() {
        Locale.setDefault(Locale.US);
        LinkedList<String> fila = new LinkedList<>();
        Set<String> jaAlmocaram = new HashSet<>();
        List<String> atendidos = new ArrayList<>();
        int recusadas = 0;
        // 1. leitura do arquivo e simulação dos eventos da fila
        try (Scanner leitorArquivo = new Scanner(new File("fila.txt"), "UTF-8")) {
            while (leitorArquivo.hasNextLine()){
                String linha = leitorArquivo.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                if (linha.equals("ATENDE")) {
                    if (fila.isEmpty()){
                        IO.println("ATENDE: fila vazia, ninguém para atender");
                    } else {
                        String atendido = fila.removeFirst();
                        atendidos.add(atendido);
                        jaAlmocaram.add(atendido);
                        IO.println("ATENDE: " + atendido);
                    }
                } else if (linha.startsWith("CHEGA ")){
                    String nome = linha.substring(6).trim();
                    if (fila.contains(nome)) {
                        IO.println("CHEGA " + nome + ": já está na fila");
                        recusadas++;
                    } else if (jaAlmocaram.contains(nome)){
                        IO.println("CHEGA " + nome + ": já almoçou hoje");
                        recusadas++;
                    } else {
                        fila.addLast(nome);
                        IO.println("CHEGA " + nome + ": entrou na fila");
                    }
                }
            }
        } catch (FileNotFoundException e){
            IO.println("Arquivo fila.txt não encontrado.");
            return;
        }
        // 2. resumo após os eventos do arquivo
        IO.println("\nAtendidos (" + atendidos.size() + "): " + atendidos);
        IO.println("Ainda na fila (" + fila.size() + "):");
        for (int i = 0; i < fila.size(); i++) {
            int posicao = i + 1;
            int espera = posicao * 3;
            IO.println(posicao + ". " + fila.get(i) + " - espera estimada: " + espera + " min");
        }
        IO.println("Entradas recusadas: " + recusadas);
        // 3. consulta pelo teclado usando indexOf
        Scanner teclado = new Scanner(System.in);
        IO.println("\nConsulta (digite \"sair\" para encerrar)");
        while (true) {
            IO.print("Nome: ");
            String nome = teclado.nextLine().trim();
            if (nome.equalsIgnoreCase("sair")) {
                break;
            }
            if (nome.isEmpty()) {
                continue;
            }
            int indice = fila.indexOf(nome);
            if (indice != -1) {
                IO.println(nome + " está na fila, posição " + (indice + 1) + ".");
            } else if (jaAlmocaram.contains(nome)) {
                IO.println(nome + " já almoçou.");
            } else {
                IO.println(nome + " não passou pelo RU hoje.");
            }
        }
        teclado.close();
    }
}

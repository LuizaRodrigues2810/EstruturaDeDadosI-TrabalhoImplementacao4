package ex4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Playlist {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        List<Musica> playlist = new ArrayList<>();
        Set<Musica> jaVistas = new HashSet<>(); // detecta repetidas via equals/hashCode
        int repetidas = 0;

        // ---- Leitura sem repetições ----
        try (Scanner arquivo = new Scanner(new File("playlist.txt"), "UTF-8")) {
            while (arquivo.hasNextLine()) {
                String linha = arquivo.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                String[] campos = linha.split(";");
                Musica m = new Musica(campos[0].trim(), campos[1].trim(),
                        Integer.parseInt(campos[2].trim()));

                if (jaVistas.add(m)) {
                    playlist.add(m); // só entra se ainda não foi vista (mantém a ordem do arquivo)
                } else {
                    repetidas++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: playlist.txt");
            return;
        }

        // ---- Cálculos ----
        System.out.println("Músicas repetidas ignoradas: " + repetidas);
        System.out.println("Playlist (" + playlist.size() + " músicas):");

        int total = 0;
        Musica maisLonga = null;
        Map<String, Integer> tempoPorArtista = new TreeMap<>();

        for (int i = 0; i < playlist.size(); i++) {
            Musica m = playlist.get(i);
            System.out.println((i + 1) + ". " + m);

            total += m.getDuracao();
            if (maisLonga == null || m.getDuracao() > maisLonga.getDuracao()) {
                maisLonga = m;
            }

            String artista = m.getArtista();
            if (tempoPorArtista.containsKey(artista)) {
                tempoPorArtista.put(artista, tempoPorArtista.get(artista) + m.getDuracao());
            } else {
                tempoPorArtista.put(artista, m.getDuracao());
            }
        }

        System.out.println("Duração total: " + Musica.formatarDuracao(total));
        System.out.println("Música mais longa: " + maisLonga);
        System.out.println("Tempo por artista:");
        for (String artista : tempoPorArtista.keySet()) {
            System.out.println(artista + ": " + Musica.formatarDuracao(tempoPorArtista.get(artista)));
        }

        // ---- Busca com indexOf (usa equals) ----
        Scanner teclado = new Scanner(System.in);
        System.out.println("Buscar música");
        System.out.print("Título: ");
        String titulo = teclado.nextLine().trim();
        System.out.print("Artista: ");
        String artista = teclado.nextLine().trim();


        int posicao = playlist.indexOf(new Musica(titulo, artista, 0));
        if (posicao >= 0) {
            System.out.println(titulo + " está na posição " + (posicao + 1) + " da playlist.");
        } else {
            System.out.println(titulo + " não foi encontrada na playlist.");
        }

        // ---- Ordenação (compareTo por título) ----
        Collections.sort(playlist);
        System.out.println("Em ordem alfabética:");
        for (Musica m : playlist) {
            System.out.println(m);
        }
    }
}


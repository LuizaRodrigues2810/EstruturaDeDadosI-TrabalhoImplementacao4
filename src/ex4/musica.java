package ex4;

import java.util.Objects;

public class Musica implements Comparable<Musica> {

    private final String titulo;
    private final String artista;
    private final int duracao; // em segundos

    public Musica(String titulo, String artista, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getDuracao() {
        return duracao;
    }

    /** Converte segundos para o formato m:ss (ex.: 250 -> "4:10"). */
    public static String formatarDuracao(int segundos) {
        return String.format("%d:%02d", segundos / 60, segundos % 60);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Musica outra = (Musica) o;
        return titulo.equals(outra.titulo) && artista.equals(outra.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista);
    }

    // Ordem natural: alfabética pelo título
    @Override
    public int compareTo(Musica outra) {
        return titulo.compareTo(outra.titulo);
    }

    @Override
    public String toString() {
        return titulo + " - " + artista + " (" + formatarDuracao(duracao) + ")";
    }
}


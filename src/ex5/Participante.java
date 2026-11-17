package ex5;

import java.util.Objects;

public class Participante implements Comparable<Participante>{
    private String nome;
    private String email;
    private String curso;

    public Participante(String nome, String email, String curso) {
        this.nome = nome;
        this.email = email;
        this.curso = curso;
    }

    @Override
    public int compareTo(Participante outro){
        return this.nome.compareTo(outro.nome);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // retorna true caso o objeto seja o mesmo
        if (o == null || getClass() != o.getClass()) return false; // retorna falso caso o objeto seja null ou de outra classe (ou seja, não é Participante)
        Participante outro = (Participante) o; // realiza um casting do objeto genérico o para a classe Participante
        return Objects.equals(email, outro.email); // retorna true caso o email seja igual
    }

    @Override
    public int hashCode() {
        return Objects.hash(email); // gera um código hash baseado no email
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    @Override
    public String toString() {
        return nome + " (" + email + ") - " + curso;
    }
}
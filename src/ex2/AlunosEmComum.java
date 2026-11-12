package ex2;

import java.util.*;

public class AlunosEmComum {

    private final List<Integer> alunosED = new ArrayList<>();
    private final List<Integer> alunosBD = new ArrayList<>();

    public void lerED(Scanner teclado){
        lerMatriculas(teclado, "ED", alunosED);
    }
    public void lerBD(Scanner teclado){
        lerMatriculas(teclado, "BD", alunosBD);
    }

    private void lerMatriculas(Scanner teclado, String disciplina, List<Integer> destino){
        IO.println("Quantos alunos em " + disciplina + "? ");
        int quantidade = teclado.nextInt();

        for(int i = 0; i < quantidade; i++){
            int matricula = teclado.nextInt();
            if(destino.contains(matricula)){
                IO.println("Matrícula " + matricula + " repetida, ignorada.");
            }else{
                destino.add(matricula);
            }
        }
    }
    public void exibirResultados(){
        Set<Integer> setED = new TreeSet<>(alunosED);
        Set<Integer> setBD = new TreeSet<>(alunosBD);

        Set<Integer> uniao = new TreeSet<>(setED);
        uniao.addAll(setBD);

        Set<Integer> ambas = new TreeSet<>(setED);
        ambas.retainAll(setBD);

        Set<Integer> somenteED = new TreeSet<>(setED);
        somenteED.removeAll(setBD);

        Set<Integer> somenteBD = new TreeSet<>(setBD);
        somenteBD.removeAll(setED);

        double percentual = 0;
        if(!setED.isEmpty()){
            percentual = (double) ambas.size() / setED.size() * 100;
        }

        IO.println("Total de alunos distintos: " + uniao.size());
        IO.println("Cursam as duas disciplinas: " + ambas);
        IO.println("Somente ED: " + somenteED);
        IO.println("Somente BD: " + somenteBD);
        System.out.printf("Alunos de ED que também cursam BD: %.2f%%%n",percentual);

    }
    public static void main(String[] args){
        Locale.setDefault(Locale.US);
        Scanner teclado = new Scanner(System.in);

        AlunosEmComum turmas = new AlunosEmComum();
        turmas.lerED(teclado);
        turmas.lerBD(teclado);
        turmas.exibirResultados();
    }
}

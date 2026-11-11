package ex1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RelatorioVendas {

    private final Map<String, Double> faturamentoPorVendedor = new TreeMap<>();
    private final Map<String, Integer> quantidadePorProduto = new TreeMap<>();
    private final Map<String, Map<String, Integer>> detalhamento = new TreeMap<>();

    public boolean lerArquivo(String nomeArquivo) {
        try (Scanner leitor = new Scanner(new File("vendas.csv"))) {
            while (leitor.hasNextLine()) {
                String linha = leitor.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }

                String[] campos = linha.split(";");
                String vendedor = campos[1];
                String produto = campos[2];
                int quantidade = Integer.parseInt(campos[3]);
                double preco = Double.parseDouble(campos[4]);
                double valorVenda = quantidade * preco;

                if(faturamentoPorVendedor.containsKey(vendedor)){
                    double atual = faturamentoPorVendedor.get(vendedor);
                    faturamentoPorVendedor.put(vendedor, atual + valorVenda);
                }else{
                    faturamentoPorVendedor.put(vendedor, valorVenda);
                }

                if(quantidadePorProduto.containsKey(produto)){
                    int atual = quantidadePorProduto.get(produto);
                    quantidadePorProduto.put(produto, atual + quantidade);
                }else{
                    quantidadePorProduto.put(produto,quantidade);
                }

                if(!detalhamento.containsKey(vendedor)){
                    detalhamento.put(vendedor, new TreeMap<>());
                }
                Map<String,Integer> produtoDoVendedor = detalhamento.get(vendedor);
                if(produtoDoVendedor.containsKey(produto)){
                    int atual = produtoDoVendedor.get(produto);
                    produtoDoVendedor.put(produto,atual + quantidade);
                }else{
                    produtoDoVendedor.put(produto,quantidade);
                }
            }
            return true;

        }catch (FileNotFoundException e){
            IO.println("Arquivo " + nomeArquivo + " não encontrado." + " Execute o programa na pasta onde ele está");
            return false;
        }
    }

    public void exibirFaturamentoPorVendedor(){
        IO.println("Faturamento por vendedor: ");
        for(String vendedor : faturamentoPorVendedor.keySet()){
            System.out.printf("%s: R$ %.2f%n", vendedor, faturamentoPorVendedor.get(vendedor));
        }
    }
   public void consultarVendedor(String nome){
        if(faturamentoPorVendedor.containsKey(nome)){
            System.out.printf("Consulta %s: R$ %.2f%n", nome, faturamentoPorVendedor.get(nome));
        }else{
            IO.println("Consulta " + nome + ": vendedor não encontrado");
        }
   }
   public void exibirEstatisticas(){
        double total = 0.0;
        for(double valor : faturamentoPorVendedor.values()){
            total += valor;
        }
        System.out.printf("Faturamento total: R$ %.2f%n", total);

        double media = total / faturamentoPorVendedor.size();
        System.out.printf("Média por vendendor: R$ %.2f%n", media);

        String melhorVendedor = null;
        double melhorValor = 0.0;
        for(String vendedor : faturamentoPorVendedor.keySet()){
            double valor = faturamentoPorVendedor.get(vendedor);
            if(melhorVendedor == null || valor > melhorValor){
                melhorVendedor = vendedor;
                melhorValor = valor;
            }
        }
        System.out.printf("Melhor vendedor: %s (R$ %.2f)%n", melhorVendedor, melhorValor);
        IO.println("Participação: ");
        for(String vendedor : faturamentoPorVendedor.keySet()){
            double percentual = faturamentoPorVendedor.get(vendedor) / total * 100;

            System.out.printf("%s: %.2f%%%n", vendedor, percentual);
        }

        List<String> acimaDaMedia = new ArrayList<>();
        for(String vendedor : faturamentoPorVendedor.keySet()){
            if(faturamentoPorVendedor.get(vendedor) > media){
                acimaDaMedia.add(vendedor);
            }
        }
        IO.println("Acima da média: " + (acimaDaMedia.isEmpty() ? "nenhum" : String.join(", ", acimaDaMedia)));
   }
   public void exibirProdutos(){
        IO.println("Quantidade por produto: ");
        String produtoMaisVendido = null;
        int maiorQuantidade = 0;
        for(String produto : quantidadePorProduto.keySet()){
            int quantidade = quantidadePorProduto.get(produto);
            IO.println(produto + ": " + quantidade);
            if(produtoMaisVendido == null || quantidade > maiorQuantidade){
                produtoMaisVendido = produto;
                maiorQuantidade = quantidade;
            }
        }
        IO.println("Produto mais vendido: " + produtoMaisVendido + " (" + maiorQuantidade + " unidades)");
   }
   public void exibirDetalhamento(){
        IO.println("Detalhamento por vendedor: ");
        for(String vendedor : detalhamento.keySet()){
            IO.println(vendedor);
            Map<String, Integer> produtos = detalhamento.get(vendedor);
            for(String produto : produtos.keySet()){
                IO.println(" " + produto + ": " + produtos.get(produto));
            }
        }
   }

   public static void main(String[] args){
        Locale.setDefault(Locale.US);

        RelatorioVendas relatorio = new RelatorioVendas();
        if(!relatorio.lerArquivo("vendas.csv")){
            return;
        }

        relatorio.exibirFaturamentoPorVendedor();
        IO.println();
        relatorio.consultarVendedor("Bruno");
        IO.println();
        relatorio.consultarVendedor("Diego");
        IO.println();
        relatorio.exibirEstatisticas();
        IO.println();
        relatorio.exibirProdutos();
        IO.println();
        relatorio.exibirDetalhamento();
   }
}

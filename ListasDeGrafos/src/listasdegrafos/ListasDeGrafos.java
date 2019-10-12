package listasdegrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ListasDeGrafos {

    public static void main(String[] args) {
        AlgoritmosEmGrafos grafo = lerArquivo(args[0]);
        int pai[];
        grafo.iniciaBuscaEmProfundidade(0);
        pai = grafo.getVerticePai();
        
        for(int i=0; i<pai.length; i++){
            System.out.println(pai[i]);
        }
        System.out.println("");
        for(int i=0; i<pai.length; i++){
            System.out.println(grafo.getDistanciaProfundidade()[i]);
        }
    }
    
    public static AlgoritmosEmGrafos lerArquivo(String arg) {
        try {
            FileReader arq = new FileReader(arg);
            BufferedReader lerArq = new BufferedReader(arq);
            int[] vertices = new int[2];
            int peso;
            
            String linha = lerArq.readLine();
            vertices[0] = Integer.parseInt(linha.split(" ")[0]);
            vertices[1] = Integer.parseInt(linha.split(" ")[1]);
                
            AlgoritmosEmGrafos grafo = new AlgoritmosEmGrafos(vertices[0]);
            linha = lerArq.readLine();
            //System.out.println(vertices[0] + " " + vertices[1]);
            while (linha != null) {
                vertices[0] = Integer.parseInt(linha.split(" ")[0]);
                vertices[1] = Integer.parseInt(linha.split(" ")[1]);
                peso = Integer.parseInt(linha.split(" ")[2]);
                //System.out.println(vertices[0] + " " + vertices[1] + " " + peso);
                grafo.insereAresta(vertices[0], vertices[1], peso);
                
                linha = lerArq.readLine(); // lê da segunda até a última linha
            }
            arq.close();
            return grafo;
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n",
            e.getMessage());
            return null; 
        }
    }
}

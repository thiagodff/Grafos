package listasdegrafos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
//import javafx.util.Pair; // utilizado para AGM

public class ListasDeGrafos {

    public static void main(String[] args) {
        AlgoritmosEmGrafos grafo = lerArquivo(args[0]);
        int fluxoTotal = 0;
        
        grafo.iniciaFluxoMaximoEmRedes(0, 5);
        ArrayList < ArrayList < Integer >> caminhosDeAumentoFR = grafo.getCaminhosFluxoRedes();
        ArrayList < Integer> caminhoResidual = grafo.getArestaMenorPeso();
        
        for (int i=0; i<caminhosDeAumentoFR.size(); i++){
            System.out.println("Caminho no grafo(invertido): " + caminhosDeAumentoFR.get(i));
            System.out.println("Peso da menor aresta: " + caminhoResidual.get(i) + "\n");
            fluxoTotal += caminhoResidual.get(i);
        }
        System.out.println("Fluxo Total: " + fluxoTotal);
        
    }
    
    /**
    * Lista todos os caminhos a partir do verticeInicial
    */
    public static void listaCMC(int pai[], int distancias[], int verticeInicial) {
        int caminho[] = new int[pai.length];
        for(int i=0; i<pai.length; i++) {
            System.out.println(verticeInicial + " -> " + i + " custo: " + distancias[i]);
            if (distancias[i] == Integer.MAX_VALUE){
                System.out.println("Não contém pai");
            } else {
                caminho[0] = i;
                int j=1, pap = pai[i];
                while(pap!=-1) {
                    caminho[j] = pap;
                    pap = pai[pap];
                    j++;
                }
                while(j!=0) {
                    System.out.print(caminho[--j] + " ");
                }
                System.out.println("");
            }
        }
    }
    
    /**
    * Lista apenas um caminho a partir do verticeInicial
    */
    public static void listaCMC(int pai[], int distancia, int verticeInicial,int verticeFinal) {
        int caminho[] = new int[pai.length];
        System.out.println(verticeInicial + " -> " + verticeFinal + " custo: " + distancia);
        if (distancia == Integer.MAX_VALUE){
            System.out.println("Não contém pai");
        } else {
            caminho[0] = verticeFinal;
            int j=1, pap = pai[verticeFinal];
            while(pap!=-1) {
                caminho[j] = pap;
                pap = pai[pap];
                j++;
            }
            while(j!=0) {
                System.out.print(caminho[--j] + " ");
            }
            System.out.println("");
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

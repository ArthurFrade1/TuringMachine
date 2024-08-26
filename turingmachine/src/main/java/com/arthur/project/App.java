package com.arthur.project;

import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Paths;
import org.json.*;

public class App {

    private ArrayList<Estado> Estados= new ArrayList<>();

    public void achaEstadoInicial(String estadoInicial){
        //Rodo todos os estados até achar o com o estado final
        for (Estado estado : Estados) {
            if(estado.getNome().equals(estadoInicial)){
                estado.setInicial();
            }
        }
    }

    public void achaEstadosFinais(JSONArray estadosFinais){
        for(int i=0; i<estadosFinais.length(); i++){
            String estadoFinal=estadosFinais.getString(i); //Para cada estado final que encontro no json de estados finais rodo todos os estados pra encontra-lo
            for (Estado estado : Estados) {
                if(estado.getNome().equals(estadoFinal)){//Achou
                    estado.setFinal();
                }
            }
        }
    }

    public Estado procuraEstado(String estadoInicial){ 
        //Procura e retorna o estado com o nome passado como parãmetro
        for (Estado estado : Estados) {
            if (estado.getNome().equals(estadoInicial)) {//Procuro o com nome igual ao Inicial
                return estado;
            }
        }
        System.out.println("THIS IS BAD");
        return null;
    }

    public void criaTransicoes(JSONArray transicoes){

        for(int i=0; i<transicoes.length(); i++){

            JSONArray transicao=transicoes.getJSONArray(i); //Pego somente uma transição

            System.out.println("transição "+(i+1));
            System.out.println(transicao);

            //Pegando elementos da transição
            String estadoInicial= transicao.getString(0);
            String le= transicao.getString(1);
            String estadoFinal= transicao.getString(2);
            String escreve= transicao.getString(3);
            String direcao= transicao.getString(4);

            //Acha estado correspondente
            Estado estado = procuraEstado(estadoInicial);
            
            //Cria transição
            estado.adicioanarTransição(new Transicao(le, escreve, estadoFinal, direcao));
        }
    }

    public void criaEstados(JSONArray estados){
        String estado;
        for(int i=0; i<estados.length(); i++){
            estado= estados.getString(i); //Obtendo o nome do estado
            Estados.add(new Estado(estado));//Crio novo estado e adiciona na lista de estados
        }
    }

    public void leJson(){

        try {
            //Lë arquivo
            String filePath = "src/main/java/com/arthur/project/mt.json";
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));

            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray mtArray = jsonObject.getJSONArray("mt");

            // Acessando o array estados
            JSONArray estados = mtArray.getJSONArray(0);
            criaEstados(estados);

            // Acessando o array transições
            JSONArray transicoes = mtArray.getJSONArray(5);
            criaTransicoes(transicoes);

            //Acessando o estado inicial
            String estadoInicial=mtArray.getString(6);
            achaEstadoInicial(estadoInicial);

            //Acessando o array de estado finais
            JSONArray estadosFinais=mtArray.getJSONArray(7);
            achaEstadosFinais(estadosFinais);

            test();

            String palavra="";
            //Chama a classe do processamento
            Processamento processamento = new Processamento(Estados, palavra);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test(){
        for (Estado estado : Estados) {
    
            System.out.println("\nEstado: "+estado.getNome()+"\nTransicoes:");
            for (Transicao tran : estado.transicoes) {
                
                System.out.println(tran.le+tran.escreve+tran.nomeEstado+tran.direcao);
            }
            System.out.println("Estado inicial: "+estado.isInicial);
            System.out.println("Estado final: "+estado.isFinal);
        }
        
    }
    public static void main(String[] args) {
        App app = new App();
        app.leJson();

        
    }
}

package com.arthur.project;
import java.util.ArrayList;
public class Processamento {
    private ArrayList<Estado> Estados;
    private String palavra="0";
    


    private boolean houveTransicao;
    // private Estado estadoAtual;
    public Processamento(ArrayList<Estado> Estados, String palavra){
        this.Estados=Estados;
        // this.palavra=palavra;
        processa();
    }

    public void preencheFita(ArrayList<String> fita){
        fita.add("[");
        for(int i=0; i<palavra.length(); i++){
            fita.add(String.valueOf(palavra.charAt(i)));
        }
        for(int i=0; i<100; i++){ //Número generico
            fita.add("_");
        }
    }

    public Estado achaEstadoInicial(){
        for (Estado estado : Estados) {
            if(estado.getIsInicial()){
                return estado;
            }
        }
        return null;
    }

    public boolean possiveisTransicoes(Estado estadoAtual, ArrayList<String> fita, int indexFita){
        ArrayList<Transicao> transicoes= estadoAtual.getTransicoes();

        String simboloAtual=fita.get(indexFita);

        boolean achou=false;
        for (Transicao transicao : transicoes) {
            if(transicao.getLe().equals(simboloAtual)){//Achou um transicao que le o simbolo

                System.out.println("ESTAMOS NO ESTADO: "+estadoAtual.getNome());
                printaFita(fita);
                estadoAtual= transiciona(estadoAtual, transicao, fita, indexFita);

                //Anda na fita
                String direcao = transicao.getDirecao();
                if(direcao.equals(">")) indexFita++;
                if(direcao.equals("<")) indexFita--;

                boolean resp=possiveisTransicoes(estadoAtual, fita, indexFita);
                if(!resp){
                    if(direcao.equals(">")) indexFita--;
                    if(direcao.equals("<")) indexFita++;
                }

                achou = achou || resp;
            }
        }
        //O estado rodou todas as recursões possiveis
        //Verificar se parou no estado final
        if(estadoAtual.getIsFinal()) return true;
        else return achou;
    }

    public Estado transiciona(Estado estadoAtual, Transicao transicao, ArrayList<String> fita, int indexFita){
 
        String escreve = transicao.getEscreve();
        fita.set(indexFita, escreve);

        //  -----------------  SOMENTE PARA O CASO SENDO DETERMINISTIICO
        //Procura estado pra onde vai
        String nomeEstado= transicao.getNomeEstado();
        for (Estado estado : Estados) {
            if(estado.getNome().equals(nomeEstado)){
                return estado;
            }
        }
        
        
        return estadoAtual;
    }

    public void processa(){
        ArrayList<String> fita = new ArrayList<>();
        preencheFita(fita);
        printaFita(fita);
        //Começamos pelo estado inicial
        Estado estadoAtual = achaEstadoInicial();


        boolean resp=possiveisTransicoes(estadoAtual, fita, 1);

        if(resp) System.out.println("Sim");
        else System.out.println("Não");

    }

    public void printaFita(ArrayList<String> fita){
        for(int i=0; i<10; i++){
            System.out.println(fita.get(i));
        }
    }

    
}


package com.arthur.project;
import java.util.ArrayList;
public class Processamento {
    private ArrayList<Estado> Estados;
    private String palavra="aaabb";
    private ArrayList<String> fita = new ArrayList<>();

    private int indexFita=1; //Sempre começa no segundo simbolo

    private boolean houveTransicao;
    // private Estado estadoAtual;
    public Processamento(ArrayList<Estado> Estados, String palavra){
        this.Estados=Estados;
        // this.palavra=palavra;
        processa();
    }

    public void preencheFita(){
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

    public Estado transiciona(Estado estadoAtual){
        //Procurar na lista de transições do estado algum "le" que seja igual ao simboloAtual
        ArrayList<Transicao> transicoes = estadoAtual.getTransicoes(); //Pego suas transições

        String simboloAtual=fita.get(indexFita);

        houveTransicao=false; //seto como falso
        for (Transicao transicao: transicoes){ //Pego uma por uma
            if(transicao.getLe().equals(simboloAtual)){ //Achou um transicao que le o simbolo

                //Se exsite pelo menos uma transação compativel
                houveTransicao=true;

                String escreve = transicao.getEscreve();
                fita.set(indexFita, escreve);

                //Anda na fita
                String direcao = transicao.getDirecao();
                if(direcao.equals(">")) indexFita++;
                if(direcao.equals("<")) indexFita--;

                //  -----------------  SOMENTE PARA O CASO SENDO DETERMINISTIICO
                //Procura estado pra onde vai
                String nomeEstado= transicao.getNomeEstado();
                for (Estado estado : Estados) {
                    if(estado.getNome().equals(nomeEstado)){
                        return estado;
                    }
                }
                
            }
        }
        return estadoAtual;
    }

    public void processa(){
        preencheFita();
        printaFita();
        //Começamos pelo estado inicial
        Estado estadoAtual = achaEstadoInicial();

        //Roda até estar no estado final
        do{ //Se for o estado final e a maquina parar, para a execução
            System.out.println("Entrou");   

            System.out.println("ESTAMOS NO ESTADO: "+estadoAtual.getNome());
            estadoAtual= transiciona(estadoAtual);

            printaFita();
        }while(houveTransicao); //Vai parar somente se não houver mais transição pra onde ir

        if(estadoAtual.getIsFinal()) System.out.println("Sim");
        else System.out.println("Não");

    }

    public void printaFita(){
        for(int i=0; i<10; i++){
            System.out.println(fita.get(i));
        }
    }

}

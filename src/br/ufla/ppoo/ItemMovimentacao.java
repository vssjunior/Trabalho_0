/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.ppoo;

//Importação utilizada para capturar a data
import java.util.Date;

/**
 * Classe responsavel pela criação dos extratos nas contas
 * @author junior
 */
public class ItemMovimentacao {
    private Date data;
    private long hora;
    private String movimentacao;
    private char tipoMovimentacao;
    private double valor;
    private double saldoAtualizado; 

    /**
     * Construtor da classe 
     * @param movimentacao
     * @param tipoMovimentacao
     * @param saldoAtualizado 
     */
    public ItemMovimentacao(String movimentacao, char tipoMovimentacao, double valor, double saldoAtualizado) {
        data = new Date(System.currentTimeMillis());
        hora = System.currentTimeMillis();
        this.movimentacao = movimentacao;
        this.tipoMovimentacao = tipoMovimentacao;
        this.valor = valor;
        this.saldoAtualizado = saldoAtualizado;
    }
    
    /**
     * Metodo de impressão dos atributos
     */
    public void imprimir(){
        //                  22/05/2016 11:20                Saque                 d                   R$ 50,00		R$ 150,00
        System.out.println( data + "\t" + hora + "\t" + movimentacao + "\t" + tipoMovimentacao + " \t" + valor + "\t" + saldoAtualizado );
    }
}

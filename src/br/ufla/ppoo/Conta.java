/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.ppoo;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsavel pelas funcionalidades de uma conta bancaria
 * @author junior
 */
public class Conta {
    //Atributos da conta
    private static int ultimaConta = 0;
    private static double rendimento;
    private List <ItemMovimentacao> movimentacoes = new ArrayList<>();
    private double saldo;
    private int numeroConta;
    private String titular;
    private String cpf;
    
    /**
     * Construtor de uma conta sem saldo
     * @param titular
     * @param cpf 
     */
    public Conta(String titular, String cpf ) {
        saldo = 0;
        numeroConta = (ultimaConta + 1);
        this.titular = titular;
        this.cpf = cpf;
        rendimento = 2;
        String movimentacao = " Criacao da Conta";
        char tipoMovimentacao = 'N';
        double saldoDeposito = 0;
        ItemMovimentacao movimento = new ItemMovimentacao(movimentacao, tipoMovimentacao, saldoDeposito, saldo);
        movimentacoes.add(movimento);
        ultimaConta++;
    }
    
    /**
     * Construtor de uma conta com saldo
     * @param titular
     * @param saldo
     * @param cpf 
     */
    public Conta(String titular, double saldo, String cpf ) {
        this.saldo = saldo;
        numeroConta = (ultimaConta + 1);
        this.titular = titular;
        this.cpf = cpf;
        rendimento = 2;
        String movimentacao = " Criacao da Conta";
        char tipoMovimentacao = 'N';
        ItemMovimentacao movimento = new ItemMovimentacao(movimentacao, tipoMovimentacao, saldo, saldo);
        movimentacoes.add(movimento);
        ultimaConta++;
    }
    
    /**
     * Metodo para depositar o valor na conta
     * @param valor
     * @return 
     */
    public double depositar (double valor){
        double saldoAnterior = saldo;
        saldo = (saldo + valor);
        //Verifica se a operacao foi realizada
        if (saldo == (saldoAnterior + valor)) {
            System.out.println("Deposito realizado com sucesso"
                    + "\n Seu novo saldo é: " + saldo + "\n");
        } else {
            System.out.println(" Deposito nao realizado ");
        }
        String movimentacao = " deposito";
        char tipoMovimentacao = 'C';
        ItemMovimentacao movimento = new ItemMovimentacao(movimentacao, tipoMovimentacao, valor, saldo);
        movimentacoes.add(movimento);
        return saldo;
    }
    
    /**
     * Metodo responsavel por sacar o valor na conta
     * @param valor
     * @return 
     */
    public double sacar (double valor){
        if (valor < saldo){
            if(valor >= 0){
                double saldoAnterior = saldo;
                saldo = (saldo - valor);
                //Verifica se a operacao foi realizada com sucesso
                if (valor == (saldoAnterior - saldo)) {
                    System.out.println("\n Saque realizado com sucesso"
                            + "\n Seu novo saldo e: " + saldo + "\n");
                } else {
                    System.out.println(" Saque nao realizado \n");
                }
            }else{
                System.out.println("\n Somente valores maiores que 0");
            }
            //Gravação do Extrato
            String movimentacao = " Saque";
            char tipoMovimentacao = 'D';
            ItemMovimentacao movimento = new ItemMovimentacao(movimentacao, tipoMovimentacao, valor, saldo);
            movimentacoes.add(movimento);
        }
        else{
            System.out.println("\n Saldo insulficiente ");
        }
        return saldo;
    }
    
    /**
     * Metodo que imprime o extrato de uma conta
     */
    public void gerarExtrato(){
        for (ItemMovimentacao movimentacao : movimentacoes ){
            movimentacao.imprimir();
        }
    }
    
    /**
     * Metodo para fazer render o dinheiro na conta
     */
    public void render (){
        saldo = (saldo * rendimento);
    }
    
    /**
     * Metodo que altera o valor do rendimento da conta
     * @param rendimento 
     */
    public void alterarRendimento (double rendimento){
        this.rendimento = rendimento;
    }
    
    /**
     * Metodo que debita o valor da conta oriunda e credita na conta
     * passada por referencia
     * @param valor
     * @param contaDestino 
     */
    public void transfere(double valor, Conta contaDestino){
        sacar(valor);
        contaDestino.depositar(valor);
        //Gravação do Extrato
        String movimentacao = " Transferencia ";
        char tipoMovimentacao = 'D';
        ItemMovimentacao movimento = new ItemMovimentacao(movimentacao, tipoMovimentacao, valor, saldo);
        movimentacoes.add(movimento);
    }
    
    /**
     * Retorna o saldo da conta
     * @return 
     */
    public double getSaldo(){
        return saldo;
    }
    
    /**
     * Retorna o nome do titular da conta
     * @return 
     */
    public String getNome(){
        return titular;
    }
    
    /**
     * Retorna o número da conta
     * @return 
     */
    public int getNumeroConta(){
        return numeroConta;
    }
    
    /**
     * Retorna o número do cpf do titular da conta
     * @return 
     */
    public String getCpf(){
        return cpf;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufla.ppoo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.NullPointerException;
import java.util.List;
import java.util.Scanner;

/**
 * Classe destinada a fazer a interação com usuario do sistema de contas bancarias
 * @author junior
 */
public class Menu {
    //Atributos da Classe
    private static Scanner entrada;
    private static List<Conta> contas;
    private Conta contaRecuperada;
    
    /**
     * Metodo responsavel por organizar a tela
     */
    public void esperarTecla() {
        try {
            //Aguarda a entrada do usuario para continuar
            System.out.print("\n\nENTER para continuar...");
            Scanner entrada = new Scanner(System.in);
            entrada.nextLine();
            
            //Limpador de tela
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");

            }else{
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {}
    }
    
    /**
    * Metodo responsavel pela busca de contas
    */
    public Conta buscarConta() throws IndexOutOfBoundsException{
        //Variavel para retorno do metodo
        Conta contaRetorno = null;
        System.out.println(" Informe o numero da conta: ");
        int consulta = entrada.nextInt();
        if (consulta <= 0) {
            System.err.println("\n O numero da conta tem que ser maior que 0 \n");
        }else{
            for(Conta conta : contas){
                if(consulta == conta.getNumeroConta()){
                    contaRetorno = conta;
                }else{
                    System.out.println("\n O metodo buscar conta não encontrou a conta");
                }
            }
        }        
        return contaRetorno;
    }
    
    /**
     * Metodo responsavel pelos saques
     */
    public void sacar(){
        //Metodo que retorna a conta desejada
        Conta contaRecuperada = buscarConta();
        if(contaRecuperada == null){
            System.err.println("\n Conta não encontrada \n");
        }else{
            System.out.println("\n Informe o valor para saque: ");
            double valorSaque = entrada.nextDouble();
            contaRecuperada.sacar(valorSaque);
        }
        esperarTecla();
    }
    
    /*
    *Metodo responsavel pelos depositos
    */
    public void depositar(){
        //Metodo que retorna a conta desejada
        Conta contaRecuperada = buscarConta();
        if(contaRecuperada == null){
            System.err.println("\n Conta não encontrada \n");
        }else{
            System.out.println("\n Informe o valor para deposito: ");
            double deposito = entrada.nextDouble();
            contaRecuperada.depositar(deposito);
        }
        esperarTecla();
    }
    
    /**
     * Metodo verificador de cpf
     */
    public String cpf(){
        String cpf = null;
        boolean verificaCpf = false;
        do {
            System.out.println("\n Insira o cpf do titular: " + 
                               "\n Formato: XXX.XXX.XXX-XX" +
                               "\n CPF: ");
            cpf = entrada.next();
            //Formato de aceitação
            String cpfPadrao = "\\d\\d\\d.\\d\\d\\d.\\d\\d\\d-\\d\\d";
            verificaCpf = cpf.matches(cpfPadrao);
            
        }while(verificaCpf != true);
        return cpf;
    }
    
    /**
     * Metodo para transferencia de conta
     */
    public void transfere(){
        //Recebe valor para passar por referencia no metodo transfere
        System.out.println("\n Informe o valor do saque");
        double valorSacado = entrada.nextDouble();
        System.out.println("\n Conta para saque:");
        Conta contaSaque = buscarConta();
        if(contaSaque != null){
            System.out.println("\n Conta para deposito:");
            Conta contaDeposito = buscarConta();
            if(contaDeposito != null){
                contaSaque.transfere(valorSacado, contaDeposito);
            }else{
                System.err.println(" \n Conta para deposito não encontrada \n");
            }
        }else{
            System.err.println("\n Conta para Saque não encontrada \n");
        }
        esperarTecla();
    }
    
    /**
     * Metodo responsavel por cadastrar os clientes
     */
    public void cadastro(){
        //Dados cadastrais
        System.out.println("\n Insira o nome do titular: ");
        String nome = entrada.next();

        //Cadastro do cpf
        String cpf = cpf();

        //Menu de interação com usuario
        System.out.println("\n Desejar inserir um saldo? \n" + 
                " 1 - Sim \n " + 
                " 2 - Nao \n " +
                " Opcão: ");

        //captura a entrada do usuario
        int opcaoSaldo = entrada.nextInt();

        //Criação de conta com saldo
        if (opcaoSaldo == 1){
            System.out.println("\n informe o valor do deposito: ");
            double valorDeposito = entrada.nextDouble();
            //Cria um novo objeto tipo conta com saldo
            Conta conta = new Conta(nome, valorDeposito, cpf);
            //Adiciona no vetor de contas
            contas.add(conta);
            //Informa a conta criada 
            System.out.println("\n Conta cadastrada com sucesso " +
                        "\n O numero da sua conta é: " + conta.getNumeroConta() +
                        "\n Seu saldo é: " + conta.getSaldo() + "\n");
            
        //Criação de conta sem saldo
        }else if(opcaoSaldo == 2){
            //Cria um novo objeto tipo conta sem saldo
            Conta conta = new Conta(nome, cpf);
            //Adiciona a conta no vetor
            contas.add(conta);
            //Verifica se a conta foi cadastrada 
            System.out.println(" \nConta cadastrada com sucesso " +
                        "\n O numero da sua conta é: " + conta.getNumeroConta() +
                        "\n Seu saldo é: " + conta.getSaldo() + "\n");
        //Entrada invalida para criação da conta    
        }else{
            System.out.println("\n Valor invalido \n Tente Novamente");
            entrada.nextLine();
        }
        esperarTecla();
    }
    
    /**
     * Metodo responsavel por alterar a taxa o rendimento
     */
    public void alterarRendimento(){
        contaRecuperada = buscarConta();
        if(contaRecuperada == null){
            System.err.println("\n Conta invalida! \n");
        }else{
            System.out.println("\n Informe o novo valor de rendimento: ");
            double rendimento = entrada.nextDouble();
            contaRecuperada.alterarRendimento(rendimento);    
        }
        esperarTecla();
    }
    
    /**
     * Metodo responsavel por aplicar o rendimento
     */
    public void aplicarRendimento(){
        Conta contaRendimento = buscarConta();
        if(contaRendimento == null){
            System.err.println(" \n Conta para aplicar rendimento não encontrada! \n");
        }else{
            double saldoAnteriorRendimento = contaRendimento.getSaldo();
            contaRendimento.render();
            double saldoPosRendimento = contaRendimento.getSaldo();
            System.out.println("Valor antes do rendimento: " + saldoAnteriorRendimento);
            System.out.println("Valor depois do rendimento: " + saldoPosRendimento);
        }
        esperarTecla();
    }
    
    /**
     * Imprime o total de contas cadastradas mais o número de cada uma
     */
    public void totalContas(){
        System.out.println("\n");
        for(Conta conta : contas){
            System.out.println("Conta: " + conta.getNumeroConta());
        }
        System.out.println("Total de contas cadastrados: " + contas.size());
        esperarTecla();
    }
    
    /**
     * Imprime uma conta com seus atributos
     */
    public void consultarConta(){
        //Metodo que retorna a conta desejada
        contaRecuperada = buscarConta();
        if(contaRecuperada == null){
            System.err.println("\n Conta não encontrada");
        }else{
            System.out.println("\n Titular: " + contaRecuperada.getNome()
                    + "\n CPF: " + contaRecuperada.getCpf()
                    + "\n Número da conta: " + contaRecuperada.getNumeroConta()
                    + "\n Saldo: " + contaRecuperada.getSaldo() + "\n");
        }
        esperarTecla();
    }
    
    /**
     * Metodo que retorna o extrato da conta
     */
    public void extrato(){
        Conta contaExtrato = buscarConta();
        if(contaExtrato == null){
            System.err.println("\n Conta inexistente");
        }else{
            contaExtrato.gerarExtrato();
        }
        esperarTecla();
    }
    
    /**
     * Metodo responsavel por gerenciar o menu
     */
    public void menu(){
        //Variaveis locais do menu
        entrada = new Scanner(System.in);
        contas = new ArrayList<>();
        int menu = 0;
        contaRecuperada = null;
        
        /**
         * Loop que controla o menu
         */
        do{
            //Opcoes do Menu
            System.out.print("\n 1 Criar conta \n"
                    + " 2 Consultar total de contas cadastradas \n"
                    + " 3 Consultar conta \n"
                    + " 4 Depositar \n"
                    + " 5 Sacar \n"
                    + " 6 Transferir \n"
                    + " 7 Rendimento \n"
                    + " 8 Alterar Rendimento \n"
                    + " 9 Extrato \n"
                    + " 10 Sair \n"
                    + " \nOpcao: "
            );
            
            //Tratamento de entrada invalida do usuario
            try{
                /**
                * Variavel responsavel por controlar o loop do menu
                */
                menu = entrada.nextInt();

                /**
                 * Loop de execucao do menu
                 */
                switch (menu) {

                    //Criar conta
                    case 1:
                        cadastro();
                    break;

                    //Consulta contas
                    case 2:
                        totalContas();
                    break;

                    //Consulta conta
                    case 3:
                        consultarConta();
                    break;
                    
                    //Deposito    
                    case 4:
                        depositar();
                    break;

                    //Saque
                    case 5:
                        sacar();
                    break;
                    
                    //Transferencia
                    case 6:
                        transfere();
                    break;
                    
                    //Rendimento
                    case 7:
                        aplicarRendimento();
                    break;
                            
                    //Opções de rendimento
                    case 8:
                        alterarRendimento();
                    break;
                    
                    case 9:
                        extrato();
                    break;
                }
            //Trata entrada invalida no menu
            }catch (InputMismatchException e1) {
                System.err.println("\n Numeros devem ser inteiros \n Tente Novamente \n ");
                entrada.nextLine();
                esperarTecla();
                
            //Trata busca vazia no arraylist de contas
            }catch (IndexOutOfBoundsException e2){//| NullPointerException
                System.err.println(" Conta Inexistente. Tente Novamente! ");
                entrada.nextLine();
                esperarTecla();
            }
        }while(menu != 10);//Termino do while
    }
}//Termino da classe
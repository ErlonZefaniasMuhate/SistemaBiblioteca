/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbpackage erlon.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Erlon Z. Muhate
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));

        // Instanciando as classes
        Biblioteca biblioteca = new Biblioteca();
        CadernoEmprestimos cadernoEmprestimos = new CadernoEmprestimos();

        //Sincronizando os ficheiros com os arrays correspondentes
        biblioteca.syncBooksFileToArrayList();

        // Menu de opções para interagir com o sistema
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("""
                               Bem vindo a biblioteca da FENG.
                               1 - Consultar livro
                               2 - Adicionar Livro a Biblioteca
                               3 - Realizar empr\u00e9stimo
                               4 - Realizar devolu\u00e7\u00e3o
                               5 - Consultar utentes com empr\u00e9stimos
                               6 - Consultar departamentos com mais empr\u00e9stimos
                               0 - Sair
                               
                               """);
            opcao = Integer.parseInt(x.readLine());

            switch (opcao) {
                case 1 -> {
                    String titulo;
                    // Consultar livro
                    System.out.print("Digite o título do livro: ");
                    System.out.flush();
                    titulo = x.readLine();
                    Livro livro = biblioteca.procurarLivroPorTitulo(titulo);
                    if (livro != null) {
                        System.out.println("Livro encontrado: " + livro.detalhes());
                    } else {
                        System.out.println("Livro não encontrado.");
                    }
                }
                case 2 -> {//Adicionar livro a biblioteca
                    String t, ed;
                    int aut;
                    System.out.println("Titulo do livro.");
                    System.out.flush();
                    t = x.readLine();
                    System.out.println("Quantos autores tem o livro?");
                    aut = Integer.parseInt(x.readLine());
                    String[] autores = new String[aut];
                    for (int i = 0; i < aut; i++) {
                        System.out.println("Digite o nome do " + (i + 1) + "o autor.");
                        System.out.flush();
                        autores[i] = x.readLine();
                    }
                    System.out.println("\nQual editora publicou o livro?");
                    System.out.flush();
                    ed = x.readLine();
                    System.out.println("\nQuantos exemplares vai adicionar a biblioteca?");
                    int ex = Integer.parseInt(x.readLine());
                    Livro novo = new Livro(t, autores, ed);
                    novo.setNumExemplares(ex);
                    biblioteca.adicionarLivro(novo, ex);
                    biblioteca.syncBooksFileToArrayList();
                }
                case 3 -> {
                    // Realizar empréstimo
                    System.out.print("Digite o código do utente: ");
                    int codigoUtente = Integer.parseInt(x.readLine());
                    Utente utente = biblioteca.procurarUtentePorCodigo(codigoUtente);
                    if (utente == null) {
                        System.out.println("Utente não encontrado.");
                        break;
                    }
                    if (cadernoEmprestimos.estaNaListaDeIncumpridores(utente.getCodigo())) {
                        System.out.println("Utente está na lista de incumpridores e não pode fazer empréstimos.");
                        break;
                    }
                    if (cadernoEmprestimos.temLivroEmprestado(utente.getCodigo())) {
                        System.out.println("Utente já tem 4 livros emprestados e não pode fazer outro empréstimo.");
                        break;
                    }
                    System.out.print("Digite o título do livro: ");
                    String titulo = x.readLine();

                    Livro livro = biblioteca.procurarLivroPorTitulo(titulo);
                    if (livro == null) {
                        System.out.println("Livro não encontrado.");
                        break;
                    }
                    cadernoEmprestimos.registarEmprestimo(livro, utente, codigoUtente);
                    System.out.println("Empréstimo registrado com sucesso.");
                }

                case 4 -> {
                    //realizar devolucao
                    System.out.println("Digite o seu código:");
                    int codigo = Integer.parseInt(x.readLine());
                    Utente utente = biblioteca.procurarUtentePorCodigo(codigo);
                    if (utente == null) {
                        System.out.println("Utente não encontrado.");
                        break;
                    }
                    System.out.println("Digite o título do livro:");
                    String titulo = x.readLine();
                    Livro livro = biblioteca.procurarLivroPorTitulo(titulo);
                    if (livro == null) {
                        System.out.println("Livro não encontrado.");
                        break;
                    }
                    biblioteca.registarDevolucao(codigo, livro);
                    System.out.println("Devolução registada com sucesso.");
                }
                case 5 -> {
                    //consultar utentes com emprestimos
                    biblioteca.visualizarUtentesComEmprestimo();
                }
                case 6 -> {
                    System.out.println("Caderno de incumpridores:");
                    ArrayList<Integer> incumpridores = cadernoEmprestimos.getIncumpridores();
                    for (int codigo : incumpridores) {
                        System.out.println(biblioteca.procurarUtentePorCodigo(codigo).toString());
                    }
                }
                case 7 -> {
                    System.out.println("Obrigado por usar o sistema da biblioteca!");
                    break;
                }
                default ->
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    public int geradorCodigo(int size) {
        String alphaNumericString = "0123456789";
        StringBuilder aleatoria = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int r = (int) (Math.random() * alphaNumericString.length());
            aleatoria.append(alphaNumericString.charAt(r));
        }
        int codigo = Integer.parseInt(aleatoria.toString());
        return codigo;
    }
}fs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */


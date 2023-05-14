/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erlon.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Erlon Z. Muhate
 */
public class Biblioteca {
//para representar a biblioteca como um todo, com atributos: lista de livros disponíveis e a lista de utentes registados, e métodos para visualizar os utentes que fizeram
//empréstimo de livros e os departamentos que mais emprestam.

    private ArrayList<Livro> livros;
    private ArrayList<Utente> utentes;
    private CadernoEmprestimos cadernoEmprestimos;

    // construtor
    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.utentes = new ArrayList<>();
        this.cadernoEmprestimos = new CadernoEmprestimos();
    }

    // método para adicionar um livro ao ficheiro livros disponíveis
    public void adicionarLivro(Livro livro, int numExemplares) {
        String superLine = null;
        try {
            boolean found;
            try ( BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Erlon Z. Muhate\\Documents\\NetBeansProjects\\Main\\files\\livrosDisponiveis.txt"))) {
                String line, newLine = null;
                found = false;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(livro.getTitulo())) {
                        newLine = line.replace(String.valueOf(livro.getNumExemplares()), String.valueOf(livro.getNumExemplares() + numExemplares)) + "\n";
                        found = true;
                        System.out.println("Foram adicionados " + numExemplares + " a bibilioteca");
                    }
                    if (found) {
                        superLine += newLine;
                    } else {
                        superLine += line;
                    }
                }
                reader.close();
            }
            if (!found) {
                try ( BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Erlon Z. Muhate\\Documents\\NetBeansProjects\\Main\\files\\livrosDisponiveis.txt", true))) {
                    writer.write(livro.getTitulo());
                    writer.write(":");
                    //o registro do livro, cada campo sera separado por ":"
                    for (String autor : livro.getAutores()) {
                        writer.write(autor);
                        writer.write(",");//autores sao separados por virgula
                    }
                    writer.write(":");
                    writer.write(livro.getEditora());
                    writer.write(":");
                    writer.write(String.valueOf(livro.getNumExemplares()));
                    writer.write("\n");
                    writer.close();
                }
            } else {
                try ( BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Erlon Z. Muhate\\Documents\\NetBeansProjects\\Main\\files\\livrosDisponiveis.txt"))) {
                    writer.write(superLine);
                    writer.close();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // método para adicionar um utente à lista de utentes registados
    public void adicionarUtente(Utente utente) {
        try {
            String line;
            try ( BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Erlon Z. Muhate\\Documents\\NetBeansProjects\\Main\\files\\utentes.txt"))) {
                while ((line = reader.readLine()) != null) {
                    if (line.contains(utente.getNome())) {
                        System.out.println("O utente ja se encontra registrado!");
                        System.out.println(utente.toString());
                    } else {
                        try ( BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Erlon Z. Muhate\\Documents\\NetBeansProjects\\Main\\files\\utentes.txt"))) {
                            writer.write(utente.getNome());
                            writer.write(":");
                            writer.write(utente.getDepartamento());
                            writer.write(":");
                            writer.write(":");
                            writer.write(utente.getTipo());
                            writer.write(":");
                            writer.write(String.valueOf(utente.getCodigo()));
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void syncBooksFileToArrayList() {
        try {
            String line, book[];
            try ( BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\Erlon Z. Muhate\\Documents\\NetBeansProjects\\Main\\files\\livrosDisponiveis.txt"))) {
                while ((line = reader.readLine()) != null) {
                    book = null;
                    int commas = 0;
                    book = new String[4];
                    book = line.split(":");//separar os dados da linha do ficheiro pelo ':' atributos do livro
                    for (int i = 0; i < book[1].length(); i++) {
                        if (book[1].charAt(i) == ',') {
                            commas++;
                        }
                    }
                    String[] aux1 = new String[commas];
                    aux1 = book[1].split(",");//separar os autores encontradados linha do ficheiro
                    String[] autores = new String[commas];
                    for (int i = 0; i < commas; i++) {
                        aux1[i] = aux1[i].replace(",", "");// remove the comma at the end
                    }
                    Livro novo = new Livro(book[0], autores, book[2]);
                    novo.setNumExemplares(Integer.parseInt(book[3]));
                    livros.add(novo);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Biblioteca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // método para visualizar os utentes que fizeram empréstimo de livros
    public void visualizarUtentesComEmprestimo() {
        for (Emprestimo e : cadernoEmprestimos.getEmprestimos().values()) {
            if (e.getDataDevolucao() == null) {
                Utente utente = e.getUtente();
                System.out.println("Nome: " + utente.getNome() + ", Departamento: " + utente.getDepartamento());
            }
        }
    }

    // método para visualizar os departamentos que mais emprestam
    public void visualizarDepartamentosMaisEmprestam() {
        HashMap<String, Integer> departamentos = new HashMap<>();
        for (Emprestimo e : cadernoEmprestimos.getEmprestimos().values()) {
            if (e.getDataDevolucao() == null) {
                Utente utente = e.getUtente();
                String departamento = utente.getDepartamento();
                if (departamentos.containsKey(departamento)) {
                    departamentos.put(departamento, departamentos.get(departamento) + 1);
                } else {
                    departamentos.put(departamento, 1);
                }
            }
        }
        // ordenar os departamentos pelo número de empréstimos
        List<Map.Entry<String, Integer>> listaDepartamentos = new LinkedList<>(departamentos.entrySet());
        Collections.sort(listaDepartamentos, (Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) -> (o2.getValue()).compareTo(o1.getValue()));
        // imprimir a lista dos departamentos ordenados
        for (Map.Entry<String, Integer> departamento : listaDepartamentos) {
            System.out.println("Departamento: " + departamento.getKey() + ", Número de empréstimos: " + departamento.getValue());
        }
    }

    // método para registar a devolução de um livro
    public void registarDevolucao(int codigoUtente, Livro livro) {
        Calendar d = Calendar.getInstance();
        for (Emprestimo e : cadernoEmprestimos.getEmprestimos().values()) {
            if (e.getUtente().getCodigo() == codigoUtente && e.getDataDevolucaoRealizada() == null) {
                e.setDataDevolucaoRealizada(d);
                // verificar se a devolução foi feita dentro do prazo
                if (d.after(e.getDataDevolucao())) {
                    // se sim, atualizar o número de exemplares disponíveis do livro
                    Livro livroPrentendido = livro;
                    livroPrentendido.setNumExemplares(livroPrentendido.getNumExemplares() + 1);
                } else {
                    // se não, receber o livro e adicionar o utente à lista de incumpridores
                    cadernoEmprestimos.getIncumpridores().add(codigoUtente);
                    Livro livroPrentendido = livro;
                    livroPrentendido.setNumExemplares(livroPrentendido.getNumExemplares() + 1);
                }
                break;
            }
        }
    }

    public Livro procurarLivroPorTitulo(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equals(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public Utente procurarUtentePorCodigo(int codigo) {
        return utentes.get(codigo);
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void setLivros(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    public ArrayList<Utente> getUtentes() {
        return utentes;
    }

    public void setUtentes(ArrayList<Utente> utentes) {
        this.utentes = utentes;
    }

    public CadernoEmprestimos getCadernoEmprestimos() {
        return cadernoEmprestimos;
    }

    public void setCadernoEmprestimos(CadernoEmprestimos cadernoEmprestimos) {
        this.cadernoEmprestimos = cadernoEmprestimos;
    }

}

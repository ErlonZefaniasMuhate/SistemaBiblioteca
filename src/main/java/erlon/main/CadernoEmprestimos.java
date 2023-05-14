/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erlon.main;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 *
 * @author Erlon Z. Muhate
 */

public class CadernoEmprestimos {
//para representar o caderno de empréstimos de livros, com métodos para verificar se um utente já tem um livro emprestado ou se está na lista de incumpridores, e para registar um novo empréstimo.
    
    private LinkedHashMap<Integer, Emprestimo> emprestimos;
    private ArrayList<Integer> incumpridores;

    // construtor
    public CadernoEmprestimos() {
        this.emprestimos = new LinkedHashMap<>();
        this.incumpridores = new ArrayList<>();
    }
    
    // método para verificar se um utente já tem um livro emprestado
    public boolean temLivroEmprestado(int codigoUtente) {
        int tot= 0;
        for (Emprestimo e : emprestimos.values()) {
            if (e.getUtente().getCodigo() == codigoUtente && e.getDataDevolucaoRealizada() == null) {
               tot +=1;//total de livros nao devolvidos
            }
            if (tot>4){
                return true;
            }
        }
        return false;
    }
    
    // método para verificar se um utente está na lista de incumpridores
    public boolean estaNaListaDeIncumpridores(int codigoUtente) {
        return incumpridores.contains(codigoUtente);
    }
    
    // método para registar um novo empréstimo
    public void registarEmprestimo(Livro livro, Utente utente, int codigo) {
        Emprestimo novo = new Emprestimo(utente, livro, codigo);
        emprestimos.put(novo.getCodigoEmprestimo(), novo);
    }
    
    //getters e setters
    public LinkedHashMap<Integer, Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(LinkedHashMap<Integer, Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public ArrayList<Integer> getIncumpridores() {
        return incumpridores;
    }

    public void setIncumpridores(ArrayList<Integer> incumpridores) {
        this.incumpridores = incumpridores;
    }


    
}


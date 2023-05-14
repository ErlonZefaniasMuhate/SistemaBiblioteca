/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erlon.main;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Erlon Z. Muhate
 */
public class Emprestimo {
//para representar um empréstimo de livro, com atributos: data de empréstimo, data de devolução e utente (que efectuou o emprestimo).
    
    private Calendar dataEmprestimo;
    private Calendar dataDevolucao;
    private Calendar dataDevolucaoRealizada;
    private Utente utente;
    private ArrayList<Livro> livrosPretendidos;
    private int codigoEmprestimo;

    // construtor
    public Emprestimo(Utente utente, Livro livrosPretendidos, int codigo) {
        this.dataEmprestimo = Calendar.getInstance();//
        this.dataDevolucao = this.dataEmprestimo;
        this.dataDevolucaoRealizada= null;
        this.dataDevolucao.add(Calendar.DAY_OF_MONTH, 30);//a data do devolucao prevista e de 30 dias apos ao dia do emprestimo
        this.utente = utente;
        this.codigoEmprestimo = codigo;
        this.livrosPretendidos= new ArrayList<>();
    }
    //metodos personalizados
    public void emprestarMaisLivros(Livro livro){//maximo de 4 livros
        this.livrosPretendidos.add(livro);
        if(livrosPretendidos.size()>4){
            System.out.println("Nao pode emprestar mais livros, devolva os que ja pediu e tente novamente.");
            livrosPretendidos.remove(livro);
        }
    }
    public void emprestarMenosLivros (Livro livro){
        if(this.livrosPretendidos.contains(livro)){
            this.livrosPretendidos.remove(livro);
        }
        else{
            System.out.println("Voce nao emprestou esse livro");
        }
    }

    // getters e setters
    public Calendar getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Calendar dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Calendar getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Calendar dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public ArrayList<Livro> getLivrosPretendido() {
        return livrosPretendidos;
    }

    public void setLivroPretendido(ArrayList<Livro> livrosPretendidos) {
        this.livrosPretendidos = livrosPretendidos;
    }

    public int getCodigoEmprestimo() {
        return codigoEmprestimo;
    }

    public void setCodigoEmprestimo(int codigoEmprestimo) {
        this.codigoEmprestimo = codigoEmprestimo;
    }

    public Calendar getDataDevolucaoRealizada() {
        return dataDevolucaoRealizada;
    }

    public void setDataDevolucaoRealizada(Calendar dataDevolucaoRealizada) {
        this.dataDevolucaoRealizada = dataDevolucaoRealizada;
    }
    
}

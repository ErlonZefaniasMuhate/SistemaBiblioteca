/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package erlon.main;

/**
 *
 * @author Erlon Z. Muhate
 */
public class Utente {
    //para representar um utente da biblioteca, com atributos: nome, departamento, código único e tipo de utente (Aluno, Docente ou Funcionário).
    
    private String nome;
    private String departamento;
    private String tipo;
    private int codigo;

    
    /**
     * @param nome
     * @param departamento
     * @param codigo
     * @param tipo
     */

    // construtor
    public Utente(String nome, String departamento, int codigo, String tipo) {
        this.nome = nome;
        this.departamento = departamento;
        this.codigo = codigo;
        this.tipo = tipo;
    }

    // getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Utente{");
        sb.append("nome=").append(nome);
        sb.append(", departamento=").append(departamento);
        sb.append(", tipo=").append(tipo);
        sb.append(", codigo=").append(codigo);
        sb.append('}');
        return sb.toString();
    }
    
}

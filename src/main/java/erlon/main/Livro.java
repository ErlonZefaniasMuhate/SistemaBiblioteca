package erlon.main;

/**
 *
 * @author Erlon Z. Muhate
 */
public class Livro {
//para representar um livro da biblioteca, com atributos:  título, autores, editora e número de exemplares.

    private String titulo;
    private String[] autores;
    private String editora;
    private int numExemplares;

    // construtor
    public Livro(String titulo, String[] autores, String editora) {
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.numExemplares = 1;
    }

    // getters e setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String[] getAutores() {
        return autores;
    }

    public void setAutores(String[] autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getNumExemplares() {
        return numExemplares;
    }

    public void setNumExemplares(int numExemplares) {
        this.numExemplares = numExemplares;
    }

    public String detalhes() {
        StringBuilder sb = new StringBuilder();
        sb.append("Livro{");
        sb.append("titulo:").append(titulo);
        sb.append(", autores: {");
        for (String each : autores) {
            System.out.println(each + ".");
        }
        sb.append("}, editora:").append(editora);
        sb.append('}');
        return sb.toString();
    }
}


import java.io.Serializable;

public class DiocesesModelo implements Serializable{
    private String idDiocese;
    private String nome;

    // Construtor
    public DiocesesModelo(String idDiocese, String nome) {
        this.idDiocese = idDiocese;
        this.nome = nome;
    }

    // Getters
    public String getIdDiocese() {
        return idDiocese;
    }

    public String getNome() {
        return nome;
    }

    // Setters
    public void setIdDiocese(String idDiocese) {
        this.idDiocese = idDiocese;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Sobrescrevendo o método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Diocese{" +
                "ID='" + idDiocese + '\'' +
                ", Nome='" + nome + '\'' +
                '}';
    }
}

import java.io.Serializable;

public class ComunaModelo implements Serializable {
    private String idComuna;
    private String descricao;

    public ComunaModelo(String idComuna, String descricao) {
        this.idComuna = idComuna;
        this.descricao = descricao;
    }

    public String getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(String idComuna) {
        this.idComuna = idComuna;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

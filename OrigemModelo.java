import java.io.Serializable;

public class OrigemModelo implements Serializable {
    private String idOrigem;
    private String descricao;

    public OrigemModelo(String idOrigem, String descricao) {
        this.idOrigem = idOrigem;
        this.descricao = descricao;
    }

    public String getIdOrigem() {
        return idOrigem;
    }

    public void setIdOrigem(String idOrigem) {
        this.idOrigem = idOrigem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

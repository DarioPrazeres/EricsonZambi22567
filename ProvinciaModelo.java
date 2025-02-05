import java.io.Serializable;

public class ProvinciaModelo implements Serializable {
    private String idProvincia;
    private String descricao;

    public ProvinciaModelo(String idProvincia, String descricao) {
        this.idProvincia = idProvincia;
        this.descricao = descricao;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

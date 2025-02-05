import java.io.Serializable;

public class FormaFarmaceuticaModelo implements Serializable {
    private String idFormaFarmaceutica;
    private String descricao;

    public FormaFarmaceuticaModelo(String idFormaFarmaceutica, String descricao) {
        this.idFormaFarmaceutica = idFormaFarmaceutica;
        this.descricao = descricao;
    }

    public String getIdFormaFarmaceutica() {
        return idFormaFarmaceutica;
    }

    public void setIdFormaFarmaceutica(String idFormaFarmaceutica) {
        this.idFormaFarmaceutica = idFormaFarmaceutica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

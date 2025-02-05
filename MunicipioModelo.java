import java.io.Serializable;

public class MunicipioModelo implements Serializable {
    private String idMunicipio;
    private String descricao;

    public MunicipioModelo(String idMunicipio, String descricao) {
        this.idMunicipio = idMunicipio;
        this.descricao = descricao;
    }

    public String getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

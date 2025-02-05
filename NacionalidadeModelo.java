import java.io.Serializable;

public class NacionalidadeModelo implements Serializable {
    private String idNacionalidade;
    private String descricao;

    public NacionalidadeModelo(String idNacionalidade, String descricao) {
        this.idNacionalidade = idNacionalidade;
        this.descricao = descricao;
    }

    public String getIdNacionalidade() {
        return idNacionalidade;
    }

    public void setIdNacionalidade(String idNacionalidade) {
        this.idNacionalidade = idNacionalidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

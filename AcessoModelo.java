import java.io.Serializable;

public class AcessoModelo implements Serializable {
    private String idAcesso;
    private String descricao;

    public AcessoModelo(String idAcesso, String descricao) {
        this.idAcesso = idAcesso;
        this.descricao = descricao;
    }

    public String getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(String idAcesso) {
        this.idAcesso = idAcesso;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

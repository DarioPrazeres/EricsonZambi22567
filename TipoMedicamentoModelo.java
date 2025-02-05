import java.io.Serializable;

public class TipoMedicamentoModelo implements Serializable {
    private String idTipoMedicamento;
    private String descricao;

    public TipoMedicamentoModelo(String idTipoMedicamento, String descricao) {
        this.idTipoMedicamento = idTipoMedicamento;
        this.descricao = descricao;
    }

    public String getIdTipoMedicamento() {
        return idTipoMedicamento;
    }

    public void setIdTipoMedicamento(String idTipoMedicamento) {
        this.idTipoMedicamento = idTipoMedicamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

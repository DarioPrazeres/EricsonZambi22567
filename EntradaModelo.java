import java.io.Serializable;
import java.util.Date;

public class EntradaModelo implements Serializable {

    private int id, idUsuario, quantidadeEntrada;
    private String nomeMedicamento, nomeFuncionario, nomeFornecedor;
    private Date dataEntrada, dataRegistro, dataAtualizacao;

    public EntradaModelo() {
    }

    public EntradaModelo(String nomeMedicamento, int idUsuario, int quantidadeEntrada, String nomeFuncionario, String nomeFornecedor, Date dataEntrada, Date dataRegistro, Date dataAtualizacao) {
        //this.id = id;
        this.nomeMedicamento = nomeMedicamento;
        this.idUsuario = idUsuario;
        this.quantidadeEntrada = quantidadeEntrada;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeFornecedor = nomeFornecedor;
        this.dataEntrada = dataEntrada;
        this.dataRegistro = dataRegistro;
        this.dataAtualizacao = dataAtualizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String Medicamento) {
        this.nomeMedicamento = Medicamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(int quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

}

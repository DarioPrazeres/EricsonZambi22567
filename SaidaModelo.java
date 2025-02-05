import java.io.Serializable;
import java.util.Date;

public class SaidaModelo implements Serializable {
    private static final long serialVersionUID = 1L; 
    private static int contador = 0;

    private int id, idUsuario, quantidadeSaida;
    private String Medicamento, nomeFuncionario, nomeCliente, nif;
    private Date dataSaida, dataRegistro, dataAtualizacao;
    public  SaidaModelo(){

    }
    public SaidaModelo(String Medicamento, int idUsuario, int quantidadeSaida, String nomeFuncionario, String nomeCliente, String nif, Date dataSaida, Date dataRegistro, Date dataAtualizacao) {
        
        this.id = ++contador;
        this.Medicamento = Medicamento;
        this.idUsuario = idUsuario;
        this.quantidadeSaida = quantidadeSaida;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeCliente = nomeCliente;
        this.nif = nif;
        this.dataSaida = dataSaida;
        this.dataRegistro = dataRegistro;
        this.dataAtualizacao = dataAtualizacao;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedicamento() {
        return Medicamento;
    }

    public void setMedicamento(String Medicamento) {
        this.Medicamento = Medicamento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(int quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataSaida;
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
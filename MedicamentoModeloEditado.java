import java.io.Serializable;
import java.util.*;

public class MedicamentoModeloEditado implements Serializable {
    private static final long serialVersionUID = 1L; 
    private static int contador = 0;

    private int id, quantidade;
    private String lote, nome, tipo, fabricante, condicoesDeArmazenamento,
                   descricao, formaFarmaceutica, dosagem, origem, Dioceses, Paroquia;
    private float precoCompra, precoVenda;
    private Date DateFundacao;

    public MedicamentoModeloEditado(
        String lote, 
        int quantidade, 
        String nome, 
        String tipo, 
        String fabricante, 
        String dioceses, 
        String paroquia, 
        Date dateFundacao, 
        String condicoesDeArmazenamento, 
        String descricao, 
        String formaFarmaceutica, 
        String origem,
        String dosagem, 
        float precoCompra, 
        float precoVenda) 
        {
        this.id = ++contador;
        this.lote = lote;
        this.quantidade = quantidade;
        this.nome = nome;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.condicoesDeArmazenamento = condicoesDeArmazenamento;
        this.descricao = descricao;
        this.formaFarmaceutica = formaFarmaceutica;
        this.dosagem = dosagem;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.DateFundacao = dateFundacao;
        this.Dioceses = dioceses;
        this.Paroquia = paroquia;
    }

    // Métodos getters e setters
    public int getId() {
        return id;
    }
    
    public String getLote() {
        return lote;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getCondicoesDeArmazenamento() {
        return condicoesDeArmazenamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public String getDosagem() {
        return dosagem;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }
    public String getCondicoesArmazenamento(){
        return condicoesDeArmazenamento;
    }
    public String getOrigem(){
        return origem;
    }
    public Date getDateFundacao(){
        return DateFundacao;
    }    
    public String getParoquia(){
        return Paroquia;
    }    
    public String getDioceses(){
        return Dioceses;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setLote(String lote){
        this.lote = lote;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }
    public void setOrigem(String origem){
        this.origem = origem;
    }
    public void setDosagem(String dosagem){
        this.dosagem = dosagem;
    }
    public void setCondicoesArmazenamento(String condicoes){
        this.condicoesDeArmazenamento = condicoes;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    public void setFormaFarmaceutica(String formaFarmaceutica){
        this.formaFarmaceutica = formaFarmaceutica;
    }
    public void setFabricante(String fabricante){
        this.fabricante = fabricante;
    }
    public void setPrecoCompra(float precoCompra){
        this.precoCompra = precoCompra;
    } 
    public void setPrecoVenda(float precoVenda){
        this.precoVenda = precoVenda;
    }
    public void setDateFundacao(Date value){
        this.DateFundacao = value;
    }
    public void setParoquia(String value){
        this.Paroquia = value;
    }
    public void setDiocese(String value){
        this.Dioceses = value;
    }
}


import java.io.Serializable;

public class  FornecedorModelo implements Serializable{
    private static final long serialVersionUID = 1L; 
    private static int contador = 0;
    
    private int id;
    private  String NomeFornecedor, contacto, Nif, Endereco, Origem;

    public FornecedorModelo(String nomeFornecedor, String contacto, String Nif, String Endereco, String Origem){
        
        this.id = ++contador;
        this.NomeFornecedor = nomeFornecedor;
        this.contacto = contacto;
        this.Nif = Nif;
        this.Endereco =  Endereco;
        this.Origem = Origem;
    }

    public int getId() {
        return id;
    }
    public void setId(int  value){
        this.id = value;
    }
    public void setNomeFornecedor(String value){
        this.NomeFornecedor = value;
    }

    public String getNomeFornecedor(){
        return this.NomeFornecedor;
    }
    public void setContacto(String value){
        this.contacto = value;
    }
    
    public String getContacto(){
        return this.contacto;
    }
    public void setNif(String value){
        this.Nif = value;
    }
    
    public String getNif(){
        return this.Nif;
    }
    public void setEndereco(String value){
        this.Endereco = value;
    }
    
    public String getEndereco(){
        return this.Endereco;
    }
    public void setOrigem(String value){
        this.Origem = value;
    }
    
    public String getOrigem(){
        return this.Origem;
    }
    
}
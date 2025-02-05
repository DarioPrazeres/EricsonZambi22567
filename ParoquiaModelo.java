public class ParoquiaModelo {
    private String idParoquia;
    private String nome;

    // Construtor
    public ParoquiaModelo(String idParoquia, String nome) {
        this.idParoquia = idParoquia;
        this.nome = nome;
    }

    // Getters
    public String getIdParoquia() {
        return idParoquia;
    }

    public String getNome() {
        return nome;
    }

    // Setters
    public void setIdParoquia(String idParoquia) {
        this.idParoquia = idParoquia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Sobrescrevendo o método toString para facilitar a exibição dos dados
    @Override
    public String toString() {
        return "Paróquia{" +
                "ID='" + idParoquia + '\'' +
                ", Nome='" + nome + '\'' +
                '}';
    }
}

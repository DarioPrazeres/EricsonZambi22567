
import java.io.Serializable;

public class UsuarioModelo implements Serializable {
    private static final long serialVersionUID = 1L; 
    private static int contador = 0;

    private String email;
    private String password;
    private String username;
    private int idAcesso, id;

    public UsuarioModelo(String email, String password, String username, int idAcesso) {
        this.id = ++contador;
        this.email = email;
        this.password = password;
        this.username = username;
        this.idAcesso = idAcesso;
    }

    public int  getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdAcesso() {
        return idAcesso;
    }

    public void setIdAcesso(int idAcesso) {
        this.idAcesso = idAcesso;
    }

}

package grupo6.proyectogrupo6.Entities;

public class Usuario {

    private int idUser;
    private String email;
    private String contra;

    public Usuario(int idUser, String email, String contra) {
        this.idUser = idUser;
        this.email = email;
        this.contra = contra;
    }

    public Usuario(String email, String contra) {
        this.email = email;
        this.contra = contra;
    }

    public Usuario(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}

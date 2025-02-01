package DataAcces.DTO;

public class Administrador2DTO {

    private Integer idAdministrador2;
    private String Codigo;
    private String Usuario;
    private String Contrasenia;
    private Integer Tipo;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;

    // Constructor sin parámetros
    public Administrador2DTO() {
    }

    // Constructor con código
    public Administrador2DTO(String Codigo) {
        this.Codigo = Codigo;
    }

    // Constructor con Tipo
    public Administrador2DTO(Integer Tipo) {
        this.Tipo = Tipo;
    }


    // Constructor con Código, Usuario y Tipo
    public Administrador2DTO(String Codigo, String Usuario, String contrasenia, Integer Tipo) {
        this.Codigo = Codigo;
        this.Usuario = Usuario;
        this.Contrasenia=contrasenia;
        this.Tipo = Tipo;
    }

    // Constructor completo con todos los campos
    public Administrador2DTO(Integer idAdministrador2, String Codigo, String Usuario, String Contrasenia, Integer Tipo, 
                              String Estado, String FechaCrea, String FechaModifica) {
        this.idAdministrador2 = idAdministrador2;
        this.Codigo = Codigo;
        this.Usuario = Usuario;
        this.Contrasenia = Contrasenia;
        this.Tipo = Tipo;
        this.Estado = Estado;
        this.FechaCrea = FechaCrea;
        this.FechaModifica = FechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idAdministrador2         " + getIdAdministrador2()
                + "\n Codigo                  " + getCodigo()
                + "\n Usuario                 " + getUsuario()
                + "\n Contrasenia             " + getContrasenia()
                + "\n Tipo                    " + getTipo()
                + "\n Estado                  " + getEstado()
                + "\n FechaCrea               " + getFechaCrea()
                + "\n FechaModifica           " + getFechaModifica();
    }

    // Getters y Setters
    public Integer getIdAdministrador2() {
        return idAdministrador2;
    }

    public void setIdAdministrador2(Integer idAdministrador2) {
        this.idAdministrador2 = idAdministrador2;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContrasenia() {
        return Contrasenia;
    }

    public void setContrasenia(String Contrasenia) {
        this.Contrasenia = Contrasenia;
    }

    public Integer getTipo() {
        return Tipo;
    }

    public void setTipo(Integer Tipo) {
        this.Tipo = Tipo;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String FechaCrea) {
        this.FechaCrea = FechaCrea;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String FechaModifica) {
        this.FechaModifica = FechaModifica;
    }
}

package DataAcces.DTO;

public class AdministradorDTO {

//     CREATE TABLE Administrador(
//     idAdministrador     INTEGER PRIMARY KEY AUTOINCREMENT
//     --Check sirve para validar que el campo cumpla con una condicion en este caso que sea de 13 caracteres.
//     ,Codigo             TEXT CHECK(length(Codigo) = 13) NOT NULL UNIQUE
//     ,Tipo               INTEGER NOT NULL
//     ,Estado             VARCHAR(1) NOT NULL DEFAULT('A')
//     ,FechaCrea          DATETIME DEFAULT(datetime('now','localtime'))
//     ,FechaModifica      DATETIME
// );

    private Integer idAdministrador;
    private String Codigo;
    private Integer Tipo;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;

    public AdministradorDTO() {
    }

    public AdministradorDTO(String codigo) {
        this.Codigo=codigo;
    }

    public AdministradorDTO(Integer Tipo) {
        this.Tipo = Tipo;
    }

    public AdministradorDTO(String Codigo, Integer Tipo) {
        this.Codigo = Codigo;
        this.Tipo = Tipo;
    }

    public AdministradorDTO(Integer idAdministrador, String Codigo, Integer Tipo, String Estado, String FechaCrea
                            , String FechaModifica) {
        this.idAdministrador = idAdministrador;
        this.Codigo = Codigo;
        this.Tipo = Tipo;
        this.Estado = Estado;
        this.FechaCrea = FechaCrea;
        this.FechaModifica = FechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idAdministrador         " + getIdAdministrador()
                + "\n Codigo                  " + getCodigo()
                + "\n Tipo                    " + getTipo()
                + "\n Estado                  " + getEstado()
                + "\n FechaCrea               " + getFechaCrea()
                + "\n FechaModifica           " + getFechaModifica();
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }
    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }
    public String getCodigo() {
        return Codigo;
    }
    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
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

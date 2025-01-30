package DataAcces.DTO;

public class AdministradorTipoDTO {

    // idAdministradorTipo  INTEGER PRIMARY KEY AUTOINCREMENT
    // ,Nombre              TEXT NOT NULL
    // ,Estado              VARCHAR(1) NOT NULL DEFAULT('A')
    // ,FechaCrea           DATETIME DEFAULT(datetime('now','localtime'))
    // ,FechaModifica       DATETIME

    private int idAdministradorTipo;
    private String Nombre;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;

    public AdministradorTipoDTO() {
    }

    public AdministradorTipoDTO(String Nombre){
        this.Nombre = Nombre;
    }

    public AdministradorTipoDTO(int idAdministradorTipo, String Nombre, String Estado
                                , String FechaCrea, String FechaModifica) {
        this.idAdministradorTipo = idAdministradorTipo;
        this.Nombre = Nombre;
        this.Estado = Estado;
        this.FechaCrea = FechaCrea;
        this.FechaModifica = FechaModifica;
    }

    @Override
    public String toString(){
        return  getClass().getName()
                + "\n idAdministradorTipo     "+ getIdAdministradorTipo()
                + "\n nombre                  "+ getNombre()
                + "\n estado                  "+ getEstado()
                + "\n fechaCrea               "+ getFechaCrea()
                + "\n fechaModifica           "+ getFechaModifica();
    }

    public int getIdAdministradorTipo() {
        return idAdministradorTipo;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCrea() {
        return FechaCrea;
    }
    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

}

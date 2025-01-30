package DataAcces.DTO;

public class SeccionDTO {

    private Integer idSeccion;
    private String Nombre;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;

    public SeccionDTO() {}

    public SeccionDTO(String Nombre) {
        this.Nombre = Nombre;
    }

    public SeccionDTO(Integer idSeccion, String Nombre, String Estado, String FechaCrea, String FechaModifica) {
        this.idSeccion = idSeccion;
        this.Nombre = Nombre;
        this.Estado = Estado;
        this.FechaCrea = FechaCrea;
        this.FechaModifica = FechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idSeccion               " + getIdSeccion()
                + "\n nombre                  " + getNombre()
                + "\n estado                  " + getEstado()
                + "\n fechaCrea               " + getFechaCrea()
                + "\n fechaModifica           " + getFechaModifica();
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }
    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
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

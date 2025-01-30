package DataAcces.DTO;

public class CategoriaDTO {


    private Integer idCategoria;
    private String Nombre;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;

    public CategoriaDTO() {
    }

    public CategoriaDTO(String Nombre) {
        this.Nombre = Nombre;
    }

    public CategoriaDTO(Integer idCategoria, String Nombre, String Estado, String FechaCrea
                        , String FechaModifica) {
        this.idCategoria = idCategoria;
        this.Nombre = Nombre;
        this.Estado = Estado;
        this.FechaCrea = FechaCrea;
        this.FechaModifica = FechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idCategoria             " + getIdCategoria()
                + "\n Nombre                  " + getNombre()
                + "\n Estado                  " + getEstado()
                + "\n FechaCrea               " + getFechaCrea()
                + "\n FechaModifica           " + getFechaModifica();
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
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

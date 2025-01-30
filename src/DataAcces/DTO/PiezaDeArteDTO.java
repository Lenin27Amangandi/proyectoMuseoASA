package DataAcces.DTO;

public class PiezaDeArteDTO {

    // idPieza INTEGER PRIMARY KEY AUTOINCREMENT
    // ,BarCode TEXT CHECK(length(BarCode) = 13) NOT NULL UNIQUE
    // ,Nombre TEXT NOT NULL
    // ,Autor TEXT
    // ,Descripcion TEXT NOT NULL
    // ,PrecioReplica REAL NOT NULL
    // ,idCategoria INTEGER NOT NULL
    // ,idSeccion INTEGER NOT NULL

    private Integer idPieza;
    private String BarCode;
    private String Nombre;
    private String Autor;
    private String Descripcion;
    private Double PrecioReplica;
    private Integer idCategoria;
    private Integer idSeccion;
    private String Estado;
    private String FechaCrea;
    private String FechaModifica;
    private int comodin;

    public PiezaDeArteDTO() {
    }

    public PiezaDeArteDTO(String nombre) {
        Nombre = nombre;
    }

    public PiezaDeArteDTO(Double precioReplica) {
        PrecioReplica = precioReplica;
    }

    public PiezaDeArteDTO(String autor, int comodin) {
        Autor = autor;
        comodin=comodin;
    }

    public PiezaDeArteDTO(String descripcion, int comodin, Boolean comodin2) {
        Descripcion = descripcion;
        comodin=comodin;
    }

    public PiezaDeArteDTO(String BarCode, String Nombre, String Autor, String Descripcion, Double PrecioReplica,
            Integer idSeccion, Integer idCategoria) {
        this.BarCode = BarCode;
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.Descripcion = Descripcion;
        this.PrecioReplica = PrecioReplica;
        this.idCategoria = idCategoria;
        this.idSeccion = idSeccion;
    }

    // public PiezaDeArteDTO(String BarCode, String Nombre, String Autor, String
    // Descripcion, Double PrecioReplica,
    // Integer idCategoria, Integer idSeccion) {
    // this.BarCode = BarCode;
    // this.Nombre = Nombre;
    // this.Autor = Autor;
    // this.Descripcion = Descripcion;
    // this.PrecioReplica = PrecioReplica;
    // this.idCategoria = idCategoria;
    // this.idSeccion = idSeccion;
    // }

    public PiezaDeArteDTO(Integer idPieza, String BarCode, String Nombre, String Autor, String Descripcion,
            Double PrecioReplica, Integer idCategoria, Integer idSeccion, String Estado, String FechaCrea,
            String FechaModifica) {
        this.idPieza = idPieza;
        this.BarCode = BarCode;
        this.Nombre = Nombre;
        this.Autor = Autor;
        this.Descripcion = Descripcion;
        this.PrecioReplica = PrecioReplica;
        this.idCategoria = idCategoria;
        this.idSeccion = idSeccion;
        this.Estado = Estado;
        this.FechaCrea = FechaCrea;
        this.FechaModifica = FechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
                + "\n idPieza                 " + getIdPieza()
                + "\n barCode                 " + getBarCode()
                + "\n nombre                  " + getNombre()
                + "\n autor                   " + getAutor()
                + "\n descripcion             " + getDescripcion()
                + "\n precioReplica           " + getPrecioReplica()
                + "\n idCategoria             " + getIdCategoria()
                + "\n idSeccion               " + getIdSeccion()
                + "\n estado                  " + getEstado()
                + "\n fechaCrea               " + getFechaCrea()
                + "\n fechaModifica           " + getFechaModifica();
    }

    public Integer getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public String getBarCode() {
        return BarCode;
    }

    public void setBarCode(String barCode) {
        BarCode = barCode;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Double getPrecioReplica() {
        return PrecioReplica;
    }

    public void setPrecioReplica(Double precioReplica) {
        PrecioReplica = precioReplica;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Integer getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(Integer idSeccion) {
        this.idSeccion = idSeccion;
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

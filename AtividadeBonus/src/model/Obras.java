public class Obras{
    private String titulo;
    private String artista;
    private int anoCriacao;
    private String tipoObra;
    private String localizacao;

    public Obras(String titulo, String artista, int anoCriacao, String tipoObra, String localizacao) {
        this.titulo = titulo;
        this.artista = artista;
        this.anoCriacao = anoCriacao;
        this.tipoObra = tipoObra;
        this.localizacao = localizacao;
    }

    // Getters e Setters
    public String getTitulo() {
        return titulo;
    }

    public String getArtista() {
        return artista;
    }

    public int getAnoCriacao() {
        return anoCriacao;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    @Override
    public String toString() {
        return titulo + ", " + artista + ", " + anoCriacao + ", " + tipoObra + ", " + localizacao;
    }

    public static Obras fromString(String linha) {
        String[] dadosObra = linha.split(", ");
        if (dadosObra.length == 5) {
            return new Obras(dadosObra[0], dadosObra[1], Integer.parseInt(dadosObra[2]), dadosObra[3], dadosObra[4]);
        } else {
            return null;
        }
    }
}

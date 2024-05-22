import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GerenciadorObras {

    private static final String ARQUIVO = "obras_de_arte.txt";

    public static void salvarObra(Obras obra) throws IOException {

        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(obra.getTitulo() + ";" +
                     obra.getArtista() + ";" +
                     obra.getAnoCriacao() + ";" +
                     obra.getTipoObra() + ";" +
                     obra.getLocalizacao() + "\n");

        }

    }

    public static ArrayList<Obras> listarObras() throws IOException, Exception {

        ArrayList<Obras> listaObras = new ArrayList<>();

        try (FileReader fr = new FileReader(ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {

                String linha;
                while ((linha = br.readLine()) != null) {
                    
                    String[] dados = linha.split(";");
                    if (dados.length == 5) {
                        Obras obra = new Obras(dados[0], dados[1], Integer.parseInt(dados[2]), dados[3], dados[4]);
                        listaObras.add(obra);
                    }
                }
        } 

        if (listaObras.isEmpty()) {
            throw new Exception("\nNão há obras de arte cadastradas");
        }

        return listaObras;

    }

    public static Obras buscarObra(String titulo) throws Exception {

        ArrayList<Obras> listaObras = listarObras();

        for (Obras obra : listaObras) {

            if(obra.getTitulo().equalsIgnoreCase(titulo)) {

                return obra;
            }
        }

        throw new Exception("\nObra com o título '" + titulo + "' não localizada!\n");

    }

    public static void excluirObra(String titulo) throws Exception{

        ArrayList<Obras> listaObras = listarObras();       
    
        boolean encontrou = false;
        for (Obras obra : listaObras) {

            if(obra.getTitulo().equalsIgnoreCase(titulo)) {
                listaObras.remove(obra);
                encontrou = true;
                break;
            }
        }

        if (!encontrou) {
            throw new Exception("\nObra com o título '" + titulo + "' não localizada!\n");
        }

        try (FileWriter fw = new FileWriter(ARQUIVO);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (Obras obra : listaObras) {

                bw.write(obra.getTitulo() + ";" +
                         obra.getArtista() + ";" +
                         obra.getAnoCriacao() + ";" +
                         obra.getTipoObra() + ";" +
                         obra.getLocalizacao() + "\n");
            }
        }
    }

     public static List<Obras> buscarPorArtista(String artista) throws IOException, Exception {
        return listarObras().stream()
                .filter(obra -> obra.getArtista().equalsIgnoreCase(artista))
                .collect(Collectors.toList());
    }

    public static List<Obras> buscarPorAnoCriacao(int anoCriacao) throws IOException, Exception {
        return listarObras().stream()
                .filter(obra -> obra.getAnoCriacao() == anoCriacao)
                .collect(Collectors.toList());
    }

    public static List<Obras> buscarPorTipo(String tipoObra) throws IOException, Exception {
        return listarObras().stream()
                .filter(obra -> obra.getTipoObra().equalsIgnoreCase(tipoObra))
                .collect(Collectors.toList());
    }
    
}
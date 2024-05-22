import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;

public class Sistema {

    private static void exibirMenu() {
        System.out.println("\nGERENCIADOR DE OBRAS DE ARTE");
        System.out.println("1) Cadastrar Obra de Arte");
        System.out.println("2) Buscar Obra de Arte");
        System.out.println("3) Listar Todas as Obras de Arte");
        System.out.println("4) Excluir Obra de Arte");
        System.out.println("5) Buscar Obras por Artista");
        System.out.println("6) Buscar Obras por Ano de Criação");
        System.out.println("7) Buscar Obras por Tipo");
        System.out.println("0) Sair\n");
        System.out.print("Sua opção: ");
    }

    private static void verificarOpcao(int op) {
        switch (op) {
            case 1:
                cadastrarObra();
                break;
            case 2:
                buscarObra();
                break;
            case 3:
                listarObras();
                break;
            case 4:
                excluirObra();
                break;
            case 5:
                buscarPorArtista();
                break;
            case 6:
                buscarPorAnoCriacao();
                break;
            case 7:
                buscarPorTipo();
                break;
            case 0:
                System.out.println("\nO Programa foi finalizado...");
                break;
            default:
                System.out.println("\nOpção inválida. Digite novamente:");
                break;
        }
    }

    private static void cadastrarObra() {
        System.out.println("\nCadastro de Nova Obra de Arte:");
        System.out.print("Título: ");
        String titulo = Console.lerString();
        System.out.print("Artista: ");
        String artista = Console.lerString();
        System.out.print("Ano de Criação: ");
        int anoCriacao = Console.lerInt();
        System.out.print("Tipo de Obra (pintura, escultura, fotografia): ");
        String tipoObra = Console.lerString();
        System.out.print("Localização: ");
        String localizacao = Console.lerString();

            Obras novaObra = new Obras(titulo, artista, anoCriacao, tipoObra, localizacao);
        
        try {
            
            GerenciadorObras.salvarObra(novaObra);

            System.out.println("\nObra de Arte cadastrada com sucesso.");
        } catch (InputMismatchException e) {
            System.out.println("\nValor inválido para o ano de criação.");
        } catch (IOException e) {
            System.out.println("\nErro ao salvar a obra de arte no arquivo.");
        }
    }

    private static void buscarObra() {

        System.out.println("\nBuscar Obra de Arte");
        System.out.print("Título da obra: ");
        String titulo = Console.lerString();

        try {

            Obras obra = GerenciadorObras.buscarObra(titulo);

            if (obra != null) {
               System.out.println("\nArte Encontrada: " + obra);
            } else {
                System.out.println("\nObra de Arte não encontrada.\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarObras() {
        System.out.println("\nLista de Obras de Arte:");
        try {

            for (Obras tempObra : GerenciadorObras.listarObras())
            {
                System.out.println(tempObra);
            }
        } catch (Exception e) {
            System.out.println("\nErro ao listar as obras de arte.");
        }
    }

    private static void excluirObra() {
        System.out.println("\nExcluir Obra de Arte");
        System.out.print("Título da obra a ser excluída: ");
        String titulo = Console.lerString();
        try {

            GerenciadorObras.excluirObra(titulo);
            System.out.println("\nObra de Arte excluída com sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarPorArtista() {
        System.out.println("\nBuscar Obras por Artista");
        System.out.print("Artista: ");
        String artista = Console.lerString();

        try {
            List<Obras> obras = GerenciadorObras.buscarPorArtista(artista);
            if (!obras.isEmpty()) {
                obras.forEach(System.out::println);
            } else {
                System.out.println("\nNenhuma obra encontrada para o artista " + artista + ".");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao buscar obras por artista.");
        }
    }

    private static void buscarPorAnoCriacao() {
        System.out.println("\nBuscar Obras por Ano de Criação");
        System.out.print("Ano de Criação: ");
        int anoCriacao = Console.lerInt();

        try {
            List<Obras> obras = GerenciadorObras.buscarPorAnoCriacao(anoCriacao);
            if (!obras.isEmpty()) {
                obras.forEach(System.out::println);
            } else {
                System.out.println("\nNenhuma obra encontrada no ano " + anoCriacao + ".");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao buscar obras por ano de criação.");
        }
    }

    private static void buscarPorTipo() {
        System.out.println("\nBuscar Obras por Tipo");
        System.out.print("Tipo de Obra: ");
        String tipo = Console.lerString();

        try {
            List<Obras> obras = GerenciadorObras.buscarPorTipo(tipo);
            if (!obras.isEmpty()) {
                obras.forEach(System.out::println);
            } else {
                System.out.println("\nNenhuma obra encontrada para o tipo " + tipo + ".");
            }
        } catch (Exception e) {
            System.out.println("\nErro ao buscar obras por tipo.");
        }
    }

    public static void executar() {
        int op;
        do {
            exibirMenu();
            op = Console.lerInt();
            verificarOpcao(op);
        } while(op != 0);
    }
}
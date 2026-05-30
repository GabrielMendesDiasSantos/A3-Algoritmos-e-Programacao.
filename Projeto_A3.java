import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Projeto_A3 {

    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();

    static ArrayList<String> itensPedido = new ArrayList<>();
    static ArrayList<Double> precosPedido = new ArrayList<>();
    static ArrayList<String> categoriasPedido = new ArrayList<>();

    public static void main(String[] args) {

        String[] pizzasalgada = {
            " ",
            "Margherita", "Calabresa", "Quatro Queijos", "Portuguesa", "Napolitana",
            "Frango c/ Catupiry", "Mucarela", "Atum", "Basca", "Pepperoni",
            "Baiana", "Caipira", "Palmito c/ Azei.", "Moda da Casa", "Calabresa c/ Cat.",
            "Brocolis c/ Bacon", "Milho c/ Bacon", "Carne Seca c/ Cat.", "Frango c/ Milho", "Alho e Oleo"
        };

        double[] precoSalgada = {
            0, 30, 35, 40, 45, 45, 35, 35, 40, 35, 35,
            50, 33, 42, 44, 40, 36, 33, 43, 37, 34
        };

        String[] pizzadoce = {
            " ",
            "Chocolate", "Banana com Canela", "Romeu e Julieta", "Morango c/ Choc."
        };

        double[] precoDoce = {0, 30, 32, 35, 38};

        String[] listaBebidas = {
            " ",
            "Coca-Cola 2L", "Coca-Cola Lata", "Guarana 2L", "Guarana Lata",
            "Suco de Laranja", "Suco de Uva", "Agua Mineral", "Cerveja Lata", "Cerveja Long Neck"
        };

        double[] precoBebida = {0, 12, 7, 10, 6, 8, 8, 4, 6, 9};

        int opcaoPrincipal;

        do {
            mostrarMenuPrincipal();
            opcaoPrincipal = lerInteiro("Escolha uma opcao: ");

            switch (opcaoPrincipal) {
                case 1:
                    menuCategoria("PIZZAS SALGADAS", "salgada", pizzasalgada, precoSalgada);
                    break;

                case 2:
                    menuCategoria("PIZZAS DOCES", "doce", pizzadoce, precoDoce);
                    break;

                case 3:
                    menuCategoria("BEBIDAS", "bebida", listaBebidas, precoBebida);
                    break;

                case 4:
                    if (!itensPedido.isEmpty()) {
                        removerItem();
                    } else {
                        System.out.println("Voce ainda nao escolheu nenhum item.");
                    }
                    break;

                case 5:
                    if (!itensPedido.isEmpty()) {
                        finalizarPedido();
                        System.out.println("Pedido finalizado. Encerrando sistema...");
                        System.exit(0);
                    } else {
                        System.out.println("Voce ainda nao escolheu nenhum item.");
                    }
                    break;

                case 0:
                    System.out.println("Codigo encerrado.");
                    break;

                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

        } while (opcaoPrincipal != 0);

        sc.close();
    }

    public static void mostrarMenuPrincipal() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        PIZZARIA DELIVERY             ║");
        System.out.println("║          -- MENU PRINCIPAL --        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1 - Pizza salgada                   ║");
        System.out.println("║  2 - Pizza doce                      ║");
        System.out.println("║  3 - Bebidas                         ║");

        if (!itensPedido.isEmpty()) {
            System.out.println("║  4 - Remover item                    ║");
            System.out.println("║  5 - Finalizar pedido                ║");
        }

        System.out.println("║  0 - Encerrar codigo                 ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    public static void menuCategoria(String titulo, String categoria, String[] itens, double[] precos) {
        int opcao;

        do {
            System.out.println();
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║        PIZZARIA DELIVERY             ║");
            System.out.printf("║          -- %-20s -- ║%n", titulo);
            System.out.println("╠══════════════════════════════════════╣");

            for (int i = 1; i < itens.length; i++) {
                System.out.printf("║ %2d - %-22s R$%6.2f ║%n", i, itens[i], precos[i]);
            }

            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║  0 - Voltar ao menu principal        ║");
            System.out.println("╚══════════════════════════════════════╝");

            opcao = lerInteiro("Escolha uma opcao: ");

            if (opcao == 0) {
                System.out.println("Voltando ao menu principal...");
            } else if (opcao >= 1 && opcao < itens.length) {
                itensPedido.add(itens[opcao]);
                precosPedido.add(precos[opcao]);
                categoriasPedido.add(categoria);

                mostrarSelecionadosDaCategoria(categoria);
            } else {
                System.out.println("Opcao invalida. Tente novamente.");
            }

        } while (opcao != 0);
    }

    public static void mostrarSelecionadosDaCategoria(String categoria) {
        char car = '-';

        for (int c = 0; c <= 39; c++) System.out.print(car);
        System.out.println();

        if (categoria.equals("salgada")) {
            System.out.println("Pizzas salgadas selecionadas:");
        } else if (categoria.equals("doce")) {
            System.out.println("Pizzas doces selecionadas:");
        } else if (categoria.equals("bebida")) {
            System.out.println("Bebidas selecionadas:");
        }

        for (int i = 0; i < itensPedido.size(); i++) {
            if (categoriasPedido.get(i).equals(categoria)) {
                System.out.printf("  %-32s R$%.2f%n", itensPedido.get(i), precosPedido.get(i));
            }
        }

        for (int c = 0; c <= 39; c++) System.out.print(car);
        System.out.println();
    }

    public static void removerItem() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        ITENS SELECIONADOS            ║");
        System.out.println("╠══════════════════════════════════════╣");

        for (int i = 0; i < itensPedido.size(); i++) {
            System.out.printf("║ %2d - %-22s R$%6.2f ║%n", i + 1, itensPedido.get(i), precosPedido.get(i));
        }

        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  0 - Voltar ao menu principal        ║");
        System.out.println("╚══════════════════════════════════════╝");

        int indice = lerInteiro("Digite o indice do item que deseja remover: ");

        if (indice == 0) {
            System.out.println("Voltando ao menu principal...");
        } else if (indice >= 1 && indice <= itensPedido.size()) {
            String removido = itensPedido.remove(indice - 1);
            precosPedido.remove(indice - 1);
            categoriasPedido.remove(indice - 1);
            System.out.println("Item removido: " + removido);
        } else {
            System.out.println("Indice invalido.");
        }
    }

    public static void finalizarPedido() {
        String[] setores = {
            "Setor A (0-3 km)",
            "Setor B (3-6 km)",
            "Setor C (6-9 km)",
            "Setor D (9-12 km)",
            "Setor E (12-15 km)"
        };

        double[] precoFrete = {5.00, 8.00, 11.00, 14.00, 17.00};

        sc.nextLine();

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        PIZZARIA DELIVERY             ║");
        System.out.println("║          -- ENDERECO --              ║");
        System.out.println("╚══════════════════════════════════════╝");

        System.out.print("Digite o nome da sua rua: ");
        String rua = sc.nextLine();

        int setorIdx = rand.nextInt(setores.length);
        double frete = precoFrete[setorIdx];

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║        PIZZARIA DELIVERY             ║");
        System.out.println("║       -- FORMA DE PAGAMENTO --       ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1 - Dinheiro       (5% desconto)    ║");
        System.out.println("║  2 - PIX            (7% desconto)    ║");
        System.out.println("║  3 - Credito        (sem desconto)   ║");
        System.out.println("║  4 - Debito         (3% desconto)    ║");
        System.out.println("╚══════════════════════════════════════╝");

        int pagamento = lerInteiro("Escolha o metodo de pagamento: ");

        while (pagamento < 1 || pagamento > 4) {
            System.out.println("Opcao invalida, tente novamente.");
            pagamento = lerInteiro("Escolha o metodo de pagamento: ");
        }

        String[] nomesPagamento = {"", "Dinheiro", "PIX", "Credito", "Debito"};
        double[] descontos = {0, 0.05, 0.07, 0.00, 0.03};

        String nomePag = nomesPagamento[pagamento];
        double desconto = descontos[pagamento];

        double subtotal = calcularSubtotal();
        double valorDesconto = subtotal * desconto;
        double totalComDesconto = subtotal - valorDesconto;
        double total = totalComDesconto + frete;

        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           RESUMO DO PEDIDO           ║");
        System.out.println("╠══════════════════════════════════════╣");

        mostrarNotaFiscal();

        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf("║ Rua: %-31s║%n", rua);
        System.out.printf("║ Setor: %-29s║%n", setores[setorIdx]);
        System.out.printf("║ Frete:                  R$%6.2f    ║%n", frete);
        System.out.printf("║ Subtotal:               R$%6.2f    ║%n", subtotal);
        System.out.printf("║ Pagamento: %-25s║%n", nomePag);

        if (desconto > 0) {
            System.out.printf("║ Desconto (%.0f%%):        -R$%6.2f    ║%n", desconto * 100, valorDesconto);
        } else {
            System.out.println("║ Sem desconto                        ║");
        }

        System.out.println("╠══════════════════════════════════════╣");
        System.out.printf("║ TOTAL COM FRETE:        R$%6.2f    ║%n", total);
        System.out.println("╚══════════════════════════════════════╝");

        itensPedido.clear();
        precosPedido.clear();
        categoriasPedido.clear();
    }

    public static void mostrarNotaFiscal() {
        boolean[] usado = new boolean[itensPedido.size()];

        for (int i = 0; i < itensPedido.size(); i++) {
            if (!usado[i]) {
                String nomeAtual = itensPedido.get(i);
                double precoAtual = precosPedido.get(i);
                int quantidade = 1;

                usado[i] = true;

                for (int j = i + 1; j < itensPedido.size(); j++) {
                    if (itensPedido.get(j).equals(nomeAtual)) {
                        quantidade++;
                        usado[j] = true;
                    }
                }

                double totalItem = precoAtual * quantidade;

                System.out.printf("║ %-18s %2dx R$%6.2f ║%n", nomeAtual, quantidade, totalItem);
            }
        }
    }

    public static double calcularSubtotal() {
        double subtotal = 0;

        for (double preco : precosPedido) {
            subtotal += preco;
        }

        return subtotal;
    }

    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);

        while (!sc.hasNextInt()) {
            System.out.println("Digite apenas numeros.");
            sc.next();
            System.out.print(mensagem);
        }

        return sc.nextInt();
    }
}

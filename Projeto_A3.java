import java.util.Random;
import java.util.Scanner;

public class Projeto_A3 {
    public static void main(String[] args) {

        // Scanner para ler os dados digitados pelo usuário.
        Scanner sc = new Scanner(System.in);

        // Random para sortear o setor de entrega.
        Random rand = new Random();

        // Lista de pizzas salgadas.
        String[] pizzasalgada = {
            " ",
            "Margherita", "Calabresa", "Quatro Queijos", "Portuguesa", "Napolitana",
            "Frango c/ Catupiry", "Mucarela", "Atum", "Basca", "Pepperoni",
            "Baiana", "Caipira", "Palmito c/ Azei.", "Moda da Casa", "Calabresa c/ Cat.",
            "Brocolis c/ Bacon", "Milho c/ Bacon", "Carne Seca c/ Cat.", "Frango c/ Milho", "Alho e Oleo"
        };

        // Preços das pizzas salgadas.
        double[] precoSalgada = {
            0, 30, 35, 40, 45, 45, 35, 35, 40, 35, 35,
            50, 33, 42, 44, 40, 36, 33, 43, 37, 34
        };

        // Lista de pizzas doces.
        String[] pizzadoce = {
            " ",
            "Chocolate", "Banana com Canela", "Romeu e Julieta", "Morango c/ Choc."
        };

        // Preços das pizzas doces.
        double[] precoDoce = {0, 30, 32, 35, 38};

        // Lista de bebidas.
        String[] listaBebidas = {
            " ",
            "Coca-Cola 2L", "Coca-Cola Lata", "Guarana 2L", "Guarana Lata",
            "Suco de Laranja", "Suco de Uva", "Agua Mineral", "Cerveja Lata", "Cerveja Long Neck"
        };

        // Preços das bebidas.
        double[] precoBebida = {0, 12, 7, 10, 6, 8, 8, 4, 6, 9};

        // Setores de entrega.
        String[] setores = {
            "Setor A (0-3 km)",
            "Setor B (3-6 km)",
            "Setor C (6-9 km)",
            "Setor D (9-12 km)",
            "Setor E (12-15 km)"
        };

        // Valor do frete de cada setor.
        double[] precoFrete = {5.00, 8.00, 11.00, 14.00, 17.00};

        // Formas de pagamento disponíveis.
        String[] nomesPagamento = {"", "Dinheiro", "PIX", "Credito", "Debito"};

        // Desconto de cada forma de pagamento.
        double[] descontos = {0, 0.05, 0.07, 0.00, 0.03};

        // Arrays do carrinho.
        String[] itensPedido = new String[200];
        double[] precoUnitarioPedido = new double[200];
        int[] quantidadePedido = new int[200];
        String[] categoriaPedido = new String[200];

        // Quantidade de tipos de itens diferentes no carrinho.
        int qtdTiposItens = 0;

        // Opção escolhida no menu principal.
        int opcaoPrincipal = -1;

        // Controla se o sistema deve encerrar depois de finalizar o pedido.
        boolean encerrarSistema = false;

        // Arrays usados para montar menu e carrinho lado a lado.
        String[] linhasMenu = new String[30];
        String[] linhasCarrinho = new String[400];

        // Laço principal do sistema.
        do {

            // Montagem do menu principal.
            int qtdLinhasMenu = 0;

            linhasMenu[qtdLinhasMenu++] = "╔══════════════════════════════════════╗";
            linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "        PIZZARIA DELIVERY");
            linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "        -- MENU PRINCIPAL --");
            linhasMenu[qtdLinhasMenu++] = "╠══════════════════════════════════════╣";
            linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "1 - Pizza salgada");
            linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "2 - Pizza doce");
            linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "3 - Bebidas");

            // As opções 4 e 5 só aparecem se houver item no carrinho.
            if (qtdTiposItens > 0) {
                linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "4 - Remover item");
                linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "5 - Finalizar pedido");
            }

            linhasMenu[qtdLinhasMenu++] = String.format("║ %-36.36s ║", "0 - Encerrar codigo");
            linhasMenu[qtdLinhasMenu++] = "╚══════════════════════════════════════╝";

            // Se houver item no carrinho, monta a caixa do carrinho.
            if (qtdTiposItens > 0) {
                int qtdLinhasCarrinho = 0;
                double subtotalCarrinho = 0;

                linhasCarrinho[qtdLinhasCarrinho++] = "╔════════════════════════════════════════════════════╗";
                linhasCarrinho[qtdLinhasCarrinho++] = String.format("║ %-50.50s ║", "              CARRINHO DO PEDIDO");
                linhasCarrinho[qtdLinhasCarrinho++] = "╠════════════════════════════════════════════════════╣";

                // Percorre todos os itens do carrinho.
                for (int i = 0; i < qtdTiposItens; i++) {
                    double totalItem = precoUnitarioPedido[i] * quantidadePedido[i];
                    subtotalCarrinho += totalItem;

                    String valorUnitario = String.format("R$%.2f", precoUnitarioPedido[i]).replace('.', ',');
                    String valorTotal = String.format("R$%.2f", totalItem).replace('.', ',');

                    String linhaItem = String.format("%02d %s", i + 1, itensPedido[i]);
                    String linhaValor = quantidadePedido[i] + " x " + valorUnitario + " = " + valorTotal;

                    linhasCarrinho[qtdLinhasCarrinho++] = String.format("║ %-50.50s ║", linhaItem);
                    linhasCarrinho[qtdLinhasCarrinho++] = String.format("║ %-50.50s ║", "   " + linhaValor);
                }

                String subtotalTexto = String.format("Subtotal: R$%.2f", subtotalCarrinho).replace('.', ',');

                linhasCarrinho[qtdLinhasCarrinho++] = "╠════════════════════════════════════════════════════╣";
                linhasCarrinho[qtdLinhasCarrinho++] = String.format("║ %-50.50s ║", subtotalTexto);
                linhasCarrinho[qtdLinhasCarrinho++] = "╚════════════════════════════════════════════════════╝";

                // Descobre qual caixa tem mais linhas.
                int maior = qtdLinhasMenu;

                if (qtdLinhasCarrinho > maior) {
                    maior = qtdLinhasCarrinho;
                }

                System.out.println();

                // Imprime menu e carrinho lado a lado.
                for (int i = 0; i < maior; i++) {
                    String esquerda = "";
                    String direita = "";

                    if (i < qtdLinhasMenu) {
                        esquerda = linhasMenu[i];
                    }

                    if (i < qtdLinhasCarrinho) {
                        direita = linhasCarrinho[i];
                    }

                    System.out.printf("%-43s %s%n", esquerda, direita);
                }

            } else {

                // Se o carrinho estiver vazio, mostra apenas o menu.
                System.out.println();

                for (int i = 0; i < qtdLinhasMenu; i++) {
                    System.out.println(linhasMenu[i]);
                }
            }

            // Leitura da opção principal.
            System.out.print("Escolha uma opcao: ");

            while (!sc.hasNextInt()) {
                System.out.println("Digite apenas numeros.");
                sc.next();
                System.out.print("Escolha uma opcao: ");
            }

            opcaoPrincipal = sc.nextInt();

            // Entrada nas categorias.
            if (opcaoPrincipal == 1 || opcaoPrincipal == 2 || opcaoPrincipal == 3) {

                String titulo = "";
                String categoria = "";
                String[] itens = pizzasalgada;
                double[] precos = precoSalgada;

                // Define qual categoria foi escolhida.
                if (opcaoPrincipal == 1) {
                    titulo = "PIZZAS SALGADAS";
                    categoria = "salgada";
                    itens = pizzasalgada;
                    precos = precoSalgada;
                } else if (opcaoPrincipal == 2) {
                    titulo = "PIZZAS DOCES";
                    categoria = "doce";
                    itens = pizzadoce;
                    precos = precoDoce;
                } else {
                    titulo = "BEBIDAS";
                    categoria = "bebida";
                    itens = listaBebidas;
                    precos = precoBebida;
                }

                int opcaoCategoria = -1;

                // Menu da categoria.
                do {
                    System.out.println();
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println(String.format("║ %-36.36s ║", "        PIZZARIA DELIVERY"));
                    System.out.println(String.format("║ %-36.36s ║", "        -- " + titulo + " --"));
                    System.out.println("╠══════════════════════════════════════╣");

                    // Mostra os itens da categoria sem quebrar a caixa.
                    for (int i = 1; i < itens.length; i++) {
                        String precoTexto = String.format("R$ %.2f", precos[i]).replace('.', ',');
                        String linhaProduto = String.format("%2d - %-20.20s %10s", i, itens[i], precoTexto);

                        System.out.println(String.format("║ %-36.36s ║", linhaProduto));
                    }

                    System.out.println("╠══════════════════════════════════════╣");
                    System.out.println(String.format("║ %-36.36s ║", "0 - Voltar ao menu principal"));
                    System.out.println("╚══════════════════════════════════════╝");

                    System.out.print("Escolha uma opcao: ");

                    while (!sc.hasNextInt()) {
                        System.out.println("Digite apenas numeros.");
                        sc.next();
                        System.out.print("Escolha uma opcao: ");
                    }

                    opcaoCategoria = sc.nextInt();

                    if (opcaoCategoria == 0) {
                        System.out.println("Voltando ao menu principal...");

                    } else if (opcaoCategoria >= 1 && opcaoCategoria < itens.length) {

                        // Pergunta a quantidade desejada.
                        System.out.print("Digite a quantidade desejada: ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Digite apenas numeros.");
                            sc.next();
                            System.out.print("Digite a quantidade desejada: ");
                        }

                        int quantidadeEscolhida = sc.nextInt();

                        while (quantidadeEscolhida <= 0) {
                            System.out.println("Quantidade invalida.");
                            System.out.print("Digite a quantidade desejada: ");

                            while (!sc.hasNextInt()) {
                                System.out.println("Digite apenas numeros.");
                                sc.next();
                                System.out.print("Digite a quantidade desejada: ");
                            }

                            quantidadeEscolhida = sc.nextInt();
                        }

                        // Verifica se o item já existe no carrinho.
                        int posicaoExistente = -1;

                        for (int i = 0; i < qtdTiposItens; i++) {
                            if (itensPedido[i].equals(itens[opcaoCategoria]) && categoriaPedido[i].equals(categoria)) {
                                posicaoExistente = i;
                            }
                        }

                        // Se já existe, soma a quantidade.
                        if (posicaoExistente != -1) {
                            quantidadePedido[posicaoExistente] += quantidadeEscolhida;

                        } else {

                            // Se não existe, adiciona um novo item ao carrinho.
                            if (qtdTiposItens < itensPedido.length) {
                                itensPedido[qtdTiposItens] = itens[opcaoCategoria];
                                precoUnitarioPedido[qtdTiposItens] = precos[opcaoCategoria];
                                quantidadePedido[qtdTiposItens] = quantidadeEscolhida;
                                categoriaPedido[qtdTiposItens] = categoria;
                                qtdTiposItens++;
                            } else {
                                System.out.println("Limite maximo de itens atingido.");
                            }
                        }

                        // Mostra o carrinho atualizado depois da escolha.
                        System.out.println();
                        System.out.println("╔════════════════════════════════════════════════════╗");
                        System.out.println(String.format("║ %-50.50s ║", "              CARRINHO DO PEDIDO"));
                        System.out.println("╠════════════════════════════════════════════════════╣");

                        double subtotal = 0;

                        for (int i = 0; i < qtdTiposItens; i++) {
                            double totalItem = precoUnitarioPedido[i] * quantidadePedido[i];
                            subtotal += totalItem;

                            String valorUnitario = String.format("R$%.2f", precoUnitarioPedido[i]).replace('.', ',');
                            String valorTotal = String.format("R$%.2f", totalItem).replace('.', ',');

                            String linhaItem = String.format("%02d %s", i + 1, itensPedido[i]);
                            String linhaValor = quantidadePedido[i] + " x " + valorUnitario + " = " + valorTotal;

                            System.out.println(String.format("║ %-50.50s ║", linhaItem));
                            System.out.println(String.format("║ %-50.50s ║", "   " + linhaValor));
                        }

                        String subtotalTexto = String.format("Subtotal: R$%.2f", subtotal).replace('.', ',');

                        System.out.println("╠════════════════════════════════════════════════════╣");
                        System.out.println(String.format("║ %-50.50s ║", subtotalTexto));
                        System.out.println("╚════════════════════════════════════════════════════╝");

                    } else {
                        System.out.println("Opcao invalida. Tente novamente.");
                    }

                } while (opcaoCategoria != 0);

            } else if (opcaoPrincipal == 4) {

                // Remoção de itens.
                if (qtdTiposItens > 0) {
                    int indiceRemover = -1;

                    do {
                        System.out.println();
                        System.out.println("╔════════════════════════════════════════════════════╗");
                        System.out.println(String.format("║ %-50.50s ║", "              CARRINHO DO PEDIDO"));
                        System.out.println("╠════════════════════════════════════════════════════╣");

                        double subtotalRemover = 0;

                        // Mostra o carrinho antes da remoção.
                        for (int i = 0; i < qtdTiposItens; i++) {
                            double totalItem = precoUnitarioPedido[i] * quantidadePedido[i];
                            subtotalRemover += totalItem;

                            String valorUnitario = String.format("R$%.2f", precoUnitarioPedido[i]).replace('.', ',');
                            String valorTotal = String.format("R$%.2f", totalItem).replace('.', ',');

                            String linhaItem = String.format("%02d %s", i + 1, itensPedido[i]);
                            String linhaValor = quantidadePedido[i] + " x " + valorUnitario + " = " + valorTotal;

                            System.out.println(String.format("║ %-50.50s ║", linhaItem));
                            System.out.println(String.format("║ %-50.50s ║", "   " + linhaValor));
                        }

                        String subtotalTexto = String.format("Subtotal: R$%.2f", subtotalRemover).replace('.', ',');

                        System.out.println("╠════════════════════════════════════════════════════╣");
                        System.out.println(String.format("║ %-50.50s ║", subtotalTexto));
                        System.out.println(String.format("║ %-50.50s ║", "0 - Voltar ao menu principal"));
                        System.out.println("╚════════════════════════════════════════════════════╝");

                        System.out.print("Digite o numero do item que deseja remover: ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Digite apenas numeros.");
                            sc.next();
                            System.out.print("Digite o numero do item que deseja remover: ");
                        }

                        indiceRemover = sc.nextInt();

                        if (indiceRemover == 0) {
                            System.out.println("Voltando ao menu principal...");

                        } else if (indiceRemover >= 1 && indiceRemover <= qtdTiposItens) {
                            int posicao = indiceRemover - 1;
                            int quantidadeRemover = 1;

                            // Se tiver mais de uma unidade, pergunta quantas deseja remover.
                            if (quantidadePedido[posicao] > 1) {
                                System.out.print("Digite a quantidade que deseja remover: ");

                                while (!sc.hasNextInt()) {
                                    System.out.println("Digite apenas numeros.");
                                    sc.next();
                                    System.out.print("Digite a quantidade que deseja remover: ");
                                }

                                quantidadeRemover = sc.nextInt();

                                while (quantidadeRemover <= 0 || quantidadeRemover > quantidadePedido[posicao]) {
                                    System.out.println("Quantidade invalida.");
                                    System.out.print("Digite a quantidade que deseja remover: ");

                                    while (!sc.hasNextInt()) {
                                        System.out.println("Digite apenas numeros.");
                                        sc.next();
                                        System.out.print("Digite a quantidade que deseja remover: ");
                                    }

                                    quantidadeRemover = sc.nextInt();
                                }
                            }

                            // Se remover menos do que existe, apenas diminui a quantidade.
                            if (quantidadeRemover < quantidadePedido[posicao]) {
                                quantidadePedido[posicao] -= quantidadeRemover;
                                System.out.println("Quantidade removida do item: " + itensPedido[posicao]);

                            } else {

                                // Se remover tudo, o item sai do carrinho.
                                String itemRemovido = itensPedido[posicao];

                                for (int i = posicao; i < qtdTiposItens - 1; i++) {
                                    itensPedido[i] = itensPedido[i + 1];
                                    precoUnitarioPedido[i] = precoUnitarioPedido[i + 1];
                                    quantidadePedido[i] = quantidadePedido[i + 1];
                                    categoriaPedido[i] = categoriaPedido[i + 1];
                                }

                                qtdTiposItens--;

                                itensPedido[qtdTiposItens] = null;
                                precoUnitarioPedido[qtdTiposItens] = 0;
                                quantidadePedido[qtdTiposItens] = 0;
                                categoriaPedido[qtdTiposItens] = null;

                                System.out.println("Item removido: " + itemRemovido);
                            }

                            // Se zerar o carrinho, volta automaticamente ao menu.
                            if (qtdTiposItens == 0) {
                                System.out.println("Carrinho vazio. Voltando ao menu principal...");
                                indiceRemover = 0;
                            }

                        } else {
                            System.out.println("Indice invalido.");
                        }

                    } while (indiceRemover != 0 && qtdTiposItens > 0);

                } else {
                    System.out.println("Voce ainda nao escolheu nenhum item.");
                }

            } else if (opcaoPrincipal == 5) {

                // Finalização do pedido.
                if (qtdTiposItens > 0) {
                    sc.nextLine();

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println(String.format("║ %-36.36s ║", "        PIZZARIA DELIVERY"));
                    System.out.println(String.format("║ %-36.36s ║", "        -- ENDERECO --"));
                    System.out.println("╚══════════════════════════════════════╝");

                    System.out.print("Digite o nome da sua rua: ");
                    String rua = sc.nextLine();

                    int setorIdx = rand.nextInt(setores.length);
                    double frete = precoFrete[setorIdx];

                    System.out.println();
                    System.out.println("╔══════════════════════════════════════╗");
                    System.out.println(String.format("║ %-36.36s ║", "        PIZZARIA DELIVERY"));
                    System.out.println(String.format("║ %-36.36s ║", "     -- FORMA DE PAGAMENTO --"));
                    System.out.println("╠══════════════════════════════════════╣");
                    System.out.println(String.format("║ %-36.36s ║", "1 - Dinheiro  (5% desconto)"));
                    System.out.println(String.format("║ %-36.36s ║", "2 - PIX       (7% desconto)"));
                    System.out.println(String.format("║ %-36.36s ║", "3 - Credito   (sem desconto)"));
                    System.out.println(String.format("║ %-36.36s ║", "4 - Debito    (3% desconto)"));
                    System.out.println("╚══════════════════════════════════════╝");

                    System.out.print("Escolha o metodo de pagamento: ");

                    while (!sc.hasNextInt()) {
                        System.out.println("Digite apenas numeros.");
                        sc.next();
                        System.out.print("Escolha o metodo de pagamento: ");
                    }

                    int pagamento = sc.nextInt();

                    while (pagamento < 1 || pagamento > 4) {
                        System.out.println("Opcao invalida, tente novamente.");
                        System.out.print("Escolha o metodo de pagamento: ");

                        while (!sc.hasNextInt()) {
                            System.out.println("Digite apenas numeros.");
                            sc.next();
                            System.out.print("Escolha o metodo de pagamento: ");
                        }

                        pagamento = sc.nextInt();
                    }

                    String nomePag = nomesPagamento[pagamento];
                    double desconto = descontos[pagamento];

                    double subtotal = 0;

                    System.out.println();
                    System.out.println("╔════════════════════════════════════════════════════╗");
                    System.out.println(String.format("║ %-50.50s ║", "                RESUMO DO PEDIDO"));
                    System.out.println("╠════════════════════════════════════════════════════╣");

                    // Mostra todos os itens do pedido final.
                    for (int i = 0; i < qtdTiposItens; i++) {
                        double totalItem = precoUnitarioPedido[i] * quantidadePedido[i];
                        subtotal += totalItem;

                        String valorUnitario = String.format("R$%.2f", precoUnitarioPedido[i]).replace('.', ',');
                        String valorTotal = String.format("R$%.2f", totalItem).replace('.', ',');

                        String linhaItem = String.format("%02d %s", i + 1, itensPedido[i]);
                        String linhaValor = quantidadePedido[i] + " x " + valorUnitario + " = " + valorTotal;

                        System.out.println(String.format("║ %-50.50s ║", linhaItem));
                        System.out.println(String.format("║ %-50.50s ║", "   " + linhaValor));
                    }

                    double valorDesconto = subtotal * desconto;
                    double totalComDesconto = subtotal - valorDesconto;
                    double total = totalComDesconto + frete;

                    String subtotalTexto = String.format("Subtotal dos itens: R$%.2f", subtotal).replace('.', ',');
                    String freteTexto = String.format("Frete: R$%.2f", frete).replace('.', ',');
                    String totalTexto = String.format("Total com frete: R$%.2f", total).replace('.', ',');
                    String descontoTexto = String.format("Desconto %.0f%%: -R$%.2f", desconto * 100, valorDesconto).replace('.', ',');

                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println(String.format("║ %-50.50s ║", "Rua: " + rua));
                    System.out.println(String.format("║ %-50.50s ║", "Setor: " + setores[setorIdx]));
                    System.out.println(String.format("║ %-50.50s ║", freteTexto));
                    System.out.println(String.format("║ %-50.50s ║", subtotalTexto));
                    System.out.println(String.format("║ %-50.50s ║", "Pagamento: " + nomePag));

                    if (desconto > 0) {
                        System.out.println(String.format("║ %-50.50s ║", descontoTexto));
                    } else {
                        System.out.println(String.format("║ %-50.50s ║", "Sem desconto"));
                    }

                    System.out.println("╠════════════════════════════════════════════════════╣");
                    System.out.println(String.format("║ %-50.50s ║", totalTexto));
                    System.out.println("╚════════════════════════════════════════════════════╝");

                    qtdTiposItens = 0;
                    encerrarSistema = true;

                    System.out.println("Pedido finalizado. Encerrando sistema...");

                } else {
                    System.out.println("Voce ainda nao escolheu nenhum item.");
                }

            } else if (opcaoPrincipal == 0) {

                System.out.println("Codigo encerrado.");

            } else {

                System.out.println("Opcao invalida. Tente novamente.");
            }

        } while (opcaoPrincipal != 0 && !encerrarSistema);

        sc.close();
    }
}
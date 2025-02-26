import java.util.Scanner;

public class CartaoCredito {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String numeroCartao = "";
        String bandeira = "";
        double saldo = 1000.00; // Saldo inicial fictício

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Inserir número do cartão");
            System.out.println("2. Realizar compra");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Digite o número do cartão: ");
                    numeroCartao = scanner.nextLine();
                    bandeira = identificarBandeira(numeroCartao);
                    if (!bandeira.isEmpty()) {
                        System.out.println("Bandeira identificada: " + bandeira);
                    } else {
                        System.out.println("Bandeira não reconhecida.");
                    }
                    break;
                case 2:
                    if (bandeira.isEmpty()) {
                        System.out.println("Por favor, insira o número do cartão primeiro.");
                        break;
                    }
                    System.out.print("Digite o valor da compra: ");
                    double valorCompra = scanner.nextDouble();
                    if (valorCompra <= saldo) {
                        saldo -= valorCompra;
                        System.out.println("Compra realizada com sucesso! Novo saldo: " + saldo);
                    } else {
                        System.out.println("Saldo insuficiente.");
                    }
                    break;
                case 3:
                    System.out.println("Saldo atual: " + saldo);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static String identificarBandeira(String numeroCartao) {
        if (numeroCartao == null || numeroCartao.isEmpty()) {
            return "";
        }

        int length = numeroCartao.length();
        char primeiroDigito = numeroCartao.charAt(0);

        if ((length == 13 || length == 16) && primeiroDigito == '4') {
            return "Visa";
        } else if (length == 16 && (primeiroDigito == '5' && (numeroCartao.charAt(1) >= '1' && numeroCartao.charAt(1) <= '5'))) {
            return "Mastercard";
        } else if (length == 15 && (primeiroDigito == '3' && (numeroCartao.charAt(1) == '4' || numeroCartao.charAt(1) == '7'))) {
            return "Amex";
        } else if (length == 14 && (primeiroDigito == '3' && (numeroCartao.charAt(1) == '0' || numeroCartao.charAt(1) == '6' || numeroCartao.charAt(1) == '8'))) {
            return "Diners Club International";
        } else if (length == 16 && (primeiroDigito == '3' && numeroCartao.charAt(1) == '5')) {
            return "JCB";
        } else if (length == 16 && (primeiroDigito == '6' || (primeiroDigito == '4' && numeroCartao.charAt(1) == '5'))) {
            return "ELO";
        } else {
            return "";
        }
    }
}
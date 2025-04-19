import java.util.Scanner;

public class Exercicio20 {
    public static void main(String[] args) {
        Scanner leitura = new Scanner (System.in);

        double saldo = 2500.00;

        System.out.println(String.format("""
                ***************************
                Dados iniciais do cliente:

                Nome: Daniella Silva
                Tipo Conta: Corrente
                Saldo inicial: %.2f
                ***************************

                """, saldo));

            int opcao = 0;

            while (opcao != 4) {
            double receber = 0, retirar = 0;

            System.out.println("""
                Operações

                1 - Consultar Saldo
                2 - Receber Valor
                3 - Transferir Valor
                4 - Sair

                Digite sua escolha:
                """);
                opcao = leitura.nextInt();



                        switch (opcao) {
                            case 1: 
                            System.out.println(String.format("Seu saldo é %.2f", saldo));
                            break;
                            case 2:
                            System.out.println("Digite quanto você receberá: ");
                            receber = leitura.nextDouble();
                            saldo += receber;
                            System.out.println(String.format("Saldo atualizado: R$%.2f", saldo));
                            break;
                            case 3:
                            System.out.println("Digite quanto irá retirar: ");
                            retirar = leitura.nextDouble();
                            if (retirar > saldo) {
                                System.out.println("Não é possivel transferir mais do que você tem!");
                                break;
                            } else {
                            saldo -= retirar;
                            System.out.println(String.format("Saldo atualizado: R$%.2f", saldo));
                            break;
                            }
                            case 4:
                        System.out.println("Saindo do programa!");
                        break;
                
                            default: 
                            System.out.println("Opção inválida");
                            break;
                        }
            }

                leitura.close();
    }
}

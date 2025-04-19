import java.util.Scanner;

public class Exercicio16 {
    public static void main(String[] args) {

Scanner leitura = new Scanner (System.in);
int quadrado = 4;
int raioCirculo = 5;
double resultado;

System.out.println(""" 
        Escolha uma das opções!
        1. Calcular a área do quadrado
        2. Calcular a área do círculo
        """);    
    
    int opcao = leitura.nextInt();

    switch (opcao) {
        case 1: 
        resultado = quadrado * quadrado;
        System.out.println(String.format("A area do quadrado é %.0f", resultado));
        break;
        case 2: 
        resultado = 3.14 * (raioCirculo * raioCirculo);
        System.out.println(String.format("A area do circulo é %.1f", resultado));
        break;
        default:
        System.out.println("Escolha inválida");
        break;

    } 
    leitura.close();

    }
}

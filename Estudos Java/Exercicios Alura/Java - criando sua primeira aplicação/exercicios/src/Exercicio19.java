import java.util.Scanner;

public class Exercicio19 {
    public static void main(String[] args) {
        Scanner leitura = new Scanner (System.in);

        System.out.println("Digite um numero para fazermos o fatorial!");
        int numero = leitura.nextInt();

        int fatorial = 1;

        for (int i = 1; i<=numero; i++) {
            fatorial = fatorial * i;
        }

        System.out.println(String.format("O fatorial de %d é %d", numero, fatorial));
        leitura.close();
    }
}

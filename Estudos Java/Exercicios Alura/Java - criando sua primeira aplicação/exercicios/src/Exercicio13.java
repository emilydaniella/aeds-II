import java.util.Random;
import java.util.Scanner;

public class Exercicio13 {
    public static void main(String[] args) throws Exception {
        int numero;
        numero = new Random().nextInt(100);
        System.out.println(numero);

        Scanner leitura = new Scanner (System.in);

        int contador = 0;

        System.out.println("Tente adivinhar o numero! 5 tentativas");

        while (contador != 5) {
            int senha = leitura.nextInt();

            if (senha == numero) {
                System.out.println("Parabéns! Você acertou!");
                break;
            } else {
                contador++;
                System.out.println("Você errou. Tente novamente!");
            }
        }
        leitura.close();
        }
}


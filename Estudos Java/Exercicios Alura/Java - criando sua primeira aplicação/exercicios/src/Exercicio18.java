import java.util.Scanner;

public class Exercicio18 {
    public static void main(String[] args) {
        Scanner leitura = new Scanner (System.in);

    System.out.println("Digite um numero para sabermos se é positivo ou negativo:");
    int numero = leitura.nextInt();

    if (numero >= 0) {
        System.out.println(String.format("O numero %d é positivo!", numero));
    } else {
        System.out.println(String.format ("O numero %d é negativo!", numero));
    }

        leitura.close();
    }
    
}

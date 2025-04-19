import java.util.Scanner;

public class Exercicio14 {
    public static void main(String[] args) {

        Scanner leitura = new Scanner (System.in);
        System.out.println("Insira um numero! Descobriremos se é par ou impar!");
        int numero = leitura.nextInt();

        if (numero % 2 == 0) {
            System.out.println("O numero é par!");
        } else {
            System.out.println("O numero é impar!");
        }
leitura.close();
    }
}

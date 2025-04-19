import java.util.Scanner;

public class Exercicio17 {
    public static void main(String[] args) {
        Scanner leitura = new Scanner (System.in);
        System.out.println(("Digite um numero para fazer a tabuada:"));
        int numero = leitura.nextInt();

    System.out.println(String.format("Tabuada do %d!", numero));

        for (int i = 1; i<=10; i++) {
            System.out.println(String.format("%d x %d = %d", numero, i, numero * i));
        }


        leitura.close();
    }
}

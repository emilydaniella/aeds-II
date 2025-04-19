import java.util.Scanner;   
public class Exercicio15 {
    public static void main(String[] args) {
        Scanner leitura = new Scanner (System.in);

        System.out.println(("Insira dois numeros: "));
        int numero1 = leitura.nextInt();
        int numero2 = leitura.nextInt();

        if (numero1 == numero2) {
            System.out.println("Os numeros sao iguais!");
        } else {
            if (numero1 > numero2) {
                System.out.println(String.format("Os numeros sao diferentes, o numero %d é maior", numero1));
            } else {
                System.out.println(String.format("Os numeros sao diferentes, o numero %d é maior", numero2));
            }
        }
        leitura.close();
    }
}

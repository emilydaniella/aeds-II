import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 15/02/25
*/

public class exercicio1 {

    public static boolean parada (String texto) {
        boolean parada = true;

        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false; //metodo para verificar se a palavra é FIM
        }
        return parada;
    }

    public static boolean palindromo (String texto) {
        boolean palindromo = true;

        for (int i = 0; i < texto.length() / 2; i++) {
            if (texto.charAt(i) != texto.charAt(texto.length() - 1 - i)) {
                palindromo = false; //metodo para verificar se a palavra é um palindromo, se não for, retorna false
            }
        }

        return palindromo;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in); 
        String texto = sc.nextLine(); // lê a primeira entrada de texto

        while (!parada(texto)) { //enquanto a palavra não for fim

            boolean resultado = palindromo(texto); //salva o resultado na variavel

            if (resultado == true) { 
                System.out.println("SIM"); // se o resultado for true, printa SIM
            } else {
                System.out.println("NAO"); // se não, printa NÃO
            }
                texto = sc.nextLine(); // lê a proxima entrada de texto
        }

        sc.close(); 
    }
}

package exercicio10;

import java.util.Scanner;

public class exercicio10 {

    public static boolean parada (String texto) {
        boolean parada = true;

        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }
        return parada;
    }

    public static int contador (String texto) {
        int contador = 0;
        for (int i = 0; i<texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                contador++;
            }
        }
        return contador+1;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner (System.in);
        String texto = sc.nextLine();

        while (!parada(texto)) {

            int contador = contador(texto);
            System.out.println(contador);
            texto = sc.nextLine();

        }
        sc.close();
    }
}

import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 24/02/25
*/

public class exercicio12 { 

    // método que verifica se o texto digitado é FIM
    public static boolean parada (String texto) {

        boolean parada = true;

        // verifica se o texto tem exatamente 3 caracteres e é igual a FIM
        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }

        return parada;
    }

    // método que verifica se o texto contém pelo menos uma letra maiúscula
    public static boolean maiuscula (String texto) {
        boolean maiuscula = false;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // verifica se o caractere está entre 'A' e 'Z'
            if (c >= 'A' && c <= 'Z') { 
                maiuscula = true;
            }
        }
        return maiuscula;
    }

    // método que verifica se o texto contém pelo menos uma letra minúscula
    public static boolean minuscula (String texto) {
        boolean minuscula = false;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // verifica se o caractere está entre 'a' e 'z'
            if (c >= 'a' && c <= 'z') { 
                minuscula = true;
            }
        }
        return minuscula;
    }

    // método que verifica se o texto contém pelo menos um número
    public static boolean numero (String texto) {
        boolean numero = false;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // verifica se o caractere está entre '0' e '9'
            if (c >= '0' && c <= '9') { 
                numero = true;
            }
        }
        return numero;
    }

    // método que verifica se o texto contém pelo menos um caractere especial
    public static boolean especial (String texto) {
        boolean especial = false;

        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // verifica se o caractere é um dos caracteres especiais permitidos
            if (c == '@' || c == '#' || c == '$' || c == '%' || c == '&' || c == '!' || c == '^') { 
                especial = true;
            }
        }
        return especial;
    }

    public static void main(String[] args) { 
        Scanner sc = new Scanner(System.in); 
        String texto = sc.nextLine(); // lê a primeira linha de entrada

        //até que o usuário digite FIM
        while (!parada(texto)) {
            boolean tamanho = texto.length() >= 8; // verifica se o texto tem pelo menos 8 caracteres

            boolean maiuscula = maiuscula(texto); // verifica se contém pelo menos uma letra maiúscula
            boolean minuscula = minuscula(texto); // verifica se contém pelo menos uma letra minúscula
            boolean numero = numero(texto); // verifica se contém pelo menos um número
            boolean especial = especial(texto); // verifica se contém pelo menos um caractere especial
            
            // verifica se todas as condições são verdadeiras
            if (tamanho && maiuscula && minuscula && especial && numero) {
                System.out.println("SIM"); 
            } else {
                System.out.println("NAO"); 
            }

            texto = sc.nextLine(); // lê a próxima linha de entrada do usuário
        } 

        sc.close(); 
    }
}

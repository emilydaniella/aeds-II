package exercicio6;

import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 20/02/25
*/

public class exercicio6 { 
    
    // método que verifica se o texto digitado é FIM
    public static boolean parada (String texto) {
        boolean parada = true;

        // verifica se o texto tem exatamente 3 caracteres e é igual a FIM
        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }

        return parada;
    }

    // método que verifica se o texto contém apenas vogais
    public static boolean vogais(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // verifica se o caractere não é uma vogal (maiúscula ou minúscula)
            if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')) {
                return false; 
            }
        }
        return true; 
    }

    // método que verifica se o texto contém apenas consoantes
    public static boolean consoantes(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            // verifica se o caractere está fora do intervalo de letras do alfabeto
            if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
                return false; 
            }

            // verifica se o caractere é uma vogal (maiúscula ou minúscula)
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
                c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
                return false; 
            }
        }
        return true; 
    }

    // método que verifica se o texto contém apenas números inteiros
    public static boolean numeros(String num) {
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            // verifica se o caractere não está no intervalo de 0 a 9
            if (c < '0' || c > '9') {
                return false; 
            }
        }
        return true; 
    }

    // método que verifica se o texto representa um número real (com ponto ou vírgula)
    public static boolean numReal(String num) {
        boolean ponto = false; // indica se já encontrou um separador decimal
        boolean numero = false; // indica se há pelo menos um número válido

        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);

            // verifica se o caractere é um separador decimal (ponto ou vírgula)
            if (c == '.' || c == ',') { 
                if (ponto) {
                    return false; // mais de um separador decimal encontrado
                }
                ponto = true;
            } else if (c >= '0' && c <= '9') {
                numero = true; // pelo menos um número foi encontrado
            } else {
                return false; // caractere inválido encontrado
            }
        }

        return numero; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        String texto = sc.nextLine(); // lê a primeira linha de entrada

        // laço de repetição até que o usuário digite FIM
        while (!parada(texto)) {

            boolean vogais = vogais(texto); // verifica se contém apenas vogais
            boolean consoantes = consoantes(texto); // verifica se contém apenas consoantes
            boolean numeros = numeros(texto); // verifica se contém apenas números inteiros
            boolean real = numReal(texto); // verifica se representa um número real

            // imprime SIM ou NAO para cada uma das verificações
            if (vogais) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            
            if (consoantes) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }
            
            if (numeros) {
                System.out.print("SIM ");
            } else {
                System.out.print("NAO ");
            }

            if (real) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }

            texto = sc.nextLine(); // lê a próxima linha de entrada
        }

        sc.close(); 
    }
}

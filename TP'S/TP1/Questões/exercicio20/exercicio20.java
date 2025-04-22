import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 04/03/25
*/

public class exercicio20 { 

    // método recursivo que verifica se o texto contém apenas vogais
    public static boolean vogais(String texto, int indice) {
        // caso base
        if (indice == texto.length()) {
            return true;
        }

        char c = texto.charAt(indice);

        // verifica se o caractere não é uma letra
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
            return false; 
        }

        // verifica se o caractere não é uma vogal
        if (!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')) {
            return false;
        }

        // chamada recursiva para o próximo caractere
        return vogais(texto, indice + 1);
    }

    // método recursivo que verifica se o texto contém apenas consoantes
    public static boolean consoantes(String texto, int indice) {
        // caso base
        if (indice == texto.length()) {
            return true;
        }

        char c = texto.charAt(indice);

        // verifica se o caractere não é uma letra
        if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z')) {
            return false; 
        }

        // verifica se o caractere é uma vogal
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
            return false;
        }

        // chamada recursiva para o próximo caractere
        return consoantes(texto, indice + 1);
    }

    // método recursivo que verifica se o texto contém apenas números inteiros
    public static boolean numeros(String texto, int indice) {
        // caso base
        if (indice == texto.length()) {
            return true;
        }

        char c = texto.charAt(indice);

        // verifica se o caractere não está no intervalo de '0' a '9'
        if (c < '0' || c > '9') {
            return false;
        }

        // chamada recursiva para o próximo caractere
        return numeros(texto, indice + 1);
    }

    // método recursivo que verifica se o texto representa um número real (com ponto ou vírgula)
    public static boolean numReal(String texto, int indice, boolean ponto) { 
        // caso base
        if (indice == texto.length()) {
            return true;
        }

        char c = texto.charAt(indice);

        // verifica se o caractere é um separador decimal (ponto ou vírgula)
        if (c == '.' || c == ',') {
            if (ponto) {
                return false; // mais de um separador decimal encontrado
            }
            return numReal(texto, indice + 1, true);
        }

        // verifica se o caractere é um número
        if (c >= '0' && c <= '9') {
            return numReal(texto, indice + 1, ponto);
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        String texto = sc.nextLine(); // lê a primeira linha de entrada

        // laço de repetição até que o usuário digite fim
        while (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {

            boolean apenasVogais = vogais(texto, 0); // verifica se contém apenas vogais
            boolean apenasConsoantes = consoantes(texto, 0); // verifica se contém apenas consoantes
            boolean apenasNumeros = numeros(texto, 0); // verifica se contém apenas números inteiros
            boolean numeroReal = numReal(texto, 0, false); // verifica se representa um número real

            // imprime "SIM" ou "NAO" para cada uma das verificações
            System.out.print(apenasVogais ? "SIM " : "NAO ");
            System.out.print(apenasConsoantes ? "SIM " : "NAO ");
            System.out.print(apenasNumeros ? "SIM " : "NAO ");
            System.out.println(numeroReal ? "SIM" : "NAO");

            texto = sc.nextLine(); // lê a próxima linha de entrada
        }

        sc.close(); 
    }
}

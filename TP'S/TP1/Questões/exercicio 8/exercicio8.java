package exercicio8;

import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 21/02/25
*/

public class exercicio8 {

    // método recursivo que calcula a soma dos dígitos numéricos presentes no texto
    public static int soma(String texto, int indice) {
        if (indice < 0) {
            return 0; // caso base: se o índice for menor que 0, retorna 0
        }
    
        char c = texto.charAt(indice); // obtém o caractere na posição atual

        // verifica se o caractere é um número e soma ao resultado da chamada recursiva
        if (c >= '0' && c <= '9') {
            return (c - '0') + soma(texto, indice - 1); 
        }   
    
        return soma(texto, indice - 1); // chamada recursiva ignorando caracteres não numéricos
    }

    // método que verifica se o texto digitado é FIM
    public static boolean parada (String texto) {
        boolean parada = true;

        // verifica se o texto tem exatamente 3 caracteres e é igual a FIM
        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }
        return parada;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine(); // lê a primeira linha de entrada

        // laço de repetição até que o usuário digite FIM
        while (!parada(texto)) {

            System.out.println(soma(texto, texto.length()-1)); // imprime a soma dos números no texto

            texto = sc.next(); // lê a próxima entrada
        }

        sc.close(); // fecha o scanner para evitar vazamento de recursos
    }
}

import java.util.Scanner;
import java.util.Random;

/*
Autor: Daniella Silva
Data: 15/02/25
*/

public class exercicio4 {

    // método que verifica se o texto digitado é FIM
    public static boolean parada (String texto) {
        boolean parada = true;

        // verifica se o texto tem exatamente 3 caracteres e é igual a FIM
        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }

        return parada;
    }

    // método que substitui todas as ocorrências de uma letra por outra no texto
    public static String randomizar (String texto, char letra1, char letra2) {
        int tamanho = texto.length();
        String resultado = "";

        // percorre cada caractere do texto e substitui a letra1 por letra2
        for (int i = 0; i < tamanho; i++) {
            if (texto.charAt(i) == letra1) {
                resultado += letra2;
            } else {
                resultado += texto.charAt(i);
            }
        }

        return resultado;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in); 
        Random randomizar = new Random(); // cria um objeto para geração de números aleatórios
        randomizar.setSeed(4); // define a semente do gerador aleatório para resultados previsíveis

        String texto = sc.nextLine(); // lê a primeira linha de entrada

        // até o usuário digitar FIM
        while (!parada(texto)) {

            // gera duas letras aleatórias entre a e z
            char letra1 = (char) ('a' + Math.abs(randomizar.nextInt() % 26));
            char letra2 = (char) ('a' + Math.abs(randomizar.nextInt() % 26));

            // substitui todas as ocorrências de letra1 por letra2 no texto
            String resultado = randomizar(texto, letra1, letra2);
            System.out.println(resultado); // exibe o texto modificado

            texto = sc.nextLine(); // lê a próxima linha de entrada
        }

        sc.close(); 
    }
}

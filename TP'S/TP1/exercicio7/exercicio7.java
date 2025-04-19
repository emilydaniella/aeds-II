package exercicio7;
import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 20/02/25
*/

public class exercicio7 {
    
    // método que verifica se o texto digitado é FIM
    public static boolean parada (String texto) {
        boolean parada = true;

        // verifica se o texto tem exatamente 3 caracteres e é igual a FIM
        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }
        return parada;
    }

    // método que inverte a ordem dos caracteres de um texto
    public static char [] textoInvertido (String texto) {
        int j = 0, tamanho;

        tamanho = texto.length();
        char textoInvertido [] = new char [tamanho];

        int i = tamanho - 1;

        // percorre o texto de trás para frente e armazena no novo array
        while (j < tamanho) {
            textoInvertido[j] = texto.charAt(i);
            i--;
            j++;
        }

        return textoInvertido;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in); 

        String texto = sc.nextLine(); // lê a primeira linha de entrada

        // até que o usuário digite FIM
        while (!parada(texto)) { 

            char [] textoInvertido = textoInvertido(texto); // inverte o texto

            System.out.println(textoInvertido); // imprime o texto invertido
            texto = sc.nextLine(); // lê a próxima linha de entrada
        }

        sc.close(); 
    }
}

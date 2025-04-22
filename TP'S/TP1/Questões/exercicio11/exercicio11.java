import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 24/02/25
*/

public class exercicio11 {

    // método que calcula o tamanho máximo da substring sem caracteres repetidos
    public static int tamanhoMaximo(String texto) {
        int tamanhoMaximo = 0;
        int esq = 0; 
        boolean[] caracteres = new boolean[256]; // vetor para rastrear caracteres ASCII

        // percorre o texto com um ponteiro direito
        for (int dir = 0; dir < texto.length(); dir++) {
            char c = texto.charAt(dir);

            // se o caractere já está na janela, movemos esq até remover a repetição
            while (caracteres[c]) {
                caracteres[texto.charAt(esq)] = false;
                esq++;
            }

            // adicionamos o novo caractere na janela
            caracteres[c] = true;
            tamanhoMaximo = Math.max(tamanhoMaximo, dir - esq + 1);
        }

        return tamanhoMaximo;
    }

    // método que verifica se o texto digitado é FIM
    public static boolean parada (String texto) {

        boolean parada = true;

        if (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {
            parada = false;
        }

        return parada;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        String texto = sc.nextLine(); // lê a primeira linha de entrada

        // laço de repetição até que o usuário digite fim
        while (!parada(texto)) {
            System.out.println(tamanhoMaximo(texto)); // imprime o tamanho máximo da substring sem repetição

            texto = sc.nextLine(); // lê a próxima linha de entrada
        }

        sc.close(); 
    }
}

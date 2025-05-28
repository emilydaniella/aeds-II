import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 15/02/25
*/

public class exercicio16 {

    // função recursiva para verificar se uma string é um palíndromo
    public static boolean palindromo(String texto, int i, int tamanho) {
        boolean resposta = true;

        // caso base
        if (i >= tamanho) {  
            return resposta;
        }
        
        // se os caracteres forem diferentes, não é palíndromo
        if (texto.charAt(i) != texto.charAt(tamanho)) {  
            resposta = false;
            return resposta;
        }

        // chamada recursiva movendo os índices para o centro
        return palindromo(texto, i + 1, tamanho - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String texto = sc.nextLine();  // lê a primeira linha de entrada

        // laço de repetição até que o usuário digite FIM
        while (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {

            // chama a função para verificar se é um palíndromo
            if (palindromo(texto, 0, texto.length() - 1)) {  
                System.out.println("SIM");  // se for um palíndromo, imprime "SIM"
            } else {
                System.out.println("NAO");  // caso contrário, imprime "NAO"
            }

            // verifica se ainda há entrada disponível antes de ler a próxima linha
            if (sc.hasNextLine()) {  
                texto = sc.nextLine();
            } else {
                break;  // se não houver mais entrada, sai do loop
            }
        }
        sc.close(); 
    }
}

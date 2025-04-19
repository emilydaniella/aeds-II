import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 03/03/25
*/

public class exercicio18 {

    // fun��o recursiva que aplica a cifra de c�sar ao texto
    public static String cifraDeCesar(String texto, int indice) {

        // caso base: se atingiu o final da string, retorna string vazia
        if (indice == texto.length()) {
            return "";
        }

        // desloca o caractere atual 3 posi��es � frente na tabela ASCII
        char letraCifra = (char) (texto.charAt(indice) + 3);

        // chamada recursiva para o pr�ximo caractere
        return letraCifra + cifraDeCesar(texto, indice + 1);
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        // l� a primeira linha de entrada usando MyIO
        String texto = MyIO.readLine(); 

        // la�o de repeti��o at� que o usu�rio digite fim
        while (!(texto.length() == 3 && texto.charAt(0) == 'F' && texto.charAt(1) == 'I' && texto.charAt(2) == 'M')) {

            // aplica a cifra de c�sar ao texto
            String resultado = cifraDeCesar(texto, 0);

            // imprime o resultado criptografado usando MyIO
            MyIO.println(resultado);

            // l� a pr�xima linha de entrada
            texto = MyIO.readLine();
        }

        sc.close(); // fecha o scanner para evitar vazamento de recursos
    }
}

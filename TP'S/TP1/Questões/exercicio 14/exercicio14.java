import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.Scanner;

/*
Autor: Daniella Silva
Data: 29/02/25
*/

public class exercicio14 {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // cria um arquivo de acesso aleatório para leitura e escrita
            RandomAccessFile arquivo = new RandomAccessFile("arquivo.txt", "rw"); 
            
            // lê a quantidade de números que serão armazenados
            int rep = MyIO.readInt();
            
            // escreve os números no arquivo
            for (int i = 0; i < rep; i++) {
                double num = MyIO.readDouble(); // lê um número de ponto flutuante
                arquivo.writeDouble(num); // escreve o número no arquivo
            }

            arquivo.close(); // fecha o arquivo após a escrita

            // reabre o arquivo para leitura
            arquivo = new RandomAccessFile("arquivo.txt", "r");

            // percorre os números no arquivo de trás para frente
            for (int i = rep - 1; i >= 0; i--) {
                arquivo.seek(i * 8); // posiciona o ponteiro do arquivo na posição correta
                double resultD = arquivo.readDouble(); // lê o número armazenado
                
                int resultI = (int) resultD; // converte para inteiro

                // verifica se o número é um inteiro ou um número decimal
                if (resultI == resultD) {
                    MyIO.println(resultI); // imprime como inteiro
                } else {
                    MyIO.println(resultD); // imprime como número decimal
                }
            }

            arquivo.close(); // fecha o arquivo após a leitura
        } catch (IOException e) {
            // captura possíveis erros de entrada e saída
        }
    }
}

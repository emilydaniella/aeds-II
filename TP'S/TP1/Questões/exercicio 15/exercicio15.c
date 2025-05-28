#include <stdio.h>
#include <stdlib.h>

/*
Autor: Daniella Silva
Data: 02/03/25
*/

int main() {
    FILE *arquivo; // ponteiro para o arquivo
    arquivo = fopen("arquivo.txt", "wb"); // abre o arquivo para escrita binária

    int quantidade;
    double resultado;

    // lê a quantidade de números que serão armazenados
    scanf("%i", &quantidade);

    // escreve os números no arquivo
    for (int i = 0; i < quantidade; i++) {
        scanf("%lf", &resultado); // lê um número de ponto flutuante
        fwrite(&resultado, sizeof(double), 1, arquivo); // escreve no arquivo
    }

    fclose(arquivo); // fecha o arquivo após a escrita

    arquivo = fopen("arquivo.txt", "rb"); // reabre o arquivo para leitura binária

    // percorre os números no arquivo de trás para frente
    for (int i = quantidade - 1; i >= 0; i--) {
        fseek(arquivo, i * sizeof(double), SEEK_SET); // posiciona o ponteiro do arquivo na posição correta
        double resultadoD;
        fread(&resultadoD, sizeof(double), 1, arquivo); // lê o número armazenado

        int resultadoI = (int) resultadoD; // converte para inteiro

        // verifica se o número é um inteiro ou um número decimal
        if (resultadoI == resultadoD) {
            printf("%i\n", resultadoI); // imprime como inteiro
        } else {
            printf("%g\n", resultadoD); // imprime como número decimal
        }
    }

    fclose(arquivo); // fecha o arquivo após a leitura

    return 0;
}

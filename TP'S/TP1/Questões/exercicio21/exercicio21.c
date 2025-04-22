#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
Autor: Daniella Silva
Data: 04/03/25
*/

// função recursiva que inverte um texto e armazena no vetor resultado
void textoInvertido(char texto[], int tamanho, char resultado[], int posicao) {
    // caso base
    if (tamanho <= 0) {
        resultado[posicao] = '\0'; 
        return;
    }

    // adiciona o último caractere do texto na posição correta do resultado
    resultado[posicao] = texto[tamanho - 1];

    // chamada recursiva para processar o próximo caractere
    textoInvertido(texto, tamanho - 1, resultado, posicao + 1);
}

int main() {
    char texto[1000]; // vetor para armazenar o texto de entrada

    scanf("%s", texto); // lê a primeira palavra de entrada

    // laço de repetição até que o usuário digite "FIM"
    while (strcmp(texto, "FIM") != 0) {
        int tamanho = strlen(texto); // obtém o tamanho do texto
        char resultado[1000]; // vetor para armazenar o texto invertido

        // chama a função recursiva para inverter o texto
        textoInvertido(texto, tamanho, resultado, 0);

        // imprime o texto invertido
        printf("%s\n", resultado);

        // lê a próxima palavra de entrada
        scanf("%s", texto);
    }

    return 0;
}

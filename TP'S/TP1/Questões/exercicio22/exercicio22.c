#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
Autor: Daniella Silva
Data: 26/02/25
*/

// função recursiva que soma os dígitos numéricos presentes no texto
int soma(char texto[], int indice) {
    // caso base
    if (indice < 0) {
        return 0;
    }

    char c = texto[indice];

    // verifica se o caractere é um número e soma ao resultado da chamada recursiva
    if (c >= '0' && c <= '9') {
        return (c - '0') + soma(texto, indice - 1);
    }

    // chamada recursiva ignorando caracteres não numéricos
    return soma(texto, indice - 1);
}

int main() {
    char texto[1000]; // vetor para armazenar o texto de entrada

    scanf("%s", texto); // lê a primeira palavra de entrada

    // laço de repetição até que o usuário digite "FIM"
    while (strcmp(texto, "FIM") != 0) { 
        // chama a função recursiva e imprime a soma dos números presentes no texto
        printf("%d\n", soma(texto, strlen(texto) - 1));

        // lê a próxima palavra de entrada
        scanf("%s", texto);
    }

    return 0;
}

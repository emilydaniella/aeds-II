#include <stdio.h>
#include <string.h>
#include <stdbool.h>

/*
Autor: Daniella Silva
Data: 15/02/25
*/

bool palindromo (char texto [], int tamanho) {

    bool palindromo = true;

    for (int i = 0; i < tamanho / 2; i++) {
        if (texto[i] != texto[tamanho - 1 - i]) {
            palindromo = false;
        }
    }

    return palindromo;
}

int main() {

    char texto[1000];

    // le a primeira entrada
    fgets(texto, sizeof texto, stdin);

    // remove o /n
    texto[strcspn(texto, "\n")] = 0;

    // enquanto a palavra digitada não for FIM
    while (strcmp(texto, "FIM") != 0) { 
        int tamanho = strlen(texto);

        bool resultado = palindromo(texto, tamanho);

        if (resultado == true) {
            printf("SIM\n");
        } else {
            printf("NAO\n");
        }

        //le as proximas entradas
        fgets(texto, sizeof texto, stdin);
        texto[strcspn(texto, "\n")] = 0; //remove o /n
    }

    return 0;
}

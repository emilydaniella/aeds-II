#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool palindromo (char texto [], int i, int tamanho) {
    bool resp = true;
    if (i>=tamanho) {
        return resp;
    }

    if (texto[i] != texto[tamanho] - 1) {
        resp = false;
        return resp;
    }

    return palindromo (texto, i+1, tamanho-1);
}

int main () {

    char texto [1000];

    fgets(texto, sizeof texto, stdin);

    texto[strcspn(texto, "\n")] = 0;

    while (strcmp(texto, "FIM")) {
    int tamanho = strlen (texto);

    bool resp = palindromo(texto, 0, tamanho);

        if (resp == false) {
            printf ("NAO\n");
        } else {
            printf ("SIM\n");
        }

        fgets(texto, sizeof texto, stdin);
        texto[strcspn(texto, "\n")] = 0;
    }


    return 0;
}



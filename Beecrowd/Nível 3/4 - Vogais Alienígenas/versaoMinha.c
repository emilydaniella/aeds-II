/*100% sozinha tirando o EOF, vai se fuderrrrr*/

#include <stdio.h>
#include <string.h>


int contaVogais (char vogaisAlienigenas[], char frase []) {
    int contador = 0;

    for (int i = 0; i<strlen(frase); i++) {
        for (int j = 0; j<strlen(vogaisAlienigenas); j++) {
            if (vogaisAlienigenas[j] == frase[i]) {
                contador++;
            }
        }
    }
return contador;
}

int main () {

    char vogaisAlienigenas [1000], frase [1000];

    while (scanf(" %[^\n]", vogaisAlienigenas) != EOF) {
        scanf (" %[^\n]", frase);

        int contador = contaVogais(vogaisAlienigenas, frase);

        printf ("%d\n", contador);
    }
    return 0;
}
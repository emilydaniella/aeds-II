#include <stdio.h>
#include <string.h>


int temParenteses (char expressoes [], int n) {
int contador1 = 0, contadorGeral = 0;


for (int i= 0; i< strlen(expressoes); i++) {
    char c = expressoes[i];

    if (c == '(') {
        contador1++;
    } else if ( c == ')') {
        if (contador1>0) {
            contador1--;
            contadorGeral++;
    } else {
        return 0;
    }
}
}

    if (contador1 == 0) {
        return contadorGeral;
    } else {
        return 0;
    }
}


int main () {

    char expressoes[1000];
    int n=0;

    while (scanf (" %[^\n]", expressoes) != EOF) {
        int contador = temParenteses(expressoes, n);
        n++;

        if (contador>0) {
            printf ("correct\n");
        } else {
            printf ("incorrect\n");
        }
    }

    return 0;
}
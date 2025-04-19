#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main () {

int numTraduçoes, numCriancas;
char idioma[100], traducao[100], nome[100], idiomaNativo[100];

scanf ("%d", &numTraduçoes);

for (int i=0; i<numTraduçoes; i++) {
scanf ("[ ^\n]", idioma);
scanf ("[ ^\n]", traducao);
}

scanf ("%d", &numCriancas);

for (int i = 0; i<numCriancas; i++) {
    scanf ("[ ^\n]", nome);
    scanf ("[ ^\n]", idiomaNativo);
}

for (int i = 0; i<strlen(idiomaNativo)) {
    printf (" %c", nome[i]);
    if (strcmp(idioma[0], idiomaNativo[i])) {
        printf (" %c", traducao[i]);
    } else {
        
    }
}



    return 0;
}
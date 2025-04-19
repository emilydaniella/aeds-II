#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main () {

int numTraducoes, numCriancas;
char idioma[100] [100], traducao[100] [100], nome[100] [100], idiomaNativo[100] [100];

scanf ("%d", &numTraducoes);

for (int i=0; i<numTraducoes; i++) {
scanf (" %[^\n]", idioma[i]);
scanf (" %[^\n]", traducao[i]);
}

scanf ("%d", &numCriancas);

for (int i = 0; i<numCriancas; i++) {
    scanf (" %[^\n]", nome[i]);
    scanf (" %[^\n]", idiomaNativo[i]);
}

for (int i = 0; i<numCriancas; i++) {
    printf ("%s\n", nome[i]);

    for (int j = 0; j<numTraducoes; j++) {
        if (strcmp(idioma[j], idiomaNativo[i]) == 0) {
            printf ("%s\n", traducao[j]);     
    }
}
    printf ("\n");
}
    return 0;
}
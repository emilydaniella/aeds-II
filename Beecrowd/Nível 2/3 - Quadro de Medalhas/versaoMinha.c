#include <stdio.h>
#include <string.h>

void swap (char paises[][100], int medalhasO[], int medalhasP[], int medalhasB[], int i, int j) {

    int tempO = medalhasO[i];
    medalhasO[i] = medalhasO[j];
    medalhasO[j] = tempO;

    int tempP = medalhasP[i];
    medalhasP[i] = medalhasP[j];
    medalhasP[j] = tempP;

    int tempB = medalhasB[i];
    medalhasB[i] = medalhasB[j];
    medalhasB[j] = tempB;

    char tempPaises [100];
    strcpy(tempPaises, paises[i]);
    strcpy(paises[i], paises[j]);
    strcpy (paises[j], tempPaises);
}

void bubblesort ( int medalhasO[], int medalhasP[], int medalhasB[], char paises [][100], int numeros) {

for (int i = 0; i < numeros - 1; i++) {
    for (int j = 0; j < numeros - i - 1; j++) {
        if (medalhasO[j] < medalhasO[j + 1] || 
            (medalhasO[j] == medalhasO[j + 1] && medalhasP[j] < medalhasP[j + 1]) ||
            (medalhasO[j] == medalhasO[j + 1] && medalhasP[j] == medalhasP[j + 1] && medalhasB[j] < medalhasB[j + 1]) ||
            (medalhasO[j] == medalhasO[j + 1] && medalhasP[j] == medalhasP[j + 1] && medalhasB[j] == medalhasB[j + 1]
                && strcmp(paises[j], paises[j + 1]) > 0)) {

                    swap(paises, medalhasO, medalhasP, medalhasB, j, j+1);
            }
        }
    }
}

int main () {

    int numeros;

    scanf ("%d", &numeros);

    if (numeros>= 0 && numeros <=500) {

        int medalhasOuro[10000], medalhasPrata[10000], medalhasBronze[10000];
        char paises[numeros] [100];

        for (int i = 0; i<numeros; i++) {
            scanf (" %s %d %d %d", paises[i], &medalhasOuro[i], &medalhasPrata[i], &medalhasBronze[i]);
        }

            bubblesort(medalhasOuro, medalhasPrata, medalhasBronze, paises, numeros);

            for (int i = 0; i < numeros; i++) {
                printf("%s %d %d %d\n", paises[i], medalhasOuro[i], medalhasPrata[i], medalhasBronze[i]);
            }
    }

    return 0;
}
#include <stdio.h>
#include <string.h>

void bubbleSort (int *num, int tamanho) {
    int atual = 0, proximo = 0, temp = 0;

    for (atual =1; atual < tamanho; atual++) {
        for (proximo = 0; proximo < strlen(num) - 1; proximo++) {
            if (num[proximo] > num[proximo+1]) {
                temp = num[proximo];
                num[proximo] = num[proximo+1];
                num[proximo+1] = temp;
            }
        }
    }
}


int main () {

int numeros = 0;
int num [1000], par[1000], impar[1000];

scanf ("%d", &numeros);

for (int i = 0; i<numeros; i++) {

    scanf ("%d", num [i]);

    if (num[i] % 2 == 0) {
        par[i] = num[i];
    } else {
        impar[i] = num[i];
    }

}
bubbleSort(par, strlen(par));
bubbleSort(impar, strlen(impar));

for (int i = 0; i<strlen(num); i++) {
    printf ("%d\n", par);
}

for (int i = strlen(impar); i<0; i--) {
    printf ("%d\n", impar);
}


    return 0;
}
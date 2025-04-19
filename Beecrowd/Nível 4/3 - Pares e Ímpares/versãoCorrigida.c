#include <stdio.h>

void bubbleSort (int *num, int tamanho) {
    int atual = 0, proximo = 0, temp = 0;

    for (atual = 0; atual < tamanho; atual++) {
        for (proximo = 0; proximo < tamanho - 1; proximo++) {
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
int quantidadePar = 0, quantidadeImpar =0;

scanf ("%d", &numeros);

for (int i = 0; i<numeros; i++) {

    scanf ("%d", &num [i]);

    if (num[i] % 2 == 0) {
        par[quantidadePar++] = num[i];
    } else {
        impar[quantidadeImpar++] = num[i];
    }

}

bubbleSort(par, quantidadePar);
bubbleSort(impar, quantidadeImpar);

for (int i = 0; i<quantidadePar; i++) {
    printf ("%d\n", par[i]);
}

for (int i = quantidadeImpar - 1; i>=0; i--) {
    printf ("%d\n", impar[i]);
}


    return 0;
}
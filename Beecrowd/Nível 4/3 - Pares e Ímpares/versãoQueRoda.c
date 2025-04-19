#include <stdio.h>
#include <stdlib.h>

int comparaCrescente(const void *a, const void *b) {
    return (*(int*)a - *(int*)b);
}

int comparaDecrescente(const void *a, const void *b) {
    return (*(int*)b - *(int*)a);
}

int main() {
    int numeros;
    int num[100000], par[100000], impar[100000];
    int quantidadePar = 0, quantidadeImpar = 0;

    scanf("%d", &numeros);

    for (int i = 0; i < numeros; i++) {
        scanf("%d", &num[i]);
        if (num[i] % 2 == 0) {
            par[quantidadePar++] = num[i];
        } else {
            impar[quantidadeImpar++] = num[i];
        }
    }

    qsort(par, quantidadePar, sizeof(int), comparaCrescente);

    qsort(impar, quantidadeImpar, sizeof(int), comparaDecrescente);

    for (int i = 0; i < quantidadePar; i++) {
        printf("%d\n", par[i]);
    }

    for (int i = 0; i < quantidadeImpar; i++) {
        printf("%d\n", impar[i]);
    }

    return 0;
}

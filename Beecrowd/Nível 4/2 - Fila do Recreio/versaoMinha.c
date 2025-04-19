#include <stdio.h>
#include <string.h>


void swap (int notas[], int tamanho, int j) {

    int contador = 0;

    int tempNotas = notas[j];
    notas[j] = notas[j+1];
    notas[j+1] = tempNotas;
}

void ordenaNotas (int notas[], int tamanho) {

    for (int i = 0; i<tamanho-1; i++) {
        for (int j = 0; j<tamanho-i-1; j++) {
            if (notas[j] < notas[j+1]) {
                swap(notas, tamanho, j);
            }
        }
    }
}

int main () {
    
    int n, numAlunos;
    int notas[1000];
    int notasRascunho[1000];
    int iguais;


    scanf ("%d", &n); 

    for (int i = 0; i<n; i++) {
        scanf ("%d", &numAlunos);

        for (int j = 0; j<numAlunos; j++) {
        scanf ("%d", &notas[j]);
        notasRascunho[j] = notas[j];
        }


    ordenaNotas(notas, numAlunos);
    iguais = 0;

    for (int i = 0; i<numAlunos; i++) {
        if (notas[i] == notasRascunho[i]) {
            iguais++;
        }
    }
    printf ("%d\n", iguais);
    }
    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int conectando [101], matriz [101][101], numSegmentos = 0,  conexoes = 0;

void buscaProfundidade (int v) {
conectando[v] = 1;

for (int i = 1; i<=numSegmentos; i++) {
    if (matriz [v] [i] == 1 && !conectando[i]) {
    buscaProfundidade(i);
        }
    }
}

int main () {

    int x, y;

    scanf ("%d %d", &numSegmentos, &conexoes);

    for (int i = 0; i<numSegmentos; i++) {
        conectando[i] = 0;
        for (int j = 0; j<numSegmentos; j++) {
            matriz [i][j] = 0;
        }
    }

    for (int i = 0; i<conexoes; i++) {
        scanf("%d %d", &x, &y);
        matriz[x][y] = 1;
        matriz [y][x] = 1;
    }

    buscaProfundidade(1);

    int completo = 1;
    for (int i = 1; i<numSegmentos; i++) {
        if (!conectando[i]) {
            completo = 0;
        }
    }


    if (completo) {
        printf("COMPLETO\n");
    } else {
        printf ("INCOMPLETO\n");
    }


    return 0;
}
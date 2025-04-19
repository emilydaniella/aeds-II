#include <stdio.h>
#include <string.h>

void swap (char arvores[][31], int j) {
    char tempArvores[1000];

    strcpy(tempArvores, arvores[j]);
    strcpy(arvores[j], arvores[j+1]);
    strcpy(arvores[j+1], tempArvores);
}

void ordenaArvores (char arvores[][31], int tamanho) {

    for (int i = 0; i<tamanho-1; i++) { 
        for (int j = 0; j<tamanho-i-1; j++) {  
            if (strcmp(arvores[j], arvores[j+1]) > 0) {
        swap(arvores, j);
    }
}
}
}

void contaArvores (char arvores [][31], int tamanho) {

    int contagem[10000];
    int especies = 0;

    for (int i = 0; i < tamanho; i++) {
        int encontrado = 0;
        for (int j = 0; j<especies; j++) {
            if (strcmp(arvores[i], arvores[j]) == 0) {
                contagem[j]++;
                encontrado = 1;
                break;
            }
        }

        if (!encontrado) {
            strcpy(arvores[especies], arvores[i]);
            contagem[especies] = 1;
            especies++;

        }
    }

    for (int i = 0; i < especies; i++) {
        double percentual = (contagem[i] * 100.0) / tamanho;
        printf("%s %.4f\n", arvores[i], percentual);
    }

}

int main () {

int n;
char arvores [1000][31], linha[31];

scanf ("%d", &n);
getchar();

for (int i = 0; i<n; i++) {
    int tamanho = 0;

    while (fgets(linha, sizeof(linha), stdin) && linha[0] != '\n') {
        sscanf(linha, "%[^\n]", arvores[tamanho]);
        tamanho++;
    }
    ordenaArvores(arvores, tamanho);
    contaArvores(arvores, tamanho);


if (i < n - 1) {
    printf("\n");
}
}

return 0;
}
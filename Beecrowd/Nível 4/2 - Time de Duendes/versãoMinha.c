#include <stdio.h>
#include <string.h>


void swap (int idades[], char nomes[][21], int i, int j)  {

    int tempIdades = idades[i];
    idades[i] = idades[j];
    idades[j] = tempIdades;

    char tempNomes [100];
    strcpy(tempNomes, nomes[i]);
    strcpy(nomes[i], nomes[j]);
    strcpy(nomes[j], tempNomes);
}

void ordenaIdades (int idades[], char nomes[][21], int numDuendes) {

    for (int i = 0; i<numDuendes-1; i++) {
        for (int j = 0; j<numDuendes-i-1; j++) {

    if (idades[j] < idades[j+1] || ((idades [j] == idades[j+1]) && strcmp(nomes[j], nomes[j+1]) >0) ) {
        swap(idades,nomes, j, j+1);
        }
    }
}
}

void divideLista(
    int idades[], char nomes[][21], int tamanhoGrupos,
    char lideres[][21], int idadesLideres[],
    char entregadores[][21], int idadesEntregadores[],
    char pilotos[][21], int idadesPilotos[]
) {
for (int i = 0; i<tamanhoGrupos; i++) {
    strcpy (lideres[i], nomes[i]);
    idadesLideres[i] = idades[i];

    strcpy (entregadores[i], nomes[i + tamanhoGrupos]);
    idadesEntregadores[i] = idades[i + tamanhoGrupos];

    strcpy(pilotos[i], nomes[i+2 * tamanhoGrupos]);
    idadesPilotos[i] = idades [i + 2 * tamanhoGrupos];
}
}

void exibeLista (
    int idades[], char nomes[][21], int tamanhoGrupos,
    char lideres[][21], int idadesLideres[],
    char entregadores[][21], int idadesEntregadores[],
    char pilotos[][21], int idadesPilotos[]
) {
    for (int i = 0; i<tamanhoGrupos; i++) {
        printf ("Time %d\n", i+1);
        printf ("%s %d\n", lideres[i], idadesLideres[i]);
        printf ("%s %d\n", entregadores[i], idadesEntregadores[i]);
        printf ("%s %d\n", pilotos[i], idadesPilotos[i]);
        printf ("\n");
    }
}

int main () {

int numDuendes, idades[30], tamanhoGrupos;
char nomes[30][21];

char lideres [30][21], entregadores[30][21], pilotos[30][21];
int idadesLideres[30], idadesEntregadores [30], idadesPilotos[30];


scanf ("%d", &numDuendes);

tamanhoGrupos = numDuendes/3;

if (numDuendes % 3 == 0) {

    for (int i = 0; i<numDuendes; i++) {
    scanf ("%s %d", nomes[i], &idades[i]);
    }

    ordenaIdades(idades, nomes, numDuendes);

    divideLista(idades,nomes,tamanhoGrupos,lideres,idadesLideres,entregadores,idadesEntregadores,pilotos, idadesPilotos);

    exibeLista(idades,nomes,tamanhoGrupos,lideres,idadesLideres,entregadores, idadesEntregadores,pilotos,idadesPilotos);

}
    return 0;
}
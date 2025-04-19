#include <stdio.h>
#include <string.h>

int diamantes (char texto []) {

    int tamanho = strlen(texto);
    int contador1 = 0, contador2 = 0, diamantes = 0;


    for (int i = 0; i<tamanho; i++) {
        char c = texto[i];
        if (c == '<') {
            contador1++;
    } else if (c == '>') {
        contador2++;
    }
}

for (int i = 0; i<contador1 || i<contador2; i++) {
if (contador1 > 0 && contador2> 0) {
    diamantes++;
    contador1--;
    contador2--;
}
}

return diamantes;

}

int main () {

    int testes;
    char texto [1000];

    printf ("Digite a quantidade de testes: ");
    scanf ("%d", &testes);
    getchar();

    for (int i = 0; i<testes; i++) {
        printf ("Digite o caso: ");
        fgets (texto, sizeof(texto), stdin);
    int resultado = diamantes(texto);

    printf ("%d\n", resultado);

    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main () {

int entrada = 0, valor=0;
char comando [10];


scanf ("%d", &entrada);

for (int i=0; i<entrada; i++) {
    scanf (" [^\n]", &comando[i]);

if (strncmp(comando[i], "PUSH", 4) == 0) {
    sscanf(comando + 5, "%d", &valor); 
}
} 
    return 0;
}

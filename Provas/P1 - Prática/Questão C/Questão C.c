#include <stdio.h>
#include <string.h>

int main () {
int R;

while (scanf("%d", &R) != EOF && R != 0) {
    int mark[100], leti[100];
    int pontosMark = 0, pontosLeti=0;
    int freqMark[11] = {0};
    int freqLeti [11] = {0};

    for (int i = 0; i<R; i++) {
        scanf ("%d", &mark[i]);
        freqMark[mark[i]]++;
        if (freqMark[mark[i]] == 3) {
            pontosMark += 30;
            freqMark[mark[i]] = 0;
        }
    }

    for (int i = 0; i<R; i++) {
        scanf ("%d", &leti[i]);
        freqLeti[leti[i]]++;
        if (freqLeti[leti[i]] == 3) {
            pontosLeti += 30;
            freqLeti[leti[i]] == 0;
        }
    }

    for (int i = 0; i<R; i++) {
        if (mark[i] > leti[i]) {
            pontosMark++;
        } else if(mark[i]<leti[i]) {
            pontosLeti++;
        }
        }

if (pontosMark> pontosLeti) {
    printf("M\n");
} else if (pontosMark< pontosLeti) {
    printf("M\n");
} else {
    printf ("T\n");
}
    
}
    return 0;
}
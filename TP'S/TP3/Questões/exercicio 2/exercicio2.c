#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct {
    char *id;
    char *tipo;
    char *titulo;
    char *diretor;
    char **elenco;
    int tamElenco;
    char *pais;
    Data dataAdicionado;
    int anoLancamento;
    char *classificacao;
    char *duracao;
    char **categorias;
    int tamCategorias;
} Show;

Show copiarShow(Show s) {
    Show novo;
    novo.id = strdup(s.id);
    novo.tipo = strdup(s.tipo);
    novo.titulo = strdup(s.titulo);
    novo.diretor = strdup(s.diretor);

    novo.tamElenco = s.tamElenco;
    if (novo.tamElenco > 0) {
        novo.elenco = malloc(novo.tamElenco * sizeof(char *));
        for (int i = 0; i < novo.tamElenco; i++)
            novo.elenco[i] = strdup(s.elenco[i]);
    } else {
        novo.elenco = NULL;
    }

    novo.pais = strdup(s.pais);
    novo.dataAdicionado = s.dataAdicionado;
    novo.anoLancamento = s.anoLancamento;
    novo.classificacao = strdup(s.classificacao);
    novo.duracao = strdup(s.duracao);

    novo.tamCategorias = s.tamCategorias;
    if (novo.tamCategorias > 0) {
        novo.categorias = malloc(novo.tamCategorias * sizeof(char *));
        for (int i = 0; i < novo.tamCategorias; i++)
            novo.categorias[i] = strdup(s.categorias[i]);
    } else {
        novo.categorias = NULL;
    }
    return novo;
}

int mesParaInt(char *mes) {
    if (strcmp(mes, "January") == 0) return 1;
    if (strcmp(mes, "February") == 0) return 2;
    if (strcmp(mes, "March") == 0) return 3;
    if (strcmp(mes, "April") == 0) return 4;
    if (strcmp(mes, "May") == 0) return 5;
    if (strcmp(mes, "June") == 0) return 6;
    if (strcmp(mes, "July") == 0) return 7;
    if (strcmp(mes, "August") == 0) return 8;
    if (strcmp(mes, "September") == 0) return 9;
    if (strcmp(mes, "October") == 0) return 10;
    if (strcmp(mes, "November") == 0) return 11;
    if (strcmp(mes, "December") == 0) return 12;
    return 0;
}

char *intParaMes(int m) {
    char *meses[] = {"", "January", "February", "March", "April", "May", "June", "July",
                     "August", "September", "October", "November", "December"};
    if (m >= 1 && m <= 12)
        return strdup(meses[m]);
    return strdup("NaN");
}

char *dataParaString(Data d) {
    char *str = malloc(64);
    char *mes = intParaMes(d.mes);
    sprintf(str, "%s %d, %d", mes, d.dia, d.ano);
    free(mes);
    return str;
}

char *arrayParaString(char **arr, int tam) {
    char *saida = malloc(512);
    saida[0] = '\0';
    for (int i = 0; i < tam; i++) {
        strcat(saida, arr[i]);
        if (i < tam - 1)
            strcat(saida, ", ");
    }
    return saida;
}

void mostrarShow(Show *s) {
    char *dataStr;
    if (s->dataAdicionado.dia && s->dataAdicionado.mes && s->dataAdicionado.ano)
        dataStr = dataParaString(s->dataAdicionado);
    else
        dataStr = strdup("NaN");

    char *elencoStr = (s->elenco != NULL) ? arrayParaString(s->elenco, s->tamElenco) : strdup("NaN");
    char *catStr = (s->categorias != NULL) ? arrayParaString(s->categorias, s->tamCategorias) : strdup("NaN");

    printf("=> %s ## %s ## %s ## %s ## [%s] ## %s ## %s ## %d ## %s ## %s ## [%s] ##\n",
           s->id, s->titulo, s->tipo, s->diretor, elencoStr, s->pais, dataStr,
           s->anoLancamento, s->classificacao, s->duracao, catStr);

    free(dataStr);
    free(elencoStr);
    free(catStr);
}

void lerShow(Show *s, char *linha) {
    char *campos[11];
    for (int i = 0; i < 11; i++) campos[i] = calloc(1024, 1);
    int k = 0, l = 0, len = strlen(linha);
    for (int i = 0; i < len && k < 11; i++) {
        if (linha[i] != ',') {
            if (linha[i] == '"') {
                i++;
                while (linha[i] != '"') campos[k][l++] = linha[i++];
            } else {
                campos[k][l++] = linha[i];
            }
        } else {
            campos[k][l] = '\0';
            l = 0;
            k++;
            while (linha[i + 1] == ',') {
                strcpy(campos[k], "NaN");
                i++;
                if (k < 11) k++;
            }
        }
    }

    s->id = strdup(campos[0]);
    s->tipo = strdup(campos[1]);
    s->titulo = strdup(campos[2]);
    s->diretor = strdup(campos[3]);

    // Elenco
    if (strcmp(campos[4], "NaN") != 0 && strlen(campos[4]) > 0) {
        int qtd = 1, tam = strlen(campos[4]);
        for (int j = 0; j < tam; j++) if (campos[4][j] == ',') qtd++;
        s->tamElenco = qtd;
        s->elenco = malloc(qtd * sizeof(char *));
        int ini = 0, idx = 0;
        for (int j = 0; j <= tam; j++) {
            if (campos[4][j] == ',' || campos[4][j] == '\0') {
                int sz = j - ini;
                while (campos[4][ini] == ' ') { ini++; sz--; }
                s->elenco[idx] = calloc(sz + 1, 1);
                strncpy(s->elenco[idx], campos[4] + ini, sz);
                s->elenco[idx][sz] = '\0';
                idx++;
                ini = j + 1;
            }
        }
        // Ordena elenco
        for (int j = 0; j < s->tamElenco - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < s->tamElenco; k++)
                if (strcmp(s->elenco[k], s->elenco[menor]) < 0)
                    menor = k;
            char *aux = s->elenco[j];
            s->elenco[j] = s->elenco[menor];
            s->elenco[menor] = aux;
        }
    } else {
        s->tamElenco = 0;
        s->elenco = NULL;
    }

    s->pais = strdup(campos[5]);

    // Data
    if (strcmp(campos[6], "NaN") != 0) {
        char mes[32], dia[8], ano[8];
        sscanf(campos[6], "%s %[^,], %s", mes, dia, ano);
        s->dataAdicionado.mes = mesParaInt(mes);
        s->dataAdicionado.dia = atoi(dia);
        s->dataAdicionado.ano = atoi(ano);
    } else {
        s->dataAdicionado.mes = 3;
        s->dataAdicionado.dia = 1;
        s->dataAdicionado.ano = 1900;
    }

    s->anoLancamento = atoi(campos[7]);
    s->classificacao = strdup(campos[8]);
    s->duracao = strdup(campos[9]);

    // Categorias
    if (strcmp(campos[10], "NaN") != 0 && strlen(campos[10]) > 0) {
        int qtd = 1, tam = strlen(campos[10]);
        for (int j = 0; j < tam; j++) if (campos[10][j] == ',') qtd++;
        s->tamCategorias = qtd;
        s->categorias = malloc(qtd * sizeof(char *));
        int ini = 0, idx = 0;
        for (int j = 0; j <= tam; j++) {
            if (campos[10][j] == ',' || campos[10][j] == '\0') {
                int sz = j - ini;
                while (campos[10][ini] == ' ') { ini++; sz--; }
                s->categorias[idx] = calloc(sz + 1, 1);
                strncpy(s->categorias[idx], campos[10] + ini, sz);
                s->categorias[idx][sz] = '\0';
                idx++;
                ini = j + 1;
            }
        }
        // Ordena categorias
        for (int j = 0; j < s->tamCategorias - 1; j++) {
            int menor = j;
            for (int k = j + 1; k < s->tamCategorias; k++)
                if (strcmp(s->categorias[k], s->categorias[menor]) < 0)
                    menor = k;
            char *aux = s->categorias[j];
            s->categorias[j] = s->categorias[menor];
            s->categorias[menor] = aux;
        }
    } else {
        s->tamCategorias = 0;
        s->categorias = NULL;
    }

    for (int i = 0; i < 11; i++) free(campos[i]);
}

void liberarShow(Show *s) {
    free(s->id);
    free(s->tipo);
    free(s->titulo);
    free(s->diretor);
    free(s->pais);
    free(s->classificacao);
    free(s->duracao);
    if (s->elenco) {
        for (int i = 0; i < s->tamElenco; i++) free(s->elenco[i]);
        free(s->elenco);
    }
    if (s->categorias) {
        for (int i = 0; i < s->tamCategorias; i++) free(s->categorias[i]);
        free(s->categorias);
    }
}

typedef struct {
    Show *array;
    int tam;
} Lista;

Lista *novaLista() {
    Lista *l = malloc(sizeof(Lista));
    l->array = calloc(1368, sizeof(Show));
    l->tam = 0;
    return l;
}

void inserirInicioLista(Lista *l, Show s) {
    for (int i = l->tam; i > 0; i--)
        l->array[i] = l->array[i - 1];
    l->array[0] = copiarShow(s);
    l->tam++;
}

void inserirFimLista(Lista *l, Show s) {
    l->array[l->tam++] = copiarShow(s);
}

void inserirPosLista(Lista *l, Show s, int pos) {
    for (int i = l->tam; i > pos; i--)
        l->array[i] = l->array[i - 1];
    l->array[pos] = copiarShow(s);
    l->tam++;
}

Show removerInicioLista(Lista *l) {
    Show temp = l->array[0];
    for (int i = 0; i < l->tam - 1; i++)
        l->array[i] = l->array[i + 1];
    l->tam--;
    return temp;
}

Show removerFimLista(Lista *l) {
    l->tam--;
    return l->array[l->tam];
}

Show removerPosLista(Lista *l, int pos) {
    Show temp = l->array[pos];
    for (int i = pos; i < l->tam - 1; i++)
        l->array[i] = l->array[i + 1];
    l->tam--;
    return temp;
}

void mostrarLista(Lista *l) {
    for (int i = 0; i < l->tam; i++)
        mostrarShow(&l->array[i]);
}

void lerLinha(char *linha, int max, FILE *arq) {
    if (fgets(linha, max, arq) == NULL) {
        fprintf(stderr, "Erro ao ler linha.\n");
        exit(1);
    }
    size_t len = strlen(linha);
    if (linha[len - 1] == '\n') linha[len - 1] = '\0';
}

int main() {
    Show *todos = calloc(1368, sizeof(Show));
    FILE *arq = fopen("/tmp/disneyplus.csv", "r");
    char linha[1024];
    fgets(linha, 1024, arq); // pula header

    for (int i = 0; i < 1368; i++) {
        lerLinha(linha, 1024, arq);
        lerShow(&todos[i], linha);
    }
    fclose(arq);

    Lista *lista = novaLista();
    char entrada[32];
    scanf("%s", entrada);
    while (strcmp(entrada, "FIM") != 0) {
        int id = atoi(entrada + 1) - 1;
        inserirFimLista(lista, todos[id]);
        scanf("%s", entrada);
    }

    int n;
    scanf("%d", &n);
    for (int i = 0; i < n; i++) {
        char op[8];
        scanf("%s", op);
        if (strcmp(op, "II") == 0) {
            char idstr[16];
            scanf("%s", idstr);
            int id = atoi(idstr + 1) - 1;
            inserirInicioLista(lista, todos[id]);
        } else if (strcmp(op, "IF") == 0) {
            char idstr[16];
            scanf("%s", idstr);
            int id = atoi(idstr + 1) - 1;
            inserirFimLista(lista, todos[id]);
        } else if (strcmp(op, "I*") == 0) {
            int pos;
            char idstr[16];
            scanf("%d %s", &pos, idstr);
            int id = atoi(idstr + 1) - 1;
            inserirPosLista(lista, todos[id], pos);
        } else if (strcmp(op, "RI") == 0) {
            Show s = removerInicioLista(lista);
            printf("(R) %s\n", s.titulo);
            liberarShow(&s);
        } else if (strcmp(op, "RF") == 0) {
            Show s = removerFimLista(lista);
            printf("(R) %s\n", s.titulo);
            liberarShow(&s);
        } else if (strcmp(op, "R*") == 0) {
            int pos;
            scanf("%d", &pos);
            Show s = removerPosLista(lista, pos);
            printf("(R) %s\n", s.titulo);
            liberarShow(&s);
        }
    }

    mostrarLista(lista);

    for (int i = 0; i < 1368; i++)
        liberarShow(&todos[i]);
    free(todos);
    free(lista->array);
    free(lista);

    return 0;
}
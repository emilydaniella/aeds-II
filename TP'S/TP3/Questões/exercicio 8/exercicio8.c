#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>

#define MAX_SHOWS 1400

typedef struct {
    int dia;
    int mes;
    int ano;
} Data;

typedef struct {
    char *idShow;
    char *tipo;
    char *titulo;
    char *diretor;
    char **elenco;
    size_t tamElenco;
    char *pais;
    Data dataAdicionado;
    int anoLancamento;
    char *classificacao;
    char *duracao;
    char **generos;
    size_t tamGeneros;
} Show;

// Funções auxiliares de manipulação de strings e datas
int mesParaInteiro(char *w) {
    if (strcmp(w, "January") == 0) return 1;
    if (strcmp(w, "February") == 0) return 2;
    if (strcmp(w, "March") == 0) return 3;
    if (strcmp(w, "April") == 0) return 4;
    if (strcmp(w, "May") == 0) return 5;
    if (strcmp(w, "June") == 0) return 6;
    if (strcmp(w, "July") == 0) return 7;
    if (strcmp(w, "August") == 0) return 8;
    if (strcmp(w, "September") == 0) return 9;
    if (strcmp(w, "October") == 0) return 10;
    if (strcmp(w, "November") == 0) return 11;
    if (strcmp(w, "December") == 0) return 12;
    return 0;
}

Show clonarShow(Show show) {
    Show clone;
    clone.idShow = strdup(show.idShow);
    clone.tipo = strdup(show.tipo);
    clone.titulo = strdup(show.titulo);
    clone.diretor = strdup(show.diretor);
    clone.tamElenco = show.tamElenco;
    if (clone.tamElenco > 0) {
        clone.elenco = (char **)calloc(clone.tamElenco, sizeof(char *));
        for (int i = 0; i < clone.tamElenco; i++) {
            clone.elenco[i] = strdup(show.elenco[i]);
        }
    } else {
        clone.elenco = NULL;
    }
    clone.pais = strdup(show.pais);
    clone.dataAdicionado = show.dataAdicionado;
    clone.anoLancamento = show.anoLancamento;
    clone.classificacao = strdup(show.classificacao);
    clone.duracao = strdup(show.duracao);
    clone.tamGeneros = show.tamGeneros;
    if (clone.tamGeneros > 0) {
        clone.generos = (char **)calloc(clone.tamGeneros, sizeof(char *));
        for (int i = 0; i < clone.tamGeneros; i++) {
            clone.generos[i] = strdup(show.generos[i]);
        }
    } else {
        clone.generos = NULL;
    }
    return clone;
}

void liberarShow(Show *i) {
    free(i->idShow);
    free(i->tipo);
    free(i->titulo);
    free(i->diretor);
    free(i->pais);
    free(i->classificacao);
    free(i->duracao);
    if (i->elenco != NULL) {
        for (int j = 0; j < i->tamElenco; j++) {
            free(i->elenco[j]);
        }
        free(i->elenco);
    }
    if (i->generos != NULL) {
        for (int j = 0; j < i->tamGeneros; j++) {
            free(i->generos[j]);
        }
        free(i->generos);
    }
}

void imprimirShow(Show *a) {
    // Ordena elenco
    if (a->tamElenco > 1) {
        for (size_t i = 0; i < a->tamElenco - 1; i++) {
            for (size_t j = i + 1; j < a->tamElenco; j++) {
                if (strcmp(a->elenco[i], a->elenco[j]) > 0) {
                    char *tmp = a->elenco[i];
                    a->elenco[i] = a->elenco[j];
                    a->elenco[j] = tmp;
                }
            }
        }
    }
    // Ordena gêneros
    if (a->tamGeneros > 1) {
        for (size_t i = 0; i < a->tamGeneros - 1; i++) {
            for (size_t j = i + 1; j < a->tamGeneros; j++) {
                if (strcmp(a->generos[i], a->generos[j]) > 0) {
                    char *tmp = a->generos[i];
                    a->generos[i] = a->generos[j];
                    a->generos[j] = tmp;
                }
            }
        }
    }

    // Imprime campos principais
    printf("=> %s ## %s ## %s ## %s ## [", a->idShow, a->titulo, a->tipo, a->diretor);

    // Elenco
    for (size_t i = 0; i < a->tamElenco; i++) {
        printf("%s", a->elenco[i]);
        if (i < a->tamElenco - 1) printf(", ");
    }
    printf("] ## %s ## ", a->pais);

    // Data no formato "Month day, year"
    const char *meses[] = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    if (a->dataAdicionado.mes > 0 && a->dataAdicionado.mes <= 12) {
        printf("%s %d, %d", meses[a->dataAdicionado.mes], a->dataAdicionado.dia, a->dataAdicionado.ano);
    } else {
        printf("NaN");
    }

    printf(" ## %d ## %s ## %s ## [", a->anoLancamento, a->classificacao, a->duracao);

    // Gêneros
    for (size_t i = 0; i < a->tamGeneros; i++) {
        printf("%s", a->generos[i]);
        if (i < a->tamGeneros - 1) printf(", ");
    }
    printf("] ##\n");
}

// --- Lista Duplamente Encadeada com sentinela no final ---
typedef struct Celula {
    Show *elemento;
    struct Celula *prox;
    struct Celula *ant;
} Celula;

Celula* novaCelula() {
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->elemento = NULL;
    tmp->prox = NULL;
    tmp->ant = NULL;
    return tmp;
}

Celula* novaCelulaComShow(Show show) {
    Celula *tmp = (Celula *)malloc(sizeof(Celula));
    tmp->elemento = (Show *)malloc(sizeof(Show));
    *(tmp->elemento) = clonarShow(show);
    tmp->prox = NULL;
    tmp->ant = NULL;
    return tmp;
}

typedef struct {
    Celula *primeiro; // sentinela cabeça
    Celula *ultimo;   // sentinela final
} Lista;

Lista* novaLista() {
    Lista *tmp = (Lista *)malloc(sizeof(Lista));
    tmp->primeiro = novaCelula();
    tmp->ultimo = novaCelula();
    tmp->primeiro->prox = tmp->ultimo;
    tmp->ultimo->ant = tmp->primeiro;
    return tmp;
}

int tamanhoLista(Lista *lista) {
    int tam = 0;
    for (Celula *i = lista->primeiro->prox; i != lista->ultimo; i = i->prox, tam++);
    return tam;
}

void inserirFimLista(Lista *lista, Show show) {
    Celula *tmp = novaCelulaComShow(show);
    tmp->ant = lista->ultimo->ant;
    lista->ultimo->ant->prox = tmp;
    tmp->prox = lista->ultimo;
    lista->ultimo->ant = tmp;
}

// Busca show pelo ID (string)
Show* buscarShowPorId(Show *shows, int total, const char *id) {
    for (int i = 0; i < total; i++) {
        if (shows[i].idShow && strcmp(shows[i].idShow, id) == 0)
            return &shows[i];
    }
    return NULL;
}

// Preenche a lista lendo IDs do stdin
void preencherListaInicial(Lista *lista, Show *shows, int total) {
    char entrada[255];
    scanf("%s", entrada);
    while (strcmp(entrada, "FIM") != 0) {
        Show *s = buscarShowPorId(shows, total, entrada);
        if (s != NULL) {
            inserirFimLista(lista, *s);
        }
        scanf("%s", entrada);
    }
}

// --- Quicksort para lista duplamente encadeada ---
// Compara por data, depois por título
int compararShow(Celula *a, Celula *b, long *comp) {
    (*comp)++;
    Data da = a->elemento->dataAdicionado;
    Data db = b->elemento->dataAdicionado;
    if (da.ano != db.ano) return da.ano - db.ano;
    if (da.mes != db.mes) return da.mes - db.mes;
    if (da.dia != db.dia) return da.dia - db.dia;
    (*comp)++;
    return strcmp(a->elemento->titulo, b->elemento->titulo);
}

void trocarElementos(Celula *a, Celula *b, long *mov) {
    Show *tmp = a->elemento;
    a->elemento = b->elemento;
    b->elemento = tmp;
    (*mov)++;
}

Celula* particao(Celula *low, Celula *high, long *comp, long *mov) {
    Celula *i = low->ant;
    for (Celula *j = low; j != high; j = j->prox) {
        if (compararShow(j, high, comp) <= 0) {
            i = (i == NULL) ? low : i->prox;
            if (i != j) trocarElementos(i, j, mov);
        }
    }
    i = (i == NULL) ? low : i->prox;
    if (i != high) trocarElementos(i, high, mov);
    return i;
}

void quicksortLista(Lista *lista, Celula *low, Celula *high, long *comp, long *mov) {
    if (low != NULL && high != NULL && low != high && low->ant != high) {
        Celula *pi = particao(low, high, comp, mov);
        if (pi != NULL && pi->ant != NULL)
            quicksortLista(lista, low, pi->ant, comp, mov);
        if (pi != NULL && pi->prox != NULL)
            quicksortLista(lista, pi->prox, high, comp, mov);
    }
}

// Imprime a lista ordenada (sem índice)
void mostrarLista(Lista *lista) {
    for (Celula *i = lista->primeiro->prox; i != lista->ultimo; i = i->prox) {
        imprimirShow(i->elemento);
    }
}

// --- Funções de leitura do arquivo e parsing ---
void lerLinha(char *linha, int maxsize, FILE *arquivo) {
    if (arquivo == NULL) {
        fprintf(stderr, "Erro: ponteiro de arquivo NULL passado para lerLinha().\n");
        exit(1);
    }
    if (fgets(linha, maxsize, arquivo) == NULL) {
        fprintf(stderr, "Erro ao ler linha do arquivo ou fim do arquivo atingido.\n");
        exit(1);
    }
    size_t len = strlen(linha);
    if (linha[len - 1] == '\n')
        linha[len - 1] = '\0';
}

char** separarAtributos(char* linha) {
    char **atributos = (char **)malloc(11 * sizeof(char *));
    for (int i = 0; i < 11; i++) {
        atributos[i] = (char *)calloc(1024, sizeof(char));
        strcpy(atributos[i], "NaN");
    }
    int k = 0, l = 0, len = strlen(linha);
    for (int i = 0; i < len && k < 11; i++) {
        if (linha[i] != ',') {
            if (linha[i] == '"') {
                i++;
                while (linha[i] != '"') {
                    atributos[k][l++] = linha[i++];
                }
            } else {
                atributos[k][l++] = linha[i];
            }
        } else {
            atributos[k][l] = '\0';
            l = 0;
            k++;
            while (linha[i + 1] == ',') {
                atributos[k][l++] = 'N';
                atributos[k][l++] = 'a';
                atributos[k][l++] = 'N';
                atributos[k][l] = '\0';
                i++;
                if (k < 11)
                    k++;
                l = 0;
            }
        }
    }
    return atributos;
}

void setIdShow(Show *a, char *atributo) {
    a->idShow = strdup(atributo);
}
void setTipo(Show *a, char *atributo) {
    a->tipo = strdup(atributo);
}
void setTitulo(Show *a, char *atributo) {
    a->titulo = strdup(atributo);
}
void setDiretor(Show *a, char *atributo) {
    a->diretor = strdup(atributo);
}
void setPais(Show *a, char *atributo) {
    a->pais = strdup(atributo);
}
void setData(Show *a, char *atributo) {
    if (strcmp(atributo, "NaN") != 0) {
        char mes[20], dia[5], ano[5];
        sscanf(atributo, "%s %s %s", mes, dia, ano);
        a->dataAdicionado.mes = mesParaInteiro(mes);
        a->dataAdicionado.dia = atoi(dia);
        a->dataAdicionado.ano = atoi(ano);
    } else {
        a->dataAdicionado.mes = 0;
        a->dataAdicionado.dia = 0;
        a->dataAdicionado.ano = 0;
    }
}
void setAnoLancamento(Show *a, char *atributo) {
    a->anoLancamento = atoi(atributo);
}
void setClassificacao(Show *a, char *atributo) {
    a->classificacao = strdup(atributo);
}
void setDuracao(Show *a, char *atributo) {
    a->duracao = strdup(atributo);
}
void setElenco(Show *a, char *atributo) {
    if (strcmp(atributo, "NaN") != 0 && strlen(atributo) > 0) {
        int quantidade = 1, len = strlen(atributo);
        for (int j = 0; j < len; j++)
            if (atributo[j] == ',')
                quantidade++;
        a->tamElenco = quantidade;
        a->elenco = (char **)calloc(quantidade, sizeof(char *));
        int k = 0, l = 0;
        a->elenco[0] = (char *)calloc(len + 1, sizeof(char));
        for (int j = 0; j < len; j++) {
            if (atributo[j] != ',') {
                a->elenco[k][l++] = atributo[j];
            } else {
                a->elenco[k][l] = '\0';
                k++;
                l = 0;
                a->elenco[k] = (char *)calloc(len + 1, sizeof(char));
                if (atributo[j + 1] == ' ')
                    j++;
            }
        }
        a->elenco[k][l] = '\0';
    } else {
        a->tamElenco = 0;
        a->elenco = NULL;
    }
}
void setGeneros(Show *a, char *atributo) {
    if (strcmp(atributo, "NaN") != 0 && strlen(atributo) > 0) {
        int quantidade = 1, len = strlen(atributo);
        for (int j = 0; j < len; j++)
            if (atributo[j] == ',')
                quantidade++;
        a->tamGeneros = quantidade;
        a->generos = (char **)calloc(quantidade, sizeof(char *));
        int k = 0, l = 0;
        a->generos[0] = (char *)calloc(len + 1, sizeof(char));
        for (int j = 0; j < len; j++) {
            if (atributo[j] != ',') {
                a->generos[k][l++] = atributo[j];
            } else {
                a->generos[k][l] = '\0';
                k++;
                l = 0;
                a->generos[k] = (char *)calloc(len + 1, sizeof(char));
                if (atributo[j + 1] == ' ')
                    j++;
            }
        }
        a->generos[k][l] = '\0';
    } else {
        a->tamGeneros = 0;
        a->generos = NULL;
    }
}

void lerShow(Show *a, char *linha) {
    char **atributos = separarAtributos(linha);
    setIdShow(a, atributos[0]);
    setTipo(a, atributos[1]);
    setTitulo(a, atributos[2]);
    setDiretor(a, atributos[3]);
    setElenco(a, atributos[4]);
    setPais(a, atributos[5]);
    setData(a, atributos[6]);
    setAnoLancamento(a, atributos[7]);
    setClassificacao(a, atributos[8]);
    setDuracao(a, atributos[9]);
    setGeneros(a, atributos[10]);
    for (int i = 0; i < 11; i++) free(atributos[i]);
    free(atributos);
}

void lerArquivo(Show *shows) {
    FILE *arquivo = fopen("/tmp/disneyplus.csv", "r");
    char *linha = (char *)malloc(1024 * sizeof(char));
    while (fgetc(arquivo) != '\n');
    for (int i = 0; i < 1368; i++) {
        lerLinha(linha, 1024, arquivo);
        lerShow((shows + i), linha);
    }
    free(linha);
    fclose(arquivo);
}

// --- Main ---
int main() {
    clock_t inicio, fim;
    double tempoTotal;
    long comparacoes = 0, movimentacoes = 0;

    int totalShows = 1368;
    Show *shows = (Show *)calloc(totalShows, sizeof(Show));
    lerArquivo(shows);

    Lista *listaShows = novaLista();
    preencherListaInicial(listaShows, shows, totalShows);

    inicio = clock();
    quicksortLista(listaShows, listaShows->primeiro->prox, listaShows->ultimo->ant, &comparacoes, &movimentacoes);
    fim = clock();

    tempoTotal = ((double)(fim - inicio)) / CLOCKS_PER_SEC;

    mostrarLista(listaShows);

    FILE *log = fopen("859238_quicksort2.txt", "w");
    if (log != NULL) {
        fprintf(log, "859238\t%ld\t%ld\t%.6fms\n", comparacoes, movimentacoes, tempoTotal * 1000);
        fclose(log);
    }

    for (int i = 0; i < totalShows; i++)
        liberarShow(shows + i);
    free(shows);

    // Libera lista
    Celula *cur = listaShows->primeiro;
    while (cur != NULL) {
        Celula *next = cur->prox;
        if (cur->elemento) {
            liberarShow(cur->elemento);
            free(cur->elemento);
        }
        free(cur);
        cur = next;
    }
    free(listaShows);

    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <ctype.h>
#include <time.h>


#define MAX_FILE_ITEMS_SIZE 2000
#define MAX_STR_SIZE 500
#define MAX_FILE_LINE_SIZE 5000
#define MAX_SHOWS 10000

// estrutura para representar uma data com dia, mês e ano
typedef struct
{
  int month;
  int day;
  int year;
} Date;

// estrutura que representa um show da plataforma
typedef struct
{
  char *show_id;
  char *type;
  char *title;
  char *director;
  char **cast;
  char *country;
  Date date_added;
  int release_year;
  char *rating;
  char *duration;
  char **listed_in;
} Show;


int len(char *str)
{
  int i = 0;
  if (str == NULL)
    return 0;
  while (str[i] != '\0')
    i++;
  return i;
}

// faz uma cópia alocada dinamicamente de uma string
char *copy_str(char *str)
{
  if (str == NULL)
    return NULL;
  int cp_str_size = len(str);
  char *cp_str = (char *)malloc(cp_str_size + 1);
  if (!cp_str)
    return NULL;
  for (int i = 0; i < cp_str_size; i++)
  {
    cp_str[i] = str[i];
  }
  cp_str[cp_str_size] = '\0';
  return cp_str;
}

char *append(char *str, const char *toAppend) {
  if (str == NULL) return strdup(toAppend);
  if (toAppend == NULL) return str;
  char *new_str = (char *)malloc(strlen(str) + strlen(toAppend) + 1);
  if (!new_str) return NULL;
  strcpy(new_str, str);
  strcat(new_str, toAppend);
  free(str);
  return new_str;
}

char **parse_line(char *line)
{
  bool has_quote = false;
  char **values = malloc(MAX_FILE_ITEMS_SIZE * sizeof(char *));
  if (!values)
    return NULL;
  int fIdx = 0;
  char *current_value = (char *)malloc(1);
  if (!current_value)
    return NULL;
  current_value[0] = '\0';
  int line_length = strlen(line);
  for (int i = 0; i < line_length; i++)
  {
    char c = line[i];
    if (c == '\"')
      has_quote = !has_quote;
    else if (c == ',' && !has_quote)
    {
      values[fIdx++] = current_value;
      current_value = (char *)malloc(1);
      if (!current_value)
        return NULL;
      current_value[0] = '\0';
    }
    else
    {
      char temp[2] = {c, '\0'};
      current_value = append(current_value, temp);
    }
  }
  values[fIdx++] = current_value;
  values[fIdx] = NULL;
  return values;
}

// conta quantas vezes um caractere aparece em uma string
int count_char(const char *str, char c)
{
  int cnt = 0;
  int str_length = strlen(str);
  for (int i = 0; i < str_length; i++)
  {
    if (str[i] == c)
      cnt++;
  }
  return cnt;
}


int cmp(const char *str1, const char *str2)
{
  if (str1 == NULL || str2 == NULL)
    return -1;
  return strcmp(str1, str2);
}

void cpy(char *destino, const char *origem)
{
  if (destino == NULL || origem == NULL)
    return;
  strcpy(destino, origem);
}

void order_strings(char **strings, int count)
{
  for (int i = 0; i < count - 1; i++)
  {
    for (int j = 0; j < count - i - 1; j++)
    {
      if (cmp(strings[j], strings[j + 1]) > 0)
      {
        char *temp = strings[j];
        strings[j] = strings[j + 1];
        strings[j + 1] = temp;
      }
    }
  }
}

char *trim(char *str)
{
  if (str == NULL)
    return NULL;
  int start = 0, end = len(str) - 1;
  while (isspace(str[start]))
    start++;
  while (end >= start && isspace(str[end]))
    end--;
  str[end + 1] = '\0';
  if (start > 0)
  {
    int i = 0;
    while (str[start] != '\0')
    {
      str[i++] = str[start++];
    }
    str[i] = '\0';
  }
    return str;
}

char **string_to_array(const char *str)
{
  if (str == NULL)
    return NULL;
  int total_length = strlen(str);
  int items_count = count_char(str, ',') + 1;
  char **items = (char **)malloc((items_count + 1) * sizeof(char *));
  if (!items)
    return NULL;

  int idx = 0;
  char *curr = (char *)malloc(1);
  if (!curr)
    return NULL;
  curr[0] = '\0';
  for (int i = 0; i < total_length; i++)
  {
    char c = str[i];
    if (c == ',')
    {
      items[idx++] = trim(curr);
      curr = (char *)malloc(1);
      if (!curr)
        return NULL;
      curr[0] = '\0';
    }
    else
    {
      char temp[2] = {c, '\0'};
      curr = append(curr, temp);
    }
  }
  items[idx++] = trim(curr);
  items[idx] = NULL;

  if (idx > 0)
    order_strings(items, idx);
  return items;
}

int month_to_int(const char *month_str)
{
  if (cmp(month_str, "January") == 0)
    return 1;
  else if (cmp(month_str, "February") == 0)
    return 2;
  else if (cmp(month_str, "March") == 0)
    return 3;
  else if (cmp(month_str, "April") == 0)
    return 4;
  else if (cmp(month_str, "May") == 0)
    return 5;
  else if (cmp(month_str, "June") == 0)
    return 6;
  else if (cmp(month_str, "July") == 0)
    return 7;
  else if (cmp(month_str, "August") == 0)
    return 8;
  else if (cmp(month_str, "September") == 0)
    return 9;
  else if (cmp(month_str, "October") == 0)
    return 10;
  else if (cmp(month_str, "November") == 0)
    return 11;
  else if (cmp(month_str, "December") == 0)
    return 12;
  else
    return 0;
}


char *month_to_string(int month)
{
  switch (month)
  {
  case 1:
    return "January";
  case 2:
    return "February";
  case 3:
    return "March";
  case 4:
    return "April";
  case 5:
    return "May";
  case 6:
    return "June";
  case 7:
    return "July";
  case 8:
    return "August";
  case 9:
    return "September";
  case 10:
    return "October";
  case 11:
    return "November";
  case 12:
    return "December";
  default:
    return "";
  }
}

Date parse_date(const char *date_str)
{
  Date date;
  char month_str[20];
  int day, year;
  if (sscanf(date_str, "%s %d, %d", month_str, &day, &year) == 3)
  {
    date.month = month_to_int(month_str);
    date.day = day;
    date.year = year;
    return date;
  }
  date.month = 3;
  date.day = 1;
  date.year = 1900;
  return date;
}

Show read_show(char **parsed)
{
  Show curr_show;
  curr_show.show_id = copy_str(parsed[0]);
  curr_show.type = copy_str(parsed[1]);
  curr_show.title = copy_str(parsed[2]);
  curr_show.director = copy_str(parsed[3]);
  curr_show.cast = string_to_array(parsed[4]);
  curr_show.country = copy_str(parsed[5]);
  curr_show.date_added = parse_date(parsed[6]);
  curr_show.release_year = atoi(parsed[7]);
  curr_show.rating = copy_str(parsed[8]);
  curr_show.duration = copy_str(parsed[9]);
  curr_show.listed_in = string_to_array(parsed[10]);
  return curr_show;
}
void free_show(Show *show)
{
  if (show == NULL)
    return;
  if (show->show_id) free(show->show_id);
  if (show->type) free(show->type);
  if (show->title) free(show->title);
  if (show->director) free(show->director);
  if (show->cast) {
      for (int i = 0; show->cast[i] != NULL; i++) {
          free(show->cast[i]);
      }
      free(show->cast);
  }
  if (show->country) free(show->country);
  if (show->rating) free(show->rating);
  if (show->duration) free(show->duration);
  if (show->listed_in) {
      for (int i = 0; show->listed_in[i] != NULL; i++) {
          free(show->listed_in[i]);
      }
      free(show->listed_in);
  }
}

void print_show(Show show)
{
  printf("=> %s ## ", (show.show_id && show.show_id[0] != '\0') ? show.show_id : "NaN");
  printf("%s ## ", (show.title && show.title[0] != '\0') ? show.title : "NaN");
  printf("%s ## ", (show.type && show.type[0] != '\0') ? show.type : "NaN");
  printf("%s ## ", (show.director && show.director[0] != '\0') ? show.director : "NaN");
  printf("[");
  if (show.cast && show.cast[0] != NULL)
  {
    for (int i = 0; show.cast[i] != NULL; i++)
    {
      printf("%s", (show.cast[i] && show.cast[i][0] != '\0') ? show.cast[i] : "NaN");
      if (show.cast[i + 1] != NULL)
        printf(", ");
    }
  }
  else
  {
    printf("NaN");
  }
  printf("] ## ");
  printf("%s ## ", (show.country && show.country[0] != '\0') ? show.country : "NaN");
    printf("%s %d, %d ## ", month_to_string(show.date_added.month), show.date_added.day, show.date_added.year);
    if (show.release_year != 0)
        printf("%d ## ", show.release_year);
        else
        printf("NaN ## ");
    printf("%s ## ", (show.rating && show.rating[0] != '\0') ? show.rating : "NaN");
    printf("%s ## ", (show.duration && show.duration[0] != '\0') ? show.duration : "NaN");
    printf("[");
    if (show.listed_in && show.listed_in[0] != NULL)
    {
      for (int i = 0; show.listed_in[i] != NULL; i++)
      {
        printf("%s", (show.listed_in[i] && show.listed_in[i][0] != '\0') ? show.listed_in[i] : "NaN");
        if (show.listed_in[i + 1] != NULL)
          printf(", ");
      }
    }
    else
    {
      printf("NaN");
    }
    printf("] ##\n");
}

Show *findOne(char *id, Show *shows, int count)
{
  for (int i = 0; i < count; i++)
  {
    if (shows[i].show_id != NULL && cmp(shows[i].show_id, id) == 0)
      return &shows[i];
  }
  return NULL;
}

Show *read_file(int *count)
{
  FILE *fptr = fopen("/tmp/disneyplus.csv", "r");
  if (!fptr)
    return NULL;
  Show *shows = (Show *)malloc(MAX_SHOWS * sizeof(Show));
  if (!shows)
    return NULL;
  *count = 0;
  char *line = (char *)malloc(MAX_FILE_LINE_SIZE);
    if (!line)
        return NULL;
    while (fgets(line, MAX_FILE_LINE_SIZE, fptr) != NULL)
    {
        char **parsed = parse_line(line);
        if (parsed == NULL)
            continue;
        shows[*count] = read_show(parsed);
        (*count)++;
        free(parsed);
    }
    fclose(fptr);
    free(line);
    return shows;
}

bool ja_foi_adicionado(char *id, Show *shows, int count)
{
  for (int i = 0; i < count; i++)
  {
    if (shows[i].show_id != NULL && cmp(shows[i].show_id, id) == 0)
      return true;
  }
  return false;
}
Show clone_show(Show *original)
{
  Show novo;
  novo.show_id = copy_str(original->show_id);
  novo.type = copy_str(original->type);
  novo.title = copy_str(original->title);
  novo.director = copy_str(original->director);
  novo.country = copy_str(original->country);
  novo.date_added = original->date_added;
  novo.release_year = original->release_year;
    novo.rating = copy_str(original->rating);
    novo.duration = copy_str(original->duration);
    if (original->cast)
    {
        int i = 0;
        while (original->cast[i] != NULL) i++;
        novo.cast = malloc((i + 1) * sizeof(char *));
        for (int j = 0; j < i; j++)
        {
            novo.cast[j] = copy_str(original->cast[j]);
        }
        novo.cast[i] = NULL;
    }
    else
    {
        novo.cast = NULL;
    }
    if (original->listed_in)
    {
        int i = 0;
        while (original->listed_in[i] != NULL) i++;
        novo.listed_in = malloc((i + 1) * sizeof(char *));
        for (int j = 0; j < i; j++)
        {
            novo.listed_in[j] = copy_str(original->listed_in[j]);
        }
        novo.listed_in[i] = NULL;
    }
    else
    {
        novo.listed_in = NULL;
    }
    return novo;
}

void free_shows(Show *shows, int count)
{
  for (int i = 0; i < count; i++)
  {
    free_show(&shows[i]);
  }
  free(shows);
}

void swap(Show *a, Show *b) {
  Show temp = *a;
  *a = *b;
  *b = temp;
}

//comparar shows por diretor e depois titulo
int comparar_shows(Show a, Show b, int *comparacoes) {
  (*comparacoes)++;
  int resultado = cmp(a.director, b.director);
  if (resultado == 0) {
      (*comparacoes)++;
      return cmp(a.title, b.title);
  }
  return resultado;
}

void refazer_heap(Show *array, int tamanho, int indice, int *comparacoes) {
  int maior = indice;
  int esquerda = 2 * indice + 1;
  int direita = 2 * indice + 2;

  if (esquerda < tamanho && comparar_shows(array[esquerda], array[maior], comparacoes) > 0)
      maior = esquerda;

  if (direita < tamanho && comparar_shows(array[direita], array[maior], comparacoes) > 0)
      maior = direita;

  if (maior != indice) {
      swap(&array[indice], &array[maior]);
      refazer_heap(array, tamanho, maior, comparacoes);
  }
}

void heapsort(Show *array, int tamanho, int k, int *movimentos, int *comparacoes) {
  for (int i = tamanho / 2 - 1; i >= 0; i--)
      refazer_heap(array, tamanho, i, comparacoes);

  for (int i = tamanho - 1; i >= tamanho - k; i--) {
      swap(&array[0], &array[i]);
      (*movimentos)++;
      refazer_heap(array, i, 0, comparacoes);
  }
}

int main() {
    int show_count = 0;
    Show *all_shows = read_file(&show_count);
    if (!all_shows) return 1;
  
    Show *selected = (Show *)malloc(MAX_SHOWS * sizeof(Show));
    int selected_count = 0;
  
    char input[100];
    while (true) {
      scanf(" %s", input);
      if (strcmp(input, "FIM") == 0) break;
  
      Show *s = findOne(input, all_shows, show_count);
      if (s != NULL) {
        selected[selected_count++] = clone_show(s);  // Clona para evitar shallow copy
      }
    }
  
    int mov = 0;
int comp = 0;

heapsort(selected, selected_count, selected_count, &mov, &comp);
  
    for (int i = 0; i < 10; i++) {
      print_show(selected[i]);
    }
  
    free_shows(all_shows, show_count);
    free_shows(selected, selected_count);
  
    return 0;
  }
  
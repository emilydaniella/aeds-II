
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class exercicio11 {
  public static Scanner sc = new Scanner(System.in);

  public static String[] parseLine(String line) {
    boolean hasQuote = false;
    String[] valores = new String[11];
    int fieldIndex = 0;
    StringBuilder currentValue = new StringBuilder();

    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (c == '\"') {
        hasQuote = !hasQuote;
      } else if (c == ',' && !hasQuote) {
        valores[fieldIndex++] = currentValue.toString();
        currentValue = new StringBuilder();
      } else {
        currentValue.append(c);
      }
    }

    if (fieldIndex < valores.length) {
      valores[fieldIndex] = currentValue.toString();
    }

    return valores;
  }

  private static Date parseDate(String dateStr) {
    if (dateStr == null || dateStr.trim().isEmpty()) {
      try {
        SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        return format.parse("March 1, 1900");
      } catch (ParseException e) {
        return null;
      }
    }

    try {
      SimpleDateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
      return format.parse(dateStr);
    } catch (ParseException e) {
      return null;
    }
  }

  private static String[] formatarStringEmArray(String value) {
    if (value == null || value.trim().isEmpty()) {
      return new String[0];
    }

    String[] items = value.split(",");
    for (int i = 0; i < items.length; i++) {
      items[i] = items[i].replaceFirst("^\\s+", "");
    }

    Arrays.sort(items);
    return items;
  }

  static class Show {
    private String show_id;
    private String type;
    private String title;
    private String director;
    private String[] cast;
    private String country;
    private Date date_added;
    private int release_year;
    private String rating;
    private String duration;
    private String[] listed_in;

    public Show(String show_id, String type, String title, String director, String[] cast, String country,
        Date date_added, int release_year, String rating, String duration, String[] listed_in) {
      this.show_id = show_id;
      this.type = type;
      this.title = title;
      this.director = director;
      this.cast = cast;
      this.country = country;
      this.date_added = date_added;
      this.release_year = release_year;
      this.rating = rating;
      this.duration = duration;
      this.listed_in = listed_in;
    }

    public Show() {
      this.show_id = "NaN";
      this.type = "NaN";
      this.title = "NaN";
      this.director = "NaN";
      this.cast = new String[] { "NaN" };
      this.country = "NaN";
      this.date_added = new Date(0);
      this.release_year = 0;
      this.rating = "NaN";
      this.duration = "NaN";
      this.listed_in = new String[] { "NaN" };
    }

    @Override
    public Show clone() {
      Show clone = new Show();
      clone.show_id = this.show_id;
      clone.type = this.type;
      clone.title = this.title;
      clone.director = this.director;
      clone.cast = this.cast != null ? this.cast.clone() : new String[0];
      clone.country = this.country;
      clone.date_added = this.date_added != null ? (Date) this.date_added.clone() : null;
      clone.release_year = this.release_year;
      clone.rating = this.rating;
      clone.duration = this.duration;
      clone.listed_in = this.listed_in != null ? this.listed_in.clone() : new String[0];
      return clone;
    }

    public void ler(String[] tokens) {
      if (tokens == null || tokens.length < 11)
        return;

      this.show_id = tokens[0] != null && !tokens[0].isEmpty() ? tokens[0] : null;
      this.type = tokens[1] != null && !tokens[1].isEmpty() ? tokens[1] : null;
      this.title = tokens[2] != null && !tokens[2].isEmpty() ? tokens[2] : null;
      this.director = tokens[3] != null && !tokens[3].isEmpty() ? tokens[3] : null;
      this.cast = formatarStringEmArray(tokens[4]);
      this.country = tokens[5] != null && !tokens[5].isEmpty() ? tokens[5] : null;
      this.date_added = parseDate(tokens[6]);

      try {
        this.release_year = tokens[7] != null && !tokens[7].isEmpty() ? Integer.parseInt(tokens[7]) : 0;
      } catch (NumberFormatException e) {
        this.release_year = 0;
      }

      this.rating = tokens[8] != null && !tokens[8].isEmpty() ? tokens[8] : null;
      this.duration = tokens[9] != null && !tokens[9].isEmpty() ? tokens[9] : null;
      this.listed_in = formatarStringEmArray(tokens[10]);
    }

    public void imprimir() {
      System.out.print("=> " + (show_id != null ? show_id : "NaN") + " ## ");
      System.out.print((title != null ? title : "NaN") + " ## ");
      System.out.print((type != null ? type : "NaN") + " ## ");
      System.out.print((director != null ? director : "NaN") + " ## ");

      System.out.print("[");
      for (int i = 0; i < cast.length; i++) {
        System.out.print(cast[i]);
        if (i < cast.length - 1)
          System.out.print(", ");
      }
      if (cast.length == 0)
        System.out.print("NaN");
      System.out.print("] ## ");

      System.out.print((country != null ? country : "NaN") + " ## ");

      if (date_added != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        System.out.print(sdf.format(date_added) + " ## ");
      } else {
        System.out.print("NaN ## ");
      }

      System.out.print((release_year != 0 ? release_year : "NaN") + " ## ");
      System.out.print((rating != null ? rating : "NaN") + " ## ");
      System.out.print((duration != null ? duration : "NaN") + " ## ");

      System.out.print("[");
      for (int i = 0; i < listed_in.length; i++) {
        System.out.print(listed_in[i]);
        if (i < listed_in.length - 1)
          System.out.print(", ");
      }
      if (listed_in.length == 0)
        System.out.print("NaN");
      System.out.println("] ##");
    }

    public String getshow_id() {
      return show_id;
    }

    public void setshow_id(String show_id) {
      this.show_id = show_id;
    }

    public String getType() {
      return type;
    }

    public void setType(String type) {
      this.type = type;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDirector() {
      return director;
    }

    public void setDirector(String director) {
      this.director = director;
    }

    public String[] getCast() {
      return cast;
    }

    public void setCast(String[] cast) {
      this.cast = cast;
    }

    public String getCountry() {
      return country;
    }

    public void setCountry(String country) {
      this.country = country;
    }

    public Date getdate_added() {
      return date_added;
    }

    public void setdate_added(Date date_added) {
      this.date_added = date_added;
    }

    public int getrelease_year() {
      return release_year;
    }

    public void setrelease_year(int release_year) {
      this.release_year = release_year;
    }

    public String getRating() {
      return rating;
    }

    public void setRating(String rating) {
      this.rating = rating;
    }

    public String getDuration() {
      return duration;
    }

    public void setDuration(String duration) {
      this.duration = duration;
    }

    public String[] getListedIn() {
      return listed_in;
    }

    public void setListedIn(String[] listedIn) {
      this.listed_in = listedIn;
    }
  }

  public static Show store(String[] tokens) {
    Show show = new Show();
    show.ler(tokens);
    return show;
  }

  public static Show[] readFile() {
    String path = "/tmp/disneyplus.csv";
    String line = "";
    Show[] temp = new Show[1368];
    int count = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      line = br.readLine();
      while ((line = br.readLine()) != null) {
        String[] currLine = parseLine(line);
        temp[count++] = store(currLine);
      }
    } catch (IOException e) {
      System.err.println("erro ao ler o arquivo: " + e.getMessage());
    }

    Show[] shows = new Show[count];
    for (int i = 0; i < count; i++)
      shows[i] = temp[i];
    return shows;
  }

  public static Show acharShowPeloId(Show[] shows, String id) {
    Show show = null;
    int idx = 0;
    while (idx < shows.length && show == null) {
      if (shows[idx].getshow_id().equals(id)) {
        show = shows[idx];
      }
      idx++;
    }
    return show;
  }

  public long now(){
		return new Date().getTime();
	}

    public void insercaoTitulo(Show[] array, int inicio, int fim) {
        for (int i = inicio + 1; i <= fim; i++) {
            Show chave = array[i];
            int j = i - 1;
            while (j >= inicio && array[j].getTitle().compareToIgnoreCase(chave.getTitle()) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chave;
        }
    }

    public void coutingSort(Show[] array, int tamanho) {
        File registro = new File("./859238_countingsort.txt");
        long inicio = new Date().getTime();
        int comparacoes = 0;
        int movimentos = 0;
    
        // Encontrar o maior ano
        int maior = array[0].getrelease_year();
        for (int i = 1; i < tamanho; i++) {
            if (array[i].getrelease_year() > maior) {
                maior = array[i].getrelease_year();
            }
        }
    
        int[] count = new int[maior + 1];
        Show[] ordenado = new Show[tamanho];
    
        // Inicializar count
        for (int i = 0; i < count.length; i++) count[i] = 0;
    
        // Contar ocorrências
        for (int i = 0; i < tamanho; i++) {
            count[array[i].getrelease_year()]++;
            movimentos++;
        }
    
        // Acumular posições
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
    
        // Ordenar por ano (Counting Sort)
        for (int i = tamanho - 1; i >= 0; i--) {
            int ano = array[i].getrelease_year();
            ordenado[count[ano] - 1] = array[i];
            count[ano]--;
            movimentos++;
        }
    
        // Agrupar por ano e ordenar por título com Insertion Sort
        int i = 0;
        while (i < tamanho) {
            int j = i + 1;
            while (j < tamanho && ordenado[j].getrelease_year() == ordenado[i].getrelease_year()) {
                j++;
            }
            insercaoTitulo(ordenado, i, j - 1); // desempate entre i e j-1
            i = j;
        }
    
        // Copiar de volta
        for (int k = 0; k < tamanho; k++) {
            array[k] = ordenado[k];
        }
    
        long fim = new Date().getTime();
        long duracao = fim - inicio;
    
        try (FileWriter registros = new FileWriter(registro)) {
            registros.write("859238\t" + comparacoes + "\t" + movimentos + "\t" + duracao / 1000.0 + "s.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Desempate usando Insertion Sort por título
    public void coutingSortTitulo(Show[] array, int inicio, int fim) {
        for (int i = inicio + 1; i <= fim; i++) {
            Show chave = array[i];
            int j = i - 1;
            while (j >= inicio && array[j].getTitle().compareToIgnoreCase(chave.getTitle()) > 0) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = chave;
        }
    }
    
  public static void main(String[] args) {
    Show[] shows = readFile();
    ArrayList<Show> listaShows = new ArrayList<Show>();
    String linhaEntrada = "";
  
    while (!linhaEntrada.equals("FIM")) {
      linhaEntrada = sc.next();
      if (!linhaEntrada.equals("FIM")) {
        Show show = acharShowPeloId(shows, linhaEntrada);
        listaShows.add(show);
      }
    }
  
    // Converte lista para array
    Show[] ordenados = listaShows.toArray(new Show[0]);
  
    // Ordena o array com sua função de ordenação por seleção
new exercicio11().coutingSort(ordenados, ordenados.length);  
    // Imprime os shows já ordenados
    for (Show show : ordenados) {
      if (show != null) {
        show.imprimir();
      }
    }
  
    sc.close();
  }
}
  
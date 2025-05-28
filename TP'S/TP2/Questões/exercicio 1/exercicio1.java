import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

// classe principal que processa os dados do disney+
public class exercicio1 {
  public static Scanner sc = new Scanner(System.in);

  // analisa uma linha csv tratando campos entre aspas corretamente
  public static String[] parseLine(String line) {
    boolean hasQuote = false;
    String[] values = new String[11]; // define 11 campos fixos
    int fieldIndex = 0;
    StringBuilder currentValue = new StringBuilder();

    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);

      if (c == '\"') {
        hasQuote = !hasQuote;
      } else if (c == ',' && !hasQuote) {
        values[fieldIndex++] = currentValue.toString();
        currentValue = new StringBuilder();
      } else {
        currentValue.append(c);
      }
    }

    // adiciona o ultimo valor
    if (fieldIndex < values.length) {
      values[fieldIndex] = currentValue.toString();
    }

    return values;
  }

  // converte string de data para objeto date no formato "month d, yyyy"
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

  // divide string por virgula, remove espaco e ordena
  private static String[] parseStrArray(String value) {
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

  // classe que representa um show do disney+
  static class Show implements Cloneable {
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

    // construtor completo
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

    // construtor vazio
    public Show() {
      this.show_id = null;
      this.type = null;
      this.title = null;
      this.director = null;
      this.cast = new String[0];
      this.country = null;
      this.date_added = null;
      this.release_year = 0;
      this.rating = null;
      this.duration = null;
      this.listed_in = new String[0];
    }

    // cria uma copia do objeto atual
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

    // le os dados de um show a partir do array de tokens
    public void ler(String[] tokens) {
      if (tokens == null || tokens.length < 11) return;

      this.show_id = tokens[0] != null && !tokens[0].isEmpty() ? tokens[0] : null;
      this.type = tokens[1] != null && !tokens[1].isEmpty() ? tokens[1] : null;
      this.title = tokens[2] != null && !tokens[2].isEmpty() ? tokens[2] : null;
      this.director = tokens[3] != null && !tokens[3].isEmpty() ? tokens[3] : null;
      this.cast = parseStrArray(tokens[4]);
      this.country = tokens[5] != null && !tokens[5].isEmpty() ? tokens[5] : null;
      this.date_added = parseDate(tokens[6]);

      try {
        this.release_year = tokens[7] != null && !tokens[7].isEmpty() ? Integer.parseInt(tokens[7]) : 0;
      } catch (NumberFormatException e) {
        this.release_year = 0;
      }

      this.rating = tokens[8] != null && !tokens[8].isEmpty() ? tokens[8] : null;
      this.duration = tokens[9] != null && !tokens[9].isEmpty() ? tokens[9] : null;
      this.listed_in = parseStrArray(tokens[10]);
    }

    // imprime os dados do show no formato especificado
    public void imprimir() {
      System.out.print("=> " + (show_id != null ? show_id : "NaN") + " ## ");
      System.out.print((title != null ? title : "NaN") + " ## ");
      System.out.print((type != null ? type : "NaN") + " ## ");
      System.out.print((director != null ? director : "NaN") + " ## ");

      System.out.print("[");
      for (int i = 0; i < cast.length; i++) {
        System.out.print(cast[i]);
        if (i < cast.length - 1) System.out.print(", ");
      }
      if (cast.length == 0) System.out.print("NaN");
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
        if (i < listed_in.length - 1) System.out.print(", ");
      }
      if (listed_in.length == 0) System.out.print("NaN");
      System.out.println("] ##");
    }

    // getters
    public String getShowId() { return show_id; }
  }

  // cria um objeto show com base nos tokens
  public static Show store(String[] tokens) {
    Show show = new Show();
    show.ler(tokens);
    return show;
  }

  // le o arquivo csv e retorna todos os shows
  public static Show[] readFile() {
    String path = "/tmp/disneyplus.csv";
    String line = "";
    Show[] temp = new Show[10000];
    int count = 0;

    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      // ignora o cabecalho
      line = br.readLine();

      while ((line = br.readLine()) != null) {
        String[] currLine = parseLine(line);
        temp[count++] = store(currLine);
      }
    } catch (IOException e) {
      System.err.println("erro ao ler o arquivo: " + e.getMessage());
    }

    Show[] shows = new Show[count];
    for (int i = 0; i < count; i++) shows[i] = temp[i];
    return shows;
  }

  // procura um show pelo id fornecido
  public static Show findOne(String id, Show[] shows) {
    for (int i = 0; i < shows.length; i++) {
      if (shows[i] != null && shows[i].getShowId() != null && shows[i].getShowId().equals(id)) {
        return shows[i];
      }
    }
    return null;
  }

  // metodo principal
  public static void main(String args[]) {
    Show[] shows = readFile();
    String id = "";

    while (true) {
      id = sc.nextLine();
      if (id.equals("FIM")) break;

      Show currShow = findOne(id, shows);
      if (currShow != null) currShow.imprimir();
    }

    sc.close();
  }
}

package org.fritter.sanpark.scraper;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class Scraper {

  private static final String BASE_URL = "https://www.sanparks.org";
  private static final Double UPPER_PRICE = 2000.0;
  private static final Double LOWER_PRICE = 900.0;
  private static final String FROM_DATE = "2019-11-05";
  private static final String TO_DATE = "2019-11-09";

  private final RestTemplate restTemplate;

  private String fromDate;
  private String toDate;

  public Scraper() {
    this.restTemplate = new RestTemplate();
  }

  public void scrap(String fromDate, String toDate) {
    System.out.println("\n");
    System.out.println(String.format("From: %s - To: %s", fromDate, toDate));
    this.fromDate = fromDate;
    this.toDate = toDate;
    try {
      Document document = Jsoup.connect("https://www.sanparks.org/parks/kruger/tourism/availability.php").get();
      Map<String, String> campLinks = document.getElementsByClass("blackBug").stream()
          .flatMap(element -> element.getElementsByTag("a").stream())
          .collect(Collectors.toMap(Element::text, element -> element.attr("href")));

      List<String> bungalowLinks = campLinks.values().parallelStream()
          .flatMap(link -> loadBungalowLinksByPrice(link).stream())
          .filter(this::hasAvailableAccommodation)
          .map(path -> BASE_URL + path)
          .collect(Collectors.toList());

      bungalowLinks.forEach(System.out::println);

//      System.out.println(bungalowLinks);

    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }

  }

  private List<String> loadBungalowLinksByPrice(final String path) {
    try {
      Document document = Jsoup.connect(BASE_URL + path).get();

      return document.getElementsByClass("default availability zebra").stream()
          .flatMap(table -> table.getElementsByTag("tr").stream())
          .filter(tableRow -> !tableRow.text().contains("Accommodation Type"))
          .filter(this::filterForPriceRange)
          .map(tableRow -> tableRow.getElementsByAttribute("href").get(0).attr("href"))
          .map(newPath -> path.substring(0, path.indexOf("tourism/") + "tourism/".length()) + newPath)
          .collect(Collectors.toList());
    } catch (IOException e) {
      throw new UncheckedIOException(e);
    }
  }

  private boolean hasAvailableAccommodation(final String path) {
    final Document doc = loadAvailabilityOfBungalow(path);
    return doc.getElementsByTag("tbody").stream()
        .filter(tbody -> !tbody.getElementsByTag("th").isEmpty())
        .flatMap(tbody -> tbody.getElementsByTag("tr").stream())
        .filter(row -> !row.text().contains("Date"))
        .flatMap(row -> row.getElementsByClass("c availableNo").stream())
        .map(Element::text)
        .allMatch(available -> "Yes".equals(available.trim()));
  }

  private Document loadAvailabilityOfBungalow(final String path) {
    final int begin = path.indexOf("?");
    final int end = path.indexOf("&");
    final String id = path.substring(begin, end).substring("id=".length());
    final String resort = path.substring(end + 1).substring("resort=".length());

    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("from_date", fromDate);
    map.add("to_date", toDate);
    map.add("Submit", "");
    map.add("resort", resort);
    map.add("unit_id", resort);
    map.add("id", id);
    map.add("action", "submit");

    HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL + path, request, String.class);
    return Jsoup.parse(response.getBody());
  }

  private boolean filterForPriceRange(final Element tableRow) {
    final String rawPrice = tableRow.getElementsByTag("td").get(2).text(); // get second column
    final Double price = rawPrice.startsWith("R") ? Double.valueOf(rawPrice.substring(1)) : Double.valueOf(rawPrice);
    return price < UPPER_PRICE && price > LOWER_PRICE;
  }

}

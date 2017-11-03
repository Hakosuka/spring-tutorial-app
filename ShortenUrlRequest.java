/**
 * This is a Plain Old Java Object which Spring will map the request for a
 * shortened URL into.
 */
public class ShortenUrlRequest {
  //The following annotations are for validating the request.
  @NotNull
  @Size(min = 5, max = 1024)
  private String url;
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}

@SpringBootApplication
/**
 * This is the main class of the app, used to initialise the Spring context
 * including the main components in this project and starts the web app inside
 * an embedded Apache Tomcat web container.
 */
public class UrlShortenerApp {
  public static void main(String[] args){
    SpringApplication.run(UrlShortenerApp.class, args);
  }
}

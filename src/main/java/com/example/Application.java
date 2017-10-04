package com.example;

import java.io.*;
import java.util.Objects;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.example.model.GameStatistics;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import spark.TemplateEngine;
import spark.template.freemarker.FreeMarkerEngine;

import com.example.appl.GameCenter;
import com.example.ui.WebServer;

/**
 * The entry point for the Guessing Game web application.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public final class Application {
  private static final Logger LOG = Logger.getLogger(Application.class.getName());

  //
  // Application Launch method
  //

  /**
   * Entry point for the Guessing Game web application.
   *
   * <p>
   * It wires the application components together.  This is an example
   * of <a href='https://en.wikipedia.org/wiki/Dependency_injection'>Dependency Injection</a>
   * </p>
   *
   * @param args
   *    Command line arguments; none expected.
   */
  public static void main(String[] args) throws IOException, ParseException, InterruptedException {
    // initialize Logging
    try {
      ClassLoader classLoader = Application.class.getClassLoader();
      final InputStream logConfig = classLoader.getResourceAsStream("log.properties");
      LogManager.getLogManager().readConfiguration(logConfig);
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println("Could not initialize log manager because: " + e.getMessage());
    }
    //TODO: set the value of global stats from file
    JSONParser jsonParser = new JSONParser();
    JSONObject a = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/globalStats.json"));

    GameStatistics.setGlobalWins(Integer.parseInt(a.get("globalWins").toString()));
    GameStatistics.setTotalPlays(Integer.parseInt(a.get("globalGames").toString()));
    // create the one and only game center
    final GameCenter gameCenter = new GameCenter();

    // The application uses FreeMarker templates to generate the HTML
    // responses sent back to the client. This will be the engine processing
    // the templates and associated data.
    final TemplateEngine templateEngine = new FreeMarkerEngine();

    // inject the game center and freemarker engine into web server
    final WebServer webServer = new WebServer(gameCenter, templateEngine);

    // inject web server into application
    final Application app = new Application(webServer);

    // start the application up
    app.initialize();
  }

  //
  // Attributes
  //

  private final WebServer webServer;

  //
  // Constructor
  //

  private Application(final WebServer webServer) {
    // validation
    Objects.requireNonNull(webServer, "webServer must not be null");
    //
    this.webServer = webServer;
  }

  //
  // Private methods
  //

  private void initialize() {
    LOG.fine("Application is initializing.");

    // configure Spark and startup the Jetty web server
    webServer.initialize();

    // other applications might have additional services to configure

    LOG.fine("Application initialization complete.");
  }
}

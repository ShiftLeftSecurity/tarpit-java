package io.shiftleft.tarpit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import io.shiftleft.tarpit.util.Unzipper;

/**
 * Servlet implementation class FileUploader
 */
@WebServlet("/FileUploader")
@MultipartConfig
public class FileUploader extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private static String productSourceFolder = System.getenv("PRODUCT_SRC_FOLDER");
  private static String productDestinationFolder = System.getenv("PRODUCT_DST_FOLDER");

  /**
   * @see HttpServlet#HttpServlet()
   */
  public FileUploader() {
    super();
  }


  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Part filePart = request.getPart("zipFile");

    InputStream input = filePart.getInputStream();

    File targetFile = new File(productSourceFolder + filePart.getSubmittedFileName());

    targetFile.createNewFile();
    OutputStream out = new FileOutputStream(targetFile);

    byte[] buffer = new byte[1024];
    int bytesRead;

    while ((bytesRead = input.read(buffer)) != -1) {
      out.write(buffer, 0, bytesRead);
    }

    input.close();
    out.flush();
    out.close();

    Unzipper.unzipFile(targetFile.getAbsolutePath(), productDestinationFolder);

    doGet(request, response);
  }

}
package io.shiftleft.tarpit.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class Unzipper {

  public static void unzipFile(String zipFileWithAbsolutePath, String destination)
      throws IOException {
    if (!doesFileExists(zipFileWithAbsolutePath)) {
      throw new FileNotFoundException("The given zip file not found: " + zipFileWithAbsolutePath);
    }

    isFilenameValid(zipFileWithAbsolutePath);

    String fileName = getFileFromPath(zipFileWithAbsolutePath);
    String finalDestination = getFinalDestination(fileName, destination);
    createDirectoryNamedAsZipFile(finalDestination);

    try {
      // Initiate ZipFile object with the path/name of the zip file.
      ZipFile zipFile = new ZipFile(zipFileWithAbsolutePath);

      // Extracts all files to the path specified
      zipFile.extractAll(finalDestination);

    } catch (ZipException e) {
      e.printStackTrace();
    }

  }

  private static void isFilenameValid(String fileName) throws IOException {
    File f = new File(fileName);
    f.getCanonicalPath();
  }

  private static boolean doesFileExists(String fileName) {
    File f = new File(fileName);
    return f.exists();
  }

  private static String getFileFromPath(String path) {
    File fileWithPath = new File(path);
    return fileWithPath.getName();
  }

  private static String getFinalDestination(String zipFile, String destination) {
    String targetDirectory = zipFile.replaceFirst("[.][^.]+$", "");
    String finalDestination = destination + targetDirectory;
    return finalDestination;
  }

  private static void createDirectoryNamedAsZipFile(String finalDestination) {
    FileSystem fileSystem = FileSystems.getDefault();

    if (Files.exists(fileSystem.getPath(finalDestination))) {
      try {
        delete(new File(finalDestination));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      Files.createDirectory(fileSystem.getPath(finalDestination));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void delete(File file) throws IOException {

    for (File childFile : file.listFiles()) {

      if (childFile.isDirectory()) {
        delete(childFile);
      } else {
        if (!childFile.delete()) {
          throw new IOException();
        }
      }
    }

    if (!file.delete()) {
      throw new IOException();
    }
  }
}
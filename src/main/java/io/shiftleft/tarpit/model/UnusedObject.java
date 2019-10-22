package io.shiftleft.tarpit.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UnusedObject {
  private static ObjectMapper deserializer = new ObjectMapper().enableDefaultTyping();
  private static ObjectMapper serializer = new ObjectMapper();
  private static String uri = "http://mycompany.com";
  private String fromAddress = "orders@mycompany.com";
}
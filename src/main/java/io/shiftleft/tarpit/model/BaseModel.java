package io.shiftleft.tarpit.model;

import io.shiftleft.tarpit.annotation.SensitiveRedact;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class BaseModel {

  @Override
  public final String toString() {

    StringBuilder toString = new StringBuilder();
    toString.append(this.getClass().getName()).append("[");
    Field[] fields = this.getClass().getDeclaredFields();

    for (int i = 0; i < fields.length; i++) {

      Field field = fields[i];

      try {
        String name = field.getName();
        Object value;

        if (!Modifier.isPublic(field.getModifiers())) {
          field.setAccessible(true);
        }

        value = field.isAnnotationPresent(SensitiveRedact.class)
            ? getMaskedValue(field.get(this)) : field.get(this);

        toString.append(name).append("=").append(value).append(", ");
      }
      catch (IllegalArgumentException | IllegalAccessException e) {
      }
    }
    toString = new StringBuilder(toString.toString().replaceAll(",\\s*$", ""));
    toString.append("]");

    return toString.toString();
  }

  /**
   *
   * @param input
   * @return
   */
  private String getMaskedValue(Object input) {

    char[] value = input.toString().toCharArray();
    StringBuilder output = new StringBuilder();
    for (int i = 0; i < value.length; i++) {
      output.append("+");
    }
    return output.toString();
  }
}
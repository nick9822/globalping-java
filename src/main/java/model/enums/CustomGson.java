package model.enums;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Common Gson instance used all over the project as a singleton Gson object.
 */
public enum CustomGson {
  INSTANCE;

  final Gson value = new GsonBuilder().create();

  public static Gson get() {
    return CustomGson.INSTANCE.value;
  }
}

package model.enums;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public enum CustomGson {
  INSTANCE;

  final Gson v = new GsonBuilder().create();

  public static Gson get() {
    return CustomGson.INSTANCE.v;
  }
}

package com.nzleyuan.persistance.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonUtils {
  private static final ObjectMapper MAPPER = new ObjectMapper();
  public static Object convertJson2Object(String jsonStr, Class<?> cls) throws IOException {
    return MAPPER.readValue(jsonStr, cls);
  }
}

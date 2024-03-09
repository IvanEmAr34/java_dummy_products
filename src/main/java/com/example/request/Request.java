package com.example.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class Request {

  public static HttpURLConnection getConnectionToUrl(String url)
    throws IOException {
    URL urlPeticion = new URL(url);
    return (HttpURLConnection) urlPeticion.openConnection();
  }

  public static JSONObject get(String url) throws IOException, JSONException {
    System.out.println("get from: " + url);

    HttpURLConnection conexion = getConnectionToUrl(url);
    StringBuilder resultadoBuilder = new StringBuilder();
    String resultado;
    conexion.setRequestMethod("GET");

    BufferedReader bufferedReader = new BufferedReader(
      new InputStreamReader(conexion.getInputStream())
    );
    String linea;

    while ((linea = bufferedReader.readLine()) != null) {
      resultadoBuilder.append(linea);
    }

    bufferedReader.close();
    resultado = resultadoBuilder.toString();
    resultado = "{\"data\":" + resultado + "}";
    System.out.println(resultado);
    System.out.println("---");
    JSONObject jsonObject = new JSONObject(resultado);
    return jsonObject;
    // return new JSONObject(resultado);
  }
}

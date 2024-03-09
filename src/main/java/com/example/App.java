package com.example;

import com.example.request.*;
import java.util.Iterator;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class App {

  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    Boolean keepWizard = true;

    do {
      System.out.println("");

      System.out.println("Selecciona una opcion");
      System.out.println("0) EXIT");
      System.out.println("1) leer todos los productos");
      System.out.println("2) traer un producto");
      String optionSelected = userInput.nextLine();

      switch (optionSelected) {
        case "0":
          System.out.println("BYE BYE");
          keepWizard = false;
          break;
        case "1":
          System.out.println("All products");
          getAllProducts();
          break;
        case "2":
          System.out.println("Ingresa el ID de un producto");
          System.out.println("0) EXIT");
          optionSelected = userInput.nextLine();
          int value = Integer.valueOf(optionSelected.toString());
          if (value == 0) {
            System.out.println("BYE BYE");
            keepWizard = false;
            break;
          }
          getProduct(optionSelected);
          break;
        default:
          break;
      }
    } while (keepWizard);
  }

  private static void getProduct(String productId) {
    String url = "https://fakestoreapi.com/products/" + productId;
    try {
      JSONObject respuesta = Request.get(url);
      JSONObject product = respuesta.getJSONObject("data");

      Iterator<String> keys = product.keys();

      while (keys.hasNext()) {
        String key = keys.next();
        System.out.println(key + ": " + product.get(key).toString());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void getAllProducts() {
    String url = "https://fakestoreapi.com/products";
    try {
      JSONObject respuesta = Request.get(url);
      JSONArray allProducts = respuesta.getJSONArray("data");

      int productsCount = allProducts.length();

      for (int idx = 0; idx < productsCount; idx++) {
        JSONObject product = allProducts.getJSONObject(idx);
        System.out.println(
          "(" + product.getString("id") + ") " + product.getString("title")
        );
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

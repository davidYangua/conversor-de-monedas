package com.alura.conversor;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class ConversorDeMonedas {

    public static Moneda obtenerDatos(String api) {

        Moneda moneda = null;
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(api))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            moneda = gson.fromJson(response.body(), Moneda.class);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return moneda;
    }

    public static String convertir(String tipoMoneda, String monedaCambio, double valor) {
        double resultado = 0;
        
        String api = "https://v6.exchangerate-api.com/v6/6f66507ef6e381d8853ec464/latest/" + tipoMoneda;
        Moneda moneda = obtenerDatos(api);
        double cambio = moneda.getConversion_rates().get(monedaCambio);
        resultado = valor*cambio;

        return "\nEl valor de " + valor + " [" + tipoMoneda + "] corresponde al valor final de =>>> " + resultado + " [" + monedaCambio + "]\n";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        String resultado = "";
        double valor = 0;

        while (opcion != 7) {

            String str = """
                       **************************************************************************
                         Sea bienvenido/a al Conversor de Moneda 
                         1) Dólar =>> Peso argentino
                         2) Peso argentino =>> Dólar
                         3) Dólar =>> Real brasileño
                         4) Real brasileño =>> Dólar
                         5) Dólar =>> Peso colombiano
                         6) Peso colombiano =>> Dólar
                         7) Salir
                         
                         Elija la opción válida: 
                       ***************************************************************************
                       """;
            System.out.print(str);
            opcion = sc.nextInt();

            if (opcion != 7) {
                System.out.print("\nIngrese el valor que desea convertir: ");
                valor = sc.nextDouble();
            }

            switch (opcion) {
                case 1:
                    resultado = ConversorDeMonedas.convertir(TipoMoneda.USD.toString(),TipoMoneda.ARS.toString() , valor);
                    break;
                case 2:
                    resultado = ConversorDeMonedas.convertir(TipoMoneda.ARS.toString(), TipoMoneda.USD.toString(), valor);
                    break;
                case 3:
                    resultado = ConversorDeMonedas.convertir(TipoMoneda.USD.toString(), TipoMoneda.ARS.toString()  ,valor);
                    break;
                case 4:
                    resultado = ConversorDeMonedas.convertir(TipoMoneda.BRL.toString(), TipoMoneda.USD.toString(),  valor);
                    break;
                case 5:
                    resultado = ConversorDeMonedas.convertir(TipoMoneda.USD.toString(), TipoMoneda.COP.toString(), valor);
                    break;
                case 6:
                    resultado = ConversorDeMonedas.convertir(TipoMoneda.COP.toString(), TipoMoneda.USD.toString(), valor);
                    break;
                case 7:
                    System.out.println("\nSalió del sistema");
                    break;
                default:
                    System.out.println("Escoja una opción válida");
                    break;
            }

            System.out.println(resultado);

        }

    }
}

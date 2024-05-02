import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://v6.exchangerate-api.com/v6/54fed40d6d643c36836a2910/latest/USD");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode() ;
            if (responseCode != 200){
                throw new RuntimeException("Ocurrio un error:" + responseCode);
            } else {
                StringBuilder informationString = new StringBuilder() ;
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                JSONObject jsonObject = new JSONObject(informationString.toString() ) ;

                JSONObject jsonConversionRates = jsonObject.getJSONObject("conversion_rates");

                System.out.println( jsonConversionRates.getDouble("USD"));
                double valorDolar = jsonConversionRates.getDouble("USD");
                System.out.println( jsonConversionRates.getDouble("BRL"));
                double valorBrasil = jsonConversionRates.getDouble("BRL");
                System.out.println( jsonConversionRates.getDouble("ARS"));
                double valorArgentino = jsonConversionRates.getDouble("ARS");
                System.out.println( jsonConversionRates.getDouble("COP"));
                double valorColombia = jsonConversionRates.getDouble("COP");

                boolean bandera = true;
                while(bandera)
                {
                    System.out.println("CONVERSOR DE MONEDAS");
                    System.out.println("""
                    ****************************************
                    Sea Bienvenido/a al conversor de monedas
                    1)Dolar =>> Peso Argentino
                    2)Peso Argentino =>> Dolar
                    3)Dolar =>> Real Brasileño
                    4)Real Brasileño =>> Dolar
                    5)Dolar =>> Peso colombiano
                    6)Peso colombiano =>> Dolar
                    7)Salir
                    *********************************************
                    """);
                    System.out.print("INGRESE UNA OPCION VALIDA: ");
                    Scanner leer = new Scanner(System.in);
                    char opcion = leer.next() .charAt(0);
                    switch(opcion){
                        case '1'-> convertir(valorArgentino, "Pesos Argentinos", "Dolar");
                        case '2' -> convertir(valorDolar, "Dolares","Pesos Argentinos" );
                        case '3' -> convertir(valorBrasil , "Reales Brasileños","Dolar");
                        case '4'-> convertir(valorDolar, "Dolares","Reales Brasileños");
                        case '5' -> convertir(valorColombia , "Pesos Colombianos","Dolares");
                        case '6' -> convertir(valorDolar , "Dolares","Pesos Colombianos");
                        case '7' ->{
                            System.out.println("CERRANDO PROGRAMA");
                            bandera = false;
                        }
                        default -> System.out.println("OPCION INCORRECTA");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static void convertir(double valor, String pais, String paisDesde){
        Scanner leer = new Scanner(System.in);
        System.out.printf("Ingrese la cantidad de %s :", paisDesde) ;
        double cantidadMoneda = leer.nextDouble() ;
        double dolares = cantidadMoneda / valor;
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("el valor de  " + dolares + " corresponde al valor final de =>>> " + pais);
        System.out.println("-------------------------------------------------------------------------");

    }
}


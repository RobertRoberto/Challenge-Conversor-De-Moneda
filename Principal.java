import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        boolean continuar = true;

        while (continuar) {
            System.out.println("Divisas de ayuda:\n" +
                    "ARS - Peso argentino\n" +
                    "BOB - Boliviano boliviano\n" +
                    "BRL - Real brasileño\n" +
                    "CLP - Peso chileno\n" +
                    "COP - Peso colombiano\n" +
                    "USD - Dólar estadounidense\n");
            System.out.println("Ingrese el código de la divisa base: ");
            String codigoBase = lectura.nextLine().toUpperCase();

            System.out.println("Ingrese la cantidad en " + codigoBase + " que desea convertir: ");
            double cantidad = Double.parseDouble(lectura.nextLine());

            System.out.println("Ingrese el código de la divisa a la que desea convertir: ");
            String codigoDestino = lectura.nextLine().toUpperCase();

            try {
                Moneda moneda = consulta.buscarMoneda(codigoBase);
                Double tasaConversion = moneda.getConversion_rates().get(codigoDestino);

                if (tasaConversion != null) {
                    double montoConvertido = cantidad * tasaConversion;
                    System.out.println(cantidad + " " + codigoBase + " equivale a " + montoConvertido + " " + codigoDestino);
                } else {
                    System.out.println("\nCódigo de moneda no encontrado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nFormato de número no válido: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("\nFinalizando la aplicación");
            }

            System.out.println("\n¿Desea realizar otra conversión? [1 = SI, Cualquier otro = NO]: ");
            int respuesta = lectura.nextInt();
            lectura.nextLine();
            if (respuesta != 1) {
                continuar = false;
            }
            System.out.println("\nPresione ENTER para avanzar");
            lectura.nextLine();
        }
    }
}

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package engtelecom.std;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        // lê o primeiro argumento como inteiro
        int arg = Integer.parseInt(args[0]);
        // int arg = 1;

        // verifica se é algum número entre 1 a 5
        switch (arg) {
            case 1:
                List<String> metodosAr = Arrays.asList("ligar", "desligar", "ajustarTemperatura");
                ArCondicionado a = new ArCondicionado(1L, "Ar condicionado", "desligado", 0, metodosAr);
                a.registraDispositivo();
                break;
            case 2:
                List<String> metodosSom = Arrays.asList("aumentarVolume", "diminuirVolume", "reproduzir", "ligar",
                        "desligar");
                Som s = new Som(3L, "Sistema de Som", "ligado", 50, metodosSom);
                s.registraDispositivo();
                break;
            case 3:
                List<String> metodosCortina = Arrays.asList("abrir", "fechar", "pausar");
                Cortina c = new Cortina(3L, "Cortina", "fechada", 0, metodosCortina);
                c.registraDispositivo();
                break;
            case 4:
                List<String> metodosLampada = Arrays.asList("ligar", "desligar", "ajustarLuminosidade");
                Lampada d = new Lampada(4L, "Lâmpada", "desligado", 0, metodosLampada);
                d.registraDispositivo();
                break;
            case 5:
                List<String> metodosTelevisao = Arrays.asList("ligar", "desligar", "ajustarVolume", "trocarCanal");
                Televisao e = new Televisao(5L, "Televisão", "ligado", 10, metodosTelevisao);
                e.registraDispositivo();
                break;
            default:
                System.out.println("Número inválido, você deve digitar um número entre 1 e 5.");
        }
    }
}

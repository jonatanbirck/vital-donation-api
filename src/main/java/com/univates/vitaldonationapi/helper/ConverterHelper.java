package com.univates.vitaldonationapi.helper;

import javax.swing.text.MaskFormatter;
import java.util.Objects;

public class ConverterHelper {

    private ConverterHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static double toKilos(int grams) {
        return (double) grams / 1000;
    }

    public static double toMeters(short centimeters) {
        return (double) centimeters / 100;
    }

    public static String maskCPF(String cpf) {
        try {
            cpf = cpf.replaceAll("[^0-9]", "");
            if (!isCPF(cpf)) return null;
            var mask = new MaskFormatter("###.###.###-##");
            mask.setValueContainsLiteralCharacters(false);
            return mask.valueToString(cpf);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Test if is valid CPF
     *
     * @param cpf withoutMask
     * @return if is valid CPF
     */
    public static boolean isCPF(String cpf){
        if (Objects.isNull(cpf)) return false;
        if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") ||
            cpf.equals("33333333333") ||
            cpf.equals("44444444444") ||
            cpf.equals("55555555555") ||
            cpf.equals("66666666666") ||
            cpf.equals("77777777777") ||
            cpf.equals("88888888888") ||
            cpf.equals("99999999999") ||
            cpf.length() != 11)
            return(false);

        char dig10;
        char dig11;
        int sm;
        int i;
        int r;
        int num;
        int peso;

        try {
            // Calculo do primeiro Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else
                dig10 = (char)(r + 48);

            // Calculo do segundo Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else
                dig11 = (char)(r + 48);

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return(true);
            else return(false);
        } catch(Exception e) {
            return(false);
        }
    }

}

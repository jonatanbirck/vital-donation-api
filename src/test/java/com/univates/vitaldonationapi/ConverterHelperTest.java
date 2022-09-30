package com.univates.vitaldonationapi;

import com.univates.vitaldonationapi.helper.ConverterHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ConverterHelperTest {

    @Test
    void testMaskCPF() {
        String cpf1 = "02572694018";
        String cpf2 = "025.726.940-18";
        String cpf3 = "025.72694018";
        String cpf4 = "asdads";
        String cpf5 = "02a7a69a018";
        String cpf6 = null;
        String cpf7 = "";
        String cpf8 = "000.000.000-00";
        String cpf9 = "11111111111";

        assertEquals("025.726.940-18", ConverterHelper.maskCPF(cpf1));
        assertEquals("025.726.940-18", ConverterHelper.maskCPF(cpf2));
        assertEquals("025.726.940-18", ConverterHelper.maskCPF(cpf3));
        assertNull(ConverterHelper.maskCPF(cpf4));
        assertNull(ConverterHelper.maskCPF(cpf5));
        assertNull(ConverterHelper.maskCPF(cpf6));
        assertNull(ConverterHelper.maskCPF(cpf7));
        assertNull(ConverterHelper.maskCPF(cpf8));
        assertNull(ConverterHelper.maskCPF(cpf9));
    }
}

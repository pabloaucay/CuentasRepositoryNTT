package com.aucsoft.serviceclientes;

import com.aucsoft.serviceclientes.service.EncryptServiceImplement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestEncrypt {
    @Autowired
    EncryptServiceImplement encryptServiceImplement= new EncryptServiceImplement();
    @Test
    public void TestEncrypDecryptMethod(){
        String value="Pablo";
        String encriptedValue= encryptServiceImplement.encryptValue(value);
        String decriptedValue= encryptServiceImplement.decryptValue(encriptedValue);
        Assertions.assertThat(value==decriptedValue);

    }

}

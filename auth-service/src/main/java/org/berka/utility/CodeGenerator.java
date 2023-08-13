package org.berka.utility;

import java.util.UUID;

public class CodeGenerator {

    public static String generateCode() {
        String code = UUID.randomUUID().toString();
        System.out.println(code);
        String[] data = code.split("-");
        StringBuilder newCode = new StringBuilder();
        for (String datum : data) {
            char c = datum.charAt(0);
            newCode.append(c);
        }
        return newCode.toString();
    }


}

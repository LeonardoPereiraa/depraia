package uff.dac.depraia.apidepraia.util;

import java.util.HashMap;
import java.util.Map;

final String ERRO = "Error! ";

public class Mensagem {

    public static Map<String, Boolean> sucesso(String name, Integer action) {
        Map<String, Boolean> response = new HashMap<>();
        String aux = "";
        switch (action) {
            case 1:
                aux = "adicionado ao";
                break;
            case 2:
                aux = "atualizado no";
                break;
            case 3:
                aux = "deletado do";
                break;
            default:
                aux = "";
                break;
        }        
        response.put("Sucesso! " + name + " foi " + aux + " banco de dados", Boolean.TRUE);
        return response;
    }
    
    public static Map<String, Boolean> error(String name, Integer action) {
        Map<String, Boolean> response = new HashMap<>();
        String aux = "";
        switch (action) {
            case 1:
                aux = "adicionado ao";
                break;
            case 2:
                aux = "atualizado no";
                break;
            case 3:
                aux = "deletado do";
                break;
            case 4:
                response.put(ERRO + name + " não foi encontrado", Boolean.FALSE);
                return response;
            default:
                response.put(ERRO + name, Boolean.FALSE);
                return response;
        }
        response.put(ERRO + name + " não foi " + aux + " banco de dados", Boolean.FALSE);
        return response;
    }
}

package expresiones;

public class AnalizadorExpresion {

    // Atributos
    private String detalle;

    // Getters
    public String getDetalle() {
        return detalle;
    }

    // Operaciones
    public boolean validar(String expresion) {
        Pila pila = new Pila();
        StringBuilder pasos = new StringBuilder();

        boolean esperaOperando = true;

        if (expresion == null || expresion.trim().isEmpty()) {
            detalle = "La expresión está vacía.";
            return false;
        }

        for (int i = 0; i < expresion.length(); i++) {
            char caracter = expresion.charAt(i);

            if (caracter == ' ') {
                continue;
            }

            if (Character.isDigit(caracter) || Character.isLetter(caracter)) {
                int inicio = i;

                while (i < expresion.length()
                        && (Character.isDigit(expresion.charAt(i)) || Character.isLetter(expresion.charAt(i)))) {
                    i++;
                }

                String token = expresion.substring(inicio, i);
                i--;

                if (!esperaOperando) {
                    pasos.append("Error: apareció el operando ").append(token)
                            .append(" cuando se esperaba un operador.\n");
                    detalle = pasos.toString();
                    return false;
                }

                pasos.append("Token ").append(token).append(": operando válido.\n");
                esperaOperando = false;

            } else if (caracter == '(') {
                if (!esperaOperando) {
                    pasos.append("Error: falta un operador antes del paréntesis de apertura.\n");
                    detalle = pasos.toString();
                    return false;
                }

                pila.push(String.valueOf(caracter));
                pasos.append("Token (: push en la pila.\n");
                esperaOperando = true;

            } else if (caracter == ')') {
                if (pila.estaVacia()) {
                    pasos.append("Error: apareció un paréntesis de cierre sin apertura.\n");
                    detalle = pasos.toString();
                    return false;
                }

                if (esperaOperando) {
                    pasos.append("Error: el paréntesis de cierre aparece después de un operador o sin contenido válido.\n");
                    detalle = pasos.toString();
                    return false;
                }

                pila.pop();
                pasos.append("Token ): pop de la pila, se cerró un paréntesis.\n");
                esperaOperando = false;

            } else if (esOperador(caracter)) {
                if (esperaOperando) {
                    pasos.append("Error: apareció el operador ").append(caracter)
                            .append(" cuando se esperaba un operando.\n");
                    detalle = pasos.toString();
                    return false;
                }

                pasos.append("Token ").append(caracter).append(": operador válido.\n");
                esperaOperando = true;

            } else {
                pasos.append("Error: el símbolo ").append(caracter).append(" no es válido.\n");
                detalle = pasos.toString();
                return false;
            }
        }

        if (esperaOperando) {
            pasos.append("Error: la expresión termina esperando un operando.\n");
            detalle = pasos.toString();
            return false;
        }

        if (!pila.estaVacia()) {
            pasos.append("Error: quedaron paréntesis de apertura sin cerrar.\n");
            detalle = pasos.toString();
            return false;
        }

        pasos.append("La pila quedó vacía y la expresión mantiene un orden correcto.\n");
        detalle = pasos.toString();
        return true;
    }

    private boolean esOperador(char caracter) {
        return caracter == '+' || caracter == '-' || caracter == '*' || caracter == '/' || caracter == '^';
    }
}
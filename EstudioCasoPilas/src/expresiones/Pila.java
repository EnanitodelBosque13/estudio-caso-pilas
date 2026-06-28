package expresiones;

public class Pila {

    // Atributos
    private Nodo cima;

    // Constructor
    public Pila() {
        cima = null;
    }

    // Operaciones
    public boolean estaVacia() {
        return cima == null;
    }

    public void push(String token) {
        Nodo nuevo = new Nodo(token);

        if (estaVacia()) {
            cima = nuevo;
        } else {
            nuevo.setSiguiente(cima);
            cima = nuevo;
        }
    }

    public String pop() {
        if (estaVacia()) {
            return null;
        }

        String token = cima.getToken();
        cima = cima.getSiguiente();
        return token;
    }

    public String peek() {
        if (estaVacia()) {
            return null;
        }

        return cima.getToken();
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La pila está vacía.");
            return;
        }

        Nodo temp = cima;

        while (temp != null) {
            System.out.println(temp.getToken());
            temp = temp.getSiguiente();
        }
    }
}
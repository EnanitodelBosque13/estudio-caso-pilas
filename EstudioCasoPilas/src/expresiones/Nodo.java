package expresiones;

public class Nodo {

    // Atributos
    private String token;
    private Nodo siguiente;

    // Constructor
    public Nodo(String token) {
        this.token = token;
        siguiente = null;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    // Setters
    public void setToken(String token) {
        this.token = token;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}
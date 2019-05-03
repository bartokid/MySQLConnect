public class Usuario {

    private String nombre;
    private String contrasenya;
    private int user_ID;
    private boolean varita;
    private int vida;
    private int dinero;

    public Usuario(){

    }

    public Usuario(String nombre, String contrasenya, int user_ID, boolean varita, int vida, int dinero) {
        this.nombre = nombre;
        this.contrasenya = contrasenya;
        this.user_ID = user_ID;
        this.varita = varita;
        this.vida = vida;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
    }

    public boolean isVarita() {
        return varita;
    }

    public void setVarita(boolean varita) {
        this.varita = varita;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
}

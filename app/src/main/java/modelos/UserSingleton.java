package modelos;

public class UserSingleton {

    private static int idUsuario;

    private static String nombre_carrera;

    private static String nombres;

    private static String apellidos;

    private UserSingleton() {}

    public static UserSingleton getInstance() {
        return UserSingletonHolder.INSTANCE;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int id) {
        idUsuario = id;
    }

    public static String getNombre_carrera() {
        return nombre_carrera;
    }

    public static String getNombres() {
        return nombres;
    }

    public static void setNombres(String nombres) {
        UserSingleton.nombres = nombres;
    }

    public static String getApellidos() {
        return apellidos;
    }

    public static void setApellidos(String apellidos) {
        UserSingleton.apellidos = apellidos;
    }

    public static void setNombre_carrera(String nombre_carrera) {
        UserSingleton.nombre_carrera = nombre_carrera;
    }

    private static class UserSingletonHolder {
        private static final UserSingleton INSTANCE = new UserSingleton();
    }
}
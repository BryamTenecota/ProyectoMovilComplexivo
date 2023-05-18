package modelos;

public class UserSingleton {

    private static int idUsuario;

    private static String nombre_carrera;

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

    public static void setNombre_carrera(String nombre_carrera) {
        UserSingleton.nombre_carrera = nombre_carrera;
    }

    private static class UserSingletonHolder {
        private static final UserSingleton INSTANCE = new UserSingleton();
    }
}
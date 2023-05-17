package modelos;

public class UserSingleton {

    private static int idUsuario;

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

    private static class UserSingletonHolder {
        private static final UserSingleton INSTANCE = new UserSingleton();
    }
}

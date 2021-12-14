import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import game.model.*;

public class Main {
    // @TODO: Sustituya xxxx por los parámetros de su conexión

    private static final String DB_SERVER = "localhost";

    private static final int DB_PORT = 3306;

    private static final String DB_NAME = "dragonesycavernas";

    private static final String DB_USER = "root";

    private static final String DB_PASS = "";

    private static Connection conn;



    public static void main(String[] args) throws Exception {

        Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://" + DB_SERVER + ":" + DB_PORT + "/" + DB_NAME;
        conn = DriverManager.getConnection(url, DB_USER, DB_PASS);

        // @TODO pruebe sus funciones

        nuevo_dragon("Viseryon", 1, 1);

        squad_derrota_dragones("Hooligans de la sangre").forEach(System.out::println);

        System.out.println("------------------------------------");

        mostrar_hachas("Forja del enano risueño").forEach(System.out::println);

        System.out.println("------------------------------------");

        System.out.println(espada_porta_guerrero("Stanto"));

        try {
            if(conn != null)
                conn.close();
        }
        catch(SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // @TODO resuelva las siguientes funciones...

    public static void nuevo_dragon(String nombre, int vida, int recompensa){
        // @TODO: complete este método para que cree un nuevo dragón en la base de datos
        try {
            String sql = "INSERT INTO dragon(nombre_dr,vida,recompensa) VALUES(?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nombre);
            pstmt.setInt(2, vida);
            pstmt.setInt(3, recompensa);
            pstmt.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static List<Dragon> squad_derrota_dragones(String squad){
        // @TODO: complete este método para que devuelva una lista de los dragones derrotados por el squad
        // Tenga en cuenta que la consulta a la base de datos le devolverá un ResultSet sobre el que deberá
        // ir iterando y creando un objeto dragon para cada uno de los dragones, y añadirlos a la lista
        List<Dragon> lista = new ArrayList<>();
        try {
            PreparedStatement statement2 = conn.prepareStatement("SELECT * FROM dragon INNER JOIN lucha ON dragon.nombre_dr = lucha.nombre_dr WHERE lucha.nombre_esc = ?");
            statement2.setString(1, squad);
            ResultSet rsDragon = statement2.executeQuery();
            while (rsDragon.next()) {
                lista.add(new Dragon(rsDragon.getString("nombre_dr"), rsDragon.getInt("vida"), rsDragon.getInt("recompensa")));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public static List<Hacha> mostrar_hachas(String nombre_forja){
        // @TODO: complete este método para que muestre por pantalla las hachas que pueden forjarse en "nombre_forja"
        // Tenga en cuenta que la consulta a la base de datos le devolverá un ResultSet sobre el que deberá
        // ir iterando y creando un objeto con cada hacha disponible en esa forja, y añadirlos a la lista
        List<Hacha> lista = new ArrayList<>();

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM hacha INNER JOIN mejora_hacha ON hacha.nombre_h = mejora_hacha.nombre_h WHERE mejora_hacha.nombre_f = ?");
            statement.setString(1, nombre_forja);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                lista.add(new Hacha(resultSet.getString("nombre_h"), resultSet.getInt("peso"), resultSet.getInt("daño")));
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return lista;
    }

    public static String espada_porta_guerrero(String nombre_guerrero){
        // @TODO: complete este método para que devuelva el nombre de la espada que porta el guerrero "nombre_guerrero"
        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM tiene_espada INNER JOIN guerrero ON tiene_espada.id_g = guerrero.id_g WHERE guerrero.nombre_p = ?");
            statement.setString(1, nombre_guerrero);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getString("nombre_e");
            }
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return "espadón";
    }

}

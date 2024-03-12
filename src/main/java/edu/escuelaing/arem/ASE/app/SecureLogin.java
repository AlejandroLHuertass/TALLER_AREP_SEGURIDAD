package edu.escuelaing.arem.ASE.app;

import java.util.ArrayList;

import java.util.List;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;


public class SecureLogin {
    private static List<String> hashList = new ArrayList<>();

    public static void main( String[] args ){
        
        port(37000);
        get("/hello",(req,res)  -> "Hello world!");
    }

    public static void mains( String[] args )
    {
        secure("certificados/ecikeystore.p12", "123456", null, null);
        port(getPort());
        
        get("/hashes", (req, res) -> {
            res.type("application/json");
            return hashList;
        });

        // Endpoint POST para agregar un nuevo hash
        post("/hashes", (req, res) -> {
            String hash = req.body();
            hashList.add(hash);
            return "Hash agregado exitosamente.";
        });

        // Endpoint GET para obtener el número total de hashes
        get("/hashes/count", (req, res) -> {
            res.type("application/json");
            return "{\"count\": " + hashList.size() + "}";
        });

        // Endpoint DELETE para eliminar todos los hashes
        delete("/hashes", (req, res) -> {
            hashList.clear();
            return "Todos los hashes han sido eliminados.";
        });
    }
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}

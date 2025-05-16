package Clase;

import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;

public class Clientes {
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String email;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public static void guardarClientes(String cedula, String nombre, String apellido,
                                       String direccion, String telefono, String email) {
        Map<String, Clientes> clientesMap = listarClientes(); 

        Clientes nuevoCliente = new Clientes();
        nuevoCliente.setCedula(cedula);
        nuevoCliente.setNombre(nombre);
        nuevoCliente.setApellido(apellido);
        nuevoCliente.setDireccion(direccion);
        nuevoCliente.setTelefono(telefono);
        nuevoCliente.setEmail(email);

        clientesMap.put(cedula, nuevoCliente); 

        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter("Clientes.json")) {
            gson.toJson(clientesMap, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Clientes> listarClientes() {
        Gson gson = new Gson();
        Map<String, Clientes> clientesMap = new HashMap<>();
        try (FileReader reader = new FileReader("Clientes.json")) {
            Type type = new TypeToken<Map<String, Clientes>>() {}.getType();
            clientesMap = gson.fromJson(reader, type);
            if(clientesMap == null){
                clientesMap = new HashMap<>();
            }
        } catch (IOException e) {
            clientesMap = new HashMap<>();

        }
        return clientesMap;
    }

    public static void borrarArchivoClientes() {
    File archivo = new File("Clientes.json");
    if (archivo.exists()) {
        archivo.delete();
    }
}

}

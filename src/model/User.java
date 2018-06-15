package model;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {

    //El ID del usuario. Este id se obtiene de la direccion de correo; ejm. en richard@gmail.com el ID es richard.
    //Ya que este ID es único para la dirección de correo, no habran conflictos.
    @Persistent
    @PrimaryKey
    private String id;

    //Nombre del Usuario
    @Persistent
    private String name;

    //Dirección de la imagen de perfil del Usuario
    @Persistent
    private String imgUrl;

    //Email del usuario
    @Persistent
    private String email;

    //Rol del Usuario
    @Persistent
    private Role role;

    //Constructor
    public User(String id, String name, String imgUrl, String email ,Role role){
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.email = email;
        this.role = role;
    }


    //Getters y Setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    //To String
    @Override
    public String toString() {
        return "User name: " + name + "\nUser role: " + role + "\n";
    }
}

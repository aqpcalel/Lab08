package model;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@PersistenceCapable
public class Role {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String name;

    @Persistent
    private String createDate;

    @Persistent
    private boolean status;

    @Persistent
    private List<Access> accessesList;

    public Role(String name, boolean status) {
        this.name = name;
        this.status = status;
        //this.accessesList = accessesList;
        DateFormat df = new SimpleDateFormat("HH:mm:ss dd/MM/yy");
        createDate = df.format(Calendar.getInstance().getTime());
    }

    //Getters y Setters
    public String getKey() {
        return KeyFactory.keyToString(key);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreateDate() {
        return createDate;
    }
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<Access> getAccessesList() {
        return accessesList;
    }
    public void setAccessesList(List<Access> accessesList) {
        this.accessesList = accessesList;
    }


    @Override
    public String toString() {
        return "Role name: " + name +"\n";
    }

}

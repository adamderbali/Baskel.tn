package edu.baskel.entities; 
import java.sql.Date;
/**
 *
 * @author gammar
 */
public class Forum {
private int id_f;
private int id_u;
private String text;
private String date_f;
private int valid_f;
private String image_uf;

    public Forum() {
    }

    public Forum(int id_f, int id_u, String text, String date_f, int valid_f) {
        this.id_f = id_f;
        this.id_u = id_u;
        this.text = text;
        this.date_f = date_f;
        this.valid_f = valid_f;
    }

    public Forum(int id_f, int id_u, String text, String date_f) {
        this.id_f = id_f;
        this.id_u = id_u;
        this.text = text;
        this.date_f = date_f;
    }

    public Forum(String text, String date_f) {
        this.text = text;
        this.date_f = date_f;
    }
    
    

    public int getId_f() {
        return id_f;
    }

    public void setId_f(int id_f) {
        this.id_f = id_f;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate_f() {
        return date_f;
    }

    public void setDate_f(String date_f) {
        this.date_f = date_f;
    }

    public int getValid_f() {
        return valid_f;
    }

    public void setValid_f(int valid_f) {
        this.valid_f = valid_f;
    }

    public String getImage_uf() {
        return image_uf;
    }

    public void setImage_uf(String image_uf) {
        this.image_uf = image_uf;
    }

    public Forum(int id_f, int id_u, String text, String date_f, String image_uf) {
        this.id_f = id_f;
        this.id_u = id_u;
        this.text = text;
        this.date_f = date_f;
        this.image_uf = image_uf;
    }

    @Override
    public String toString() {
        return "Forum1{" + "id_f=" + id_f + ", id_u=" + id_u + ", text=" + text + ", date_f=" + date_f + ", valid_f=" + valid_f + ", image_uf=" + image_uf + '}';
    }

    


}

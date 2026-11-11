package entities;

import jakarta.persistence.Entity;
import java.io.Serializable;

@Entity
public class RectElement extends Element implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int hight;
    private int width;

    public RectElement() {
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHight() {
        return hight;
    }

    public void setHight(int hight) {
        this.hight = hight;
    }

    @Override
    public String toString() {
        return super.toString() + " WIDTH=" + width;
    }
    
}

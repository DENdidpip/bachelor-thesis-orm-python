package entities;

import jakarta.persistence.Entity;
import java.io.Serializable;

@Entity
public class TextElement extends Element implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String text;
    private int fontSize;
    private String font;
    
    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
 
    @Override
    public String toString() {
        return super.toString() + " TEXT=" + text;
    }

}

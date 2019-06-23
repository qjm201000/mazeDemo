import java.awt.*;

public enum ColorNumber {
    RED_NULBER(8, new Color(255, 0,0)),
    BLACK_NUMBER(1, new Color(0, 0,0)),
    WHITE_NULBER(0, new Color(255, 255,255));

    private int code;
    private Color color;
    ColorNumber(int code,Color color){
        this.code = code;
        this.color = color;
    }

    public int getCode() {
        return code;
    }

    public Color getColor() {
        return color;
    }
}

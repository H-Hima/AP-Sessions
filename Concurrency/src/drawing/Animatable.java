package drawing;

import java.io.Serializable;

public interface Animatable extends Serializable{
    public static final long serialVersionUID=10L;
    public void step();
}

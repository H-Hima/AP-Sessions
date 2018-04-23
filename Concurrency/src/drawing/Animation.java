package drawing;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public abstract class Animation implements Animatable, Serializable {

    boolean animating=true;
    Date nextAnimation=new Date();
    int stepDelay;

    public static final long serialVersionUID=10L;

    public boolean getAnimating() {
        return animating;
    }

    public void setAnimating(boolean animating) {
        this.animating = animating;
    }

    public int getStepDelay() {
        return stepDelay;
    }

    public void setStepDelay(int stepDelay) {
        this.stepDelay = stepDelay;
    }

    public Animation(int stepDelay) {
        this.stepDelay=stepDelay;
        nextAnimation=new Date();
        animating=true;
    }

    public void animate() {
        if(animating) {
            if (nextAnimation.before(new Date())) {
                step();
                nextAnimation.setTime(new Date().getTime() + stepDelay);
            }
        }
    }
}

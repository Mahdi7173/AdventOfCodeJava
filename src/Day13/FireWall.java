package Day13;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class FireWall {

    private boolean forward;
    private boolean backward;
    private int firePosition;
    private List<Integer> range;

    public FireWall(int rangeSize) {
        this.forward = true;
        this.backward = false;
        this.firePosition = 0;
        this.range = Arrays.asList(new Integer[rangeSize]);
        this.range.set(firePosition, 1); // Can I do this in another way?!

    }

    private void findDestPosition() {
        //ListIterator<Integer> litr = this.range.listIterator();
        if (this.forward) {
            //if (litr.hasNext()) {
            if (firePosition < this.range.size() - 1) {
                ++firePosition;
            } else {
                this.backward = true;
                this.forward = false;
                --firePosition;
            }
        } else if (this.backward) {
            //if (litr.hasPrevious()) {
            if (firePosition > 0) {
                --firePosition;
            } else {
                this.forward = true;
                this.backward = false;
                ++firePosition;
            }
        }
    }

    public void moveToPosition() {
        this.range.set(this.firePosition, 0);
        findDestPosition();
        this.range.set(this.firePosition, 1);
    }

    @Override
    public String toString() {
        return "range: " + this.range + ", forward: " + forward + ", backward: " + backward + ", firePosition: " + firePosition;
    }


    public boolean isForward() {
        return forward;
    }

    public void setForward(boolean forward) {
        this.forward = forward;
    }

    public boolean isBackward() {
        return backward;
    }

    public void setBackward(boolean backward) {
        this.backward = backward;
    }

    public int getFirePosition() {
        return firePosition;
    }

    public void setFirePosition(int firePosition) {
        this.firePosition = firePosition;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

}

package me.namenotfound128.voltura.animation;

public class Animation {
    private float value;
    private long lastTime;
    private float changePerMillisecond;
    private float start;
    private float end;
    boolean increasing;
    private Easing easing;

    public Animation(float duration, float start, float end) {
        this((long) (duration * 1000.0f), start, end, Easing.LINEAR);
    }

    public Animation(float duration, float start, float end, Easing easing) {
        this((long) (duration * 1000.0f), start, end, easing);
    }

    public Animation(long duration, float start, float end) {
        this(duration, start, end, Easing.LINEAR);
    }

    public Animation(long duration, float start, float end, Easing easing) {
        this.value = start;
        this.end = end;
        this.start = start;
        this.increasing = end > start;
        float difference = Math.abs(start - end);
        this.changePerMillisecond = difference / (float) duration;
        this.lastTime = System.currentTimeMillis();
        this.easing = easing;
    }

    public static Animation fromChangePerSecond(float changePerSecond, float start, float end) {
        return Animation.fromChangePerSecond(changePerSecond, start, end, Easing.LINEAR);
    }

    public static Animation fromChangePerSecond(float changePerSecond, float start, float end, Easing easing) {
        return new Animation(Math.abs(start - end) / changePerSecond, start, end, easing);
    }

    public void reset() {
        this.value = this.start;
        this.lastTime = System.currentTimeMillis();
    }

    public float getValue() {
        return this.getEased(this.easing != null ? this.easing : Easing.LINEAR);
    }

    private float loadValue() {
        if (this.value == this.end) {
            return this.value;
        }
        if (this.increasing) {
            if (this.value >= this.end) {
                this.value = this.end;
                return this.value;
            }
            this.value += this.changePerMillisecond * (float) (System.currentTimeMillis() - this.lastTime);
            if (this.value > this.end) {
                this.value = this.end;
            }
            this.lastTime = System.currentTimeMillis();
            return this.value;
        }
        if (this.value <= this.end) {
            this.value = this.end;
            return this.value;
        }
        this.value -= this.changePerMillisecond * (float) (System.currentTimeMillis() - this.lastTime);
        if (this.value < this.end) {
            this.value = this.end;
        }
        this.lastTime = System.currentTimeMillis();
        return this.value;
    }

    public boolean isDone() {
        return this.value == this.end;
    }

    public float getEased(Easing easing) {
        if (easing == Easing.LINEAR) {
            return this.loadValue();
        }
        return Animation.map(Easings.getEasingValue(Animation.map(this.loadValue(), this.start, this.end, 0.0f, 1.0f), easing), 0.0f, 1.0f, this.start, this.end);
    }

    public String toString() {
        return "Animation{value=" + this.value + ", lastTime=" + this.lastTime + ", changePerMillisecond=" + this.changePerMillisecond + ", start=" + this.start + ", end=" + this.end + ", increasing=" + this.increasing + ", easing=" + this.easing + "}";
    }

    private static float map(float value, float minInput, float maxInput, float minMapped, float maxMapped) {
        return (value - minInput) / (maxInput - minInput) * (maxMapped - minMapped) + minMapped;
    }
}

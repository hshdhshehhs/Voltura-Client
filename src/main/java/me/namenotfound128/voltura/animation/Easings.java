package me.namenotfound128.voltura.animation;

public class Easings {
    public static float linear(float x) {
        return x;
    }

    public static float easeInSine(float x) {
        return (float) (1.0 - Math.cos((double) x * Math.PI / 2.0));
    }

    public static float easeOutSine(float x) {
        return (float) Math.sin((double) x * Math.PI / 2.0);
    }

    public static float easeInOutSine(float x) {
        return (float) (-(Math.cos(Math.PI * (double) x) - 1.0) / 2.0);
    }

    public static float easeInCubic(float x) {
        return x * x * x;
    }

    public static float easeOutCubic(float x) {
        return (float) (1.0 - Math.pow(1.0f - x, 3.0));
    }

    public static float easeInOutCubic(float x) {
        return (float) ((double) x < 0.5 ? (double) (4.0f * x * x * x) : 1.0 - Math.pow(-2.0f * x + 2.0f, 3.0) / 2.0);
    }

    public static float easeInQuint(float x) {
        return x * x * x * x * x;
    }

    public static float easeOutQuint(float x) {
        return (float) (1.0 - Math.pow(1.0f - x, 5.0));
    }

    public static float easeInOutQuint(float x) {
        return (float) ((double) x < 0.5 ? (double) (16.0f * x * x * x * x * x) : 1.0 - Math.pow(-2.0f * x + 2.0f, 5.0) / 2.0);
    }

    public static float easeInCirc(float x) {
        return (float) (1.0 - Math.sqrt(1.0 - Math.pow(x, 2.0)));
    }

    public static float easeOutCirc(float x) {
        return (float) Math.sqrt(1.0 - Math.pow(x - 1.0f, 2.0));
    }

    public static float easeInOutCirc(float x) {
        return (float) ((double) x < 0.5 ? (1.0 - Math.sqrt(1.0 - Math.pow(2.0f * x, 2.0))) / 2.0 : (Math.sqrt(1.0 - Math.pow(-2.0f * x + 2.0f, 2.0)) + 1.0) / 2.0);
    }

    public static float easeInElastic(float x) {
        if (x <= 0.0f) {
            return 0.0f;
        }
        if (x >= 1.0f) {
            return 1.0f;
        }
        return (float) (-Math.pow(2.0, 10.0f * x - 10.0f) * Math.sin(((double) (x * 10.0f) - 10.75) * 2.0943951023931953));
    }

    public static float easeOutElastic(float x) {
        if (x <= 0.0f) {
            return 0.0f;
        }
        if (x >= 1.0f) {
            return 1.0f;
        }
        return (float) (Math.pow(2.0, -10.0f * x) * Math.sin(((double) (x * 10.0f) - 0.75) * 2.0943951023931953) + 1.0);
    }

    public static float easeInOutElastic(float x) {
        if (x <= 0.0f) {
            return 0.0f;
        }
        if (x >= 1.0f) {
            return 1.0f;
        }
        return (float) ((double) x < 0.5 ? -(Math.pow(2.0, 20.0f * x - 10.0f) * Math.sin(((double) (20.0f * x) - 11.125) * 1.3962634015954636)) / 2.0 : Math.pow(2.0, -20.0f * x + 10.0f) * Math.sin(((double) (20.0f * x) - 11.125) * 1.3962634015954636) / 2.0 + 1.0);
    }

    public static float easeInQuad(float x) {
        return x * x;
    }

    public static float easeOutQuad(float x) {
        return 1.0f - (1.0f - x) * (1.0f - x);
    }

    public static float easeInOutQuad(float x) {
        return (float) ((double) x < 0.5 ? (double) (2.0f * x * x) : 1.0 - Math.pow(-2.0f * x + 2.0f, 2.0) / 2.0);
    }

    public static float easeInQuart(float x) {
        return x * x * x * x;
    }

    public static float easeOutQuart(float x) {
        return (float) (1.0 - Math.pow(1.0f - x, 4.0));
    }

    public static float easeInOutQuart(float x) {
        return (float) ((double) x < 0.5 ? (double) (8.0f * x * x * x * x) : 1.0 - Math.pow(-2.0f * x + 2.0f, 4.0) / 2.0);
    }

    public static float easeInExponential(float x) {
        return (float) (x == 0.0f ? 0.0 : Math.pow(2.0, 10.0f * x - 10.0f));
    }

    public static float easeOutExponential(float x) {
        return (float) (x == 1.0f ? 1.0 : 1.0 - Math.pow(2.0, -10.0f * x));
    }

    public static float easeInOutExponential(float x) {
        return (float) (x == 0.0f ? 0.0 : (x == 1.0f ? 1.0 : ((double) x < 0.5 ? Math.pow(2.0, 20.0f * x - 10.0f) / 2.0 : (2.0 - Math.pow(2.0, -20.0f * x + 10.0f)) / 2.0)));
    }

    public static float easeInBack(float x) {
        float c1 = 1.70158f;
        return (c1 + 1.0f) * x * x * x - c1 * x * x;
    }

    public static float easeOutBack(float x) {
        float c1 = 1.70158f;
        return (float) (1.0 + (double) (c1 + 1.0f) * Math.pow(x - 1.0f, 3.0) + (double) c1 * Math.pow(x - 1.0f, 2.0));
    }

    public static float easeInOutBack(float x) {
        float c1 = 1.70158f;
        float c2 = c1 * 1.525f;
        return (float) ((double) x < 0.5 ? Math.pow(2.0f * x, 2.0) * (double) ((c2 + 1.0f) * 2.0f * x - c2) / 2.0 : (Math.pow(2.0f * x - 2.0f, 2.0) * (double) ((c2 + 1.0f) * (x * 2.0f - 2.0f) + c2) + 2.0) / 2.0);
    }

    public static float easeInBounce(float x) {
        return 1.0f - Easings.easeOutBounce(1.0f - x);
    }

    public static float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;
        if (x < 1.0f / d1) {
            return n1 * x * x;
        }
        if (x < 2.0f / d1) {
            x = (float) ((double) x - 1.5 / (double) d1);
            return (float) ((double) (n1 * x * x) + 0.75);
        }
        if ((double) x < 2.5 / (double) d1) {
            x = (float) ((double) x - 2.25 / (double) d1);
            return (float) ((double) (n1 * x * x) + 0.9375);
        }
        x = (float) ((double) x - 2.625 / (double) d1);
        return (float) ((double) (n1 * x * x) + 0.984375);
    }

    public static float easeInOutBounce(float x) {
        return (double) x < 0.5 ? (1.0f - Easings.easeOutBounce(1.0f - 2.0f * x)) / 2.0f : (1.0f + Easings.easeOutBounce(2.0f * x - 1.0f)) / 2.0f;
    }

    public static float getEasingValue(float x, Easing easing) {
        if (x <= 0.0f) {
            return 0.0f;
        }
        if (x >= 1.0f) {
            return 1.0f;
        }
        switch (easing) {
            case LINEAR: {
                return Easings.linear(x);
            }
            case EASE_IN_SINE: {
                return Easings.easeInSine(x);
            }
            case EASE_OUT_SINE: {
                return Easings.easeOutSine(x);
            }
            case EASE_IN_OUT_SINE: {
                return Easings.easeInOutSine(x);
            }
            case EASE_IN_CUBIC: {
                return Easings.easeInCubic(x);
            }
            case EASE_OUT_CUBIC: {
                return Easings.easeOutCubic(x);
            }
            case EASE_IN_OUT_CUBIC: {
                return Easings.easeInOutCubic(x);
            }
            case EASE_IN_QUINT: {
                return Easings.easeInQuint(x);
            }
            case EASE_OUT_QUINT: {
                return Easings.easeOutQuint(x);
            }
            case EASE_IN_OUT_QUINT: {
                return Easings.easeInOutQuint(x);
            }
            case EASE_IN_CIRC: {
                return Easings.easeInCirc(x);
            }
            case EASE_OUT_CIRC: {
                return Easings.easeOutCirc(x);
            }
            case EASE_IN_OUT_CIRC: {
                return Easings.easeInOutCirc(x);
            }
            case EASE_IN_ELASTIC: {
                return Easings.easeInElastic(x);
            }
            case EASE_OUT_ELASTIC: {
                return Easings.easeOutElastic(x);
            }
            case EASE_IN_OUT_ELASTIC: {
                return Easings.easeInOutElastic(x);
            }
            case EASE_IN_QUAD: {
                return Easings.easeInQuad(x);
            }
            case EASE_OUT_QUAD: {
                return Easings.easeOutQuad(x);
            }
            case EASE_IN_OUT_QUAD: {
                return Easings.easeInOutQuad(x);
            }
            case EASE_IN_QUART: {
                return Easings.easeInQuart(x);
            }
            case EASE_OUT_QUART: {
                return Easings.easeOutQuart(x);
            }
            case EASE_IN_OUT_QUART: {
                return Easings.easeInOutQuart(x);
            }
            case EASE_IN_EXPONENTIAL: {
                return Easings.easeInExponential(x);
            }
            case EASE_OUT_EXPONENTIAL: {
                return Easings.easeOutExponential(x);
            }
            case EASE_IN_OUT_EXPONENTIAL: {
                return Easings.easeInOutExponential(x);
            }
            case EASE_IN_BACK: {
                return Easings.easeInBack(x);
            }
            case EASE_OUT_BACK: {
                return Easings.easeOutBack(x);
            }
            case EASE_IN_OUT_BACK: {
                return Easings.easeInOutBack(x);
            }
            case EASE_IN_BOUNCE: {
                return Easings.easeInBounce(x);
            }
            case EASE_OUT_BOUNCE: {
                return Easings.easeOutBounce(x);
            }
            case EASE_IN_OUT_BOUNCE: {
                return Easings.easeInOutBounce(x);
            }
        }
        System.err.println("Unkown Easing type " + easing.name());
        return x;
    }
}

package main.java.exercise.helper;

public enum ProbeType {
    LINEARES_SONDIEREN,
    QUADRATISCHES_SONDIEREN,
    DOUBLE_HASHING,
    NONE;

    public static ProbeType getProbeTypeByName(String name) {
        if (name.equals("Lineares Sondieren")) {
            return ProbeType.LINEARES_SONDIEREN;
        } else if (name.equals("Quadratisches Sondieren")) {
            return ProbeType.QUADRATISCHES_SONDIEREN;
        } else if (name.equals("Double Hashing")) {
            return ProbeType.DOUBLE_HASHING;
        } else {
            return ProbeType.NONE;
        }
    }
}

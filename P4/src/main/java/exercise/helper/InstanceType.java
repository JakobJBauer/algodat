package main.java.exercise.helper;

public enum InstanceType {
    VERKETTUNG,
    LINEARES_SONDIEREN,
    QUADRATISCHES_SONDIEREN,
    DOUBLE_HASHING,
    VERBESSERUNG_NACH_BRENT,
    INSERT;

    public static InstanceType getInstanceTypeByGroupName(String groupName) {
        if (groupName.equals("Verkettung")) {
            return InstanceType.VERKETTUNG;
        } else if (groupName.equals("Lineares Sondieren")) {
            return InstanceType.LINEARES_SONDIEREN;
        } else if (groupName.equals("Quadratisches Sondieren")) {
            return InstanceType.QUADRATISCHES_SONDIEREN;
        } else if (groupName.equals("Double Hashing")) {
            return InstanceType.DOUBLE_HASHING;
        } else if (groupName.equals("Insert")) {
            return InstanceType.INSERT;
        } else if (groupName.equals("Verbesserung nach Brent")) {
            return InstanceType.VERBESSERUNG_NACH_BRENT;
        }
        return null;
    }
}

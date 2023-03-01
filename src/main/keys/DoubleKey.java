package keys;

public class DoubleKey extends AKey {
    private final String key1;
    private final String key2;

    public DoubleKey(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getKey() {
        return key1 + "\n" + key2;
    }
}

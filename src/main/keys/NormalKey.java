package keys;

public class NormalKey extends AKey {
    private final String key;

    public NormalKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}

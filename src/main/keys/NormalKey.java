package keys;

public class NormalKey implements IKey {
    private String key;

    public NormalKey(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }
}

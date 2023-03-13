package log;

import java.util.Objects;

public class EncryptionLogEventArgs {
    private String fileName;
    private String algo;
    private long startTime;

    public EncryptionLogEventArgs(String fileName, String algo) {
        this.fileName = fileName;
        this.algo = algo;
        this.startTime = 0;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public float getTimeTook() {
        return System.currentTimeMillis() - startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncryptionLogEventArgs)) return false;
        EncryptionLogEventArgs that = (EncryptionLogEventArgs) o;
        return startTime == that.startTime && Objects.equals(fileName, that.fileName) && Objects.equals(algo, that.algo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, algo, startTime);
    }

    @Override
    public String toString() {
        return "EncryptionLogEventArgs {" +
                "fileName='" + fileName + '\'' +
                ", algo='" + algo + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}

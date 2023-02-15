package encryptionDecryption.utils;

public class GeneralMethods {
    /**
     * take the full path and enter only to the name some suffix
     * @param path
     * @param suffix
     * @return
     */
    public static String addSuffixFileName(String path, String suffix){
        String newPath = path.substring(path.lastIndexOf("\\")+1, path.indexOf("."));
        newPath = path.substring(0, path.lastIndexOf("\\")+1) + newPath + "_" + suffix;
        newPath += path.substring(path.indexOf("."));
        return newPath;
    }
}

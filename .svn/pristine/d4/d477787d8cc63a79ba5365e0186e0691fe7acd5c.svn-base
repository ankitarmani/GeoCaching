package models;

import java.io.File;

/**
 * Designed to help to delete files.
 * @author ilyakostrikov
 */
public final class FileDeleter {
    /**
     * Removes a file with the corresponding name.
     *
     * @param file - name of the file to remove.
     */
    public static void deletefile(final String file) {
        File f1 = new File(file);
        boolean success = f1.delete();
        if (!success) {
            System.out.println("Deletion failed.");
            System.exit(0);
        } else {
            System.out.println("File deleted.");
        }
    }

    /**
     * Protects from creating an object of the utility class.
     */
    private FileDeleter() {
    }
}

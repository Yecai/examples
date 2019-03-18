package com.xiaopan;

import jdk.internal.dynalink.beans.StaticClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;

public class ActiveMqTestUtils {

    private static final Log LOGGER = LogFactory.getLog(ActiveMqTestUtils.class);

    /*删除activemq-data文件夹*/
    public static void prepare() {
        LOGGER.info("Refreshing ActiveMq data dictory.");
        File activeMqTempDir = new File("activemq-data");
        deleteDir(activeMqTempDir);
    }

    /*递归删除指定文件夹directory*/
    private static void deleteDir(File directory) {
        if (directory.exists()) {
            String[] childred = directory.list();
            if (childred != null) {
                for (int i = 0; i < childred.length; i++) {
                    deleteDir(new File(directory, childred[i]));
                }
            }
        }
        directory.delete();
    }
}

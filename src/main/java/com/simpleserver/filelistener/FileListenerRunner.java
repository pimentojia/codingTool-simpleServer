package com.simpleserver.filelistener;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @ClassName FileListenerRunner
 * @Author huangyb
 * @Date 2019/6/14 14:26
 * @Version 1.0
 */
@Component
public class FileListenerRunner implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(FileListenerRunner.class);

    @Autowired
    private FileListenerFactory fileListenerFactory;

    @Override
    public void run(String... args) throws Exception {

        // 创建监听者
        System.out.println("fileListenerFactory= " + fileListenerFactory);
        FileAlterationMonitor fileAlterationMonitor = fileListenerFactory.getMonitor(".xml");
        try {
            // do not stop this thread
            fileAlterationMonitor.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//
//    public static void main(String[] args) {
//        FileListenerRunner.run
//    }
}

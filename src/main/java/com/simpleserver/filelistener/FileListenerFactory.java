package com.simpleserver.filelistener;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FileListenerFactory
 * @Author huangyb
 * @Date 2019/6/14 14:19
 * @Version 1.0
 */
@Component
public class FileListenerFactory {

    private Logger logger = LoggerFactory.getLogger(FileListenerFactory.class);

    private String[] monitorDir = {"F:\\codeTool\\simpleServer\\src\\main\\resources\\templates"};

    // 设置轮询间隔
    private final long interval = TimeUnit.SECONDS.toMillis(1);

    public FileAlterationMonitor getMonitor(String fileType) {
        // 创建过滤器
        System.out.println("===in FileAlterationMonitor=== ");
        IOFileFilter directories = FileFilterUtils.and(
                FileFilterUtils.directoryFileFilter(),
                HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(
                FileFilterUtils.fileFileFilter(),
                FileFilterUtils.suffixFileFilter(fileType));
        IOFileFilter filter = FileFilterUtils.or(directories, files);

        // 装配过滤器
//         FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir));
        FileAlterationObserver observer = new FileAlterationObserver(new File(monitorDir[0]), filter);
        System.out.println("observer= " + observer);
        // 向监听者添加监听器，并注入业务服务
        observer.addListener(new FileListener());
        //        observer.addListener(new FileListener());

        // 返回监听者
        return new FileAlterationMonitor(interval, observer);

    }


}

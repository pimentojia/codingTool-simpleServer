package com.simpleserver.filelistener;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @ClassName FileListener 文件变化监听器
 * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下： 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 * @Author huangyb
 * @Date 2019/6/14 14:14
 * @Version 1.0
 */
public class FileListener extends FileAlterationListenerAdaptor {
    private Logger logger = LoggerFactory.getLogger(FileListener.class);

    // 文件创建执行
    @Override
    public void onFileCreate(File file) {
        logger.info("[新建]:" + file.getAbsolutePath());
        System.out.println("[新建]:" + file.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }

    // 文件创建修改
    @Override
    public void onFileChange(File file) {
        logger.info("[修改]:" + file.getAbsolutePath());
        System.out.println("[修改]:" + file.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }

    // 文件创建删除
    @Override
    public void onFileDelete(File file) {
        logger.info("[删除]:" + file.getAbsolutePath());
        System.out.println("[删除]:" + file.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }

    // 目录创建
    @Override
    public void onDirectoryCreate(File directory) {
        logger.info("[目录创建]:" + directory.getAbsolutePath());
        System.out.println("[目录创建]:" + directory.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }

    // 目录修改
    @Override
    public void onDirectoryChange(File directory) {
        logger.info("[目录修改]:" + directory.getAbsolutePath());
        System.out.println("[目录修改]:" + directory.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }

    // 目录删除
    @Override
    public void onDirectoryDelete(File directory) {
        logger.info("[目录删除]:" + directory.getAbsolutePath());
        // 触发业务
//        listenerService.doSomething();
    }


    // 轮询开始
    @Override
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }

    // 轮询结束
    @Override
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }

}

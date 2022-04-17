package com.sndshun.library.common.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 点赞的定时任务
 * @author mr.sun
 */
@Slf4j
public class LikeTask extends QuartzJobBean {


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        log.info("执行点赞定时任务-------------------- {}", sdf.format(new Date()));

        //将 缓存 里的点赞信息同步到数据库里

    }
}

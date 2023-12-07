package com.linkwechat.scheduler.task;



import com.linkwechat.common.utils.DateUtils;
import com.linkwechat.scheduler.service.SopTaskService;
import com.linkwechat.service.IWeSopBaseService;
import com.linkwechat.service.IWeSopExecuteTargetAttachmentsService;
import com.linkwechat.service.IWeSopExecuteTargetService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * sop相关定时任务
 * 1:提前结束sop处理(每五分钟处理一次)
 * 2:周期推送任务生成处理(每周日处理一次)
 * 3:定时推送提醒处理(每五分钟处理一次)
 */
@Slf4j
@Component
public class WeSopTask {

    @Autowired
    private IWeSopExecuteTargetService iWeSopExecuteTargetService;


    @Autowired
    private IWeSopExecuteTargetAttachmentsService iWeSopExecuteTargetAttachmentsService;

    @Autowired
    private SopTaskService sopTaskService;


//    @XxlJob("builderXkSopPlan")
//    public void builderXkSopPlan(String params){
//        log.info("新客sop构建计划>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
//        sopTaskService.builderXkPlan();
//    }


//    @XxlJob("handleChangeSop")
//    public void handleChangeSop(String params){
//        log.info("转入sop构建处理>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
//        sopTaskService.handleChangeSop();
//    }


     @XxlJob("builderXkSopPlanTip")
     public void builderXkSopPlanTip(String params){
         log.info("企业微信发送方式新客sop提醒发送>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
         iWeSopExecuteTargetAttachmentsService.weChatPushTypeSopTaskTip(null);

     }


    @XxlJob("earlyEndSopTask")
    public void earlyEndSop(String params) {
        log.info("提前结束sop提处理>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
        iWeSopExecuteTargetService.earlyEndConditionsSop();
    }

    @XxlJob("createCycleGroupSopTask")
    public void createCycleGroupSop(String params){
        log.info("创建周期群sop任务>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
        iWeSopExecuteTargetService.builderCycleExecutionPlan();

    }


    @XxlJob("pushWeChatTypeTipTask")
    public void pushWeChatTypeTaskTip(String params){
        log.info("执行sop任务每日任务提醒>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
        iWeSopExecuteTargetAttachmentsService.manualPushTypeSopTaskTip(false);
    }

    @XxlJob("pushSopAppMsgDailyTipTask")
    public void pushSopAppMsgDailyTip(String params){
        log.info("执行sop任务每日即将过期sop任务提醒>>>>>>>>>>>>>>>>>>>启动 params:{}", params);
        iWeSopExecuteTargetAttachmentsService.manualPushTypeSopTaskTip(true);
    }








}

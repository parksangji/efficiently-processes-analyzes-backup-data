package com.example.jpabatchrepository.batch;

import com.example.jpabatchrepository.entity.ProcessedLoginActivity;
import com.example.jpabatchrepository.entity.UserActivityLog;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class UserActivityLogProcessor implements ItemProcessor<UserActivityLog, ProcessedLoginActivity> {

    @Override
    public ProcessedLoginActivity process(UserActivityLog item) throws Exception {
        if ("LOGIN".equals(item.getActivityType())) {
            ProcessedLoginActivity processedLog = new ProcessedLoginActivity();
            processedLog.setUserId(item.getUserId());
            processedLog.setLoginTime(item.getActivityTime().plusHours(9)); // UTC -> KST
            return processedLog;
        }
        return null;
    }
}
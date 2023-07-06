package com.example.telegram_one.sevice;

import com.example.telegram_one.repository.NotificationTaskRepository;
import com.example.telegram_one.entity.NotificationTask;
import org.springframework.stereotype.Service;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    public void save(NotificationTask notificationTask) {
        notificationTaskRepository.save(notificationTask);
    }
}

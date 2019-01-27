package service.impl;

import dao.NotificationDao;
import model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.NotifyService;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    @Autowired
    private NotificationDao notificationDao;

    @Override
    @Transactional
    public void addNotification(Notification notification) {
        notificationDao.insertNotification(notification);
    }

    @Override
    @Transactional
    public List<Notification> getAllNotifications() {
        List<Notification> list = new ArrayList<>();
        for (Notification notification: notificationDao.getAllNotifications())
            if (notification.isUnread())
                list.add(notification);
        return list;
    }

    @Override
    @Transactional
    public void clearNotification(Notification notification) {
        notification.setUnread(false);
        notificationDao.updateNotification(notification);
    }
}

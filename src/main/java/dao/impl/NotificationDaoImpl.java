package dao.impl;

import dao.NotificationDao;
import model.Notification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NotificationDaoImpl extends BaseDaoImpl implements NotificationDao {
    @Override
    public void insertNotification(Notification notification) {
        save(notification);
    }

    @Override
    public List<Notification> getAllNotifications() {
        return getAllList(Notification.class);
    }

    @Override
    public void updateNotification(Notification notification) {
        update(notification);
    }
}

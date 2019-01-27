package dao;

import model.Notification;

import java.util.List;

public interface NotificationDao {
    public void insertNotification(Notification notification);

    public List<Notification> getAllNotifications();

    public void updateNotification(Notification notification);
}

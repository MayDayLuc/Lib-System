package service;

import model.Notification;

import java.util.List;

public interface NotifyService {
    public void addNotification(Notification notification);

    public List<Notification> getAllNotifications();

    public void clearNotification(Notification notification);
}

package service;

import model.Book;
import service.utils.OverdueInfo;

import java.util.List;

public interface OverdueService {
    public boolean isOverDue(String uid);

    public List<OverdueInfo> getPenaltyList();

    public boolean checkOverDue(Book book);
}

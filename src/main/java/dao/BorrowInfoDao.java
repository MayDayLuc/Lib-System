package dao;

import model.BorrowInfo;

import java.util.List;

public interface BorrowInfoDao {
    public List<BorrowInfo> getUserCurrentBorrow(String uid);
}

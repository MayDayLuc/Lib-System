package dao.impl;

import dao.BorrowInfoDao;
import model.BorrowInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BorrowInfoDaoImpl extends BaseDaoImpl implements BorrowInfoDao {
    @Override
    public List<BorrowInfo> getAllBorrowInfos() {
        return getAllList(BorrowInfo.class);
    }
}

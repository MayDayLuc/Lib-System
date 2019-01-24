package dao;

import dao.impl.BaseDaoImpl;
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

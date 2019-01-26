package service.utils;

public class GraduateBorrowMethod extends BorrowMethod {
    public GraduateBorrowMethod(int borrowCntThisTime, int borrowedCnt) {
        setMaxBorrowCnt(30);
        setMaxSingleBorrowCnt(10);
        setBorrowDays(30);
        setBorrowCntThisTime(borrowCntThisTime);
        setBorrowedCnt(borrowedCnt);
    }
}

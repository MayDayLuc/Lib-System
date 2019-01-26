package service.utils;

public class UndergraduateBorrowMethod extends BorrowMethod {
    public UndergraduateBorrowMethod(int borrowCntThisTime, int borrowedCnt) {
        setMaxBorrowCnt(20);
        setMaxSingleBorrowCnt(5);
        setBorrowDays(20);
        setBorrowCntThisTime(borrowCntThisTime);
        setBorrowedCnt(borrowedCnt);
    }
}

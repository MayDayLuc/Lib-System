package service.utils;

public class TeacherBorrowMethod extends BorrowMethod {
    public TeacherBorrowMethod(int borrowCntThisTime, int borrowedCnt) {
        setMaxBorrowCnt(40);
        setMaxSingleBorrowCnt(15);
        setBorrowDays(60);
        setBorrowCntThisTime(borrowCntThisTime);
        setBorrowedCnt(borrowedCnt);
    }
}

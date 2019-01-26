package service.utils;

public class GraduateBorrowMethod extends BorrowMethod {
    public GraduateBorrowMethod() {
        setMaxBorrowCnt(30);
        setMaxSingleBorrowCnt(10);
        setBorrowDays(30);
    }
}

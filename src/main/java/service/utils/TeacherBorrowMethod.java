package service.utils;

public class TeacherBorrowMethod extends BorrowMethod {
    public TeacherBorrowMethod() {
        setMaxBorrowCnt(40);
        setMaxSingleBorrowCnt(15);
        setBorrowDays(60);
    }
}

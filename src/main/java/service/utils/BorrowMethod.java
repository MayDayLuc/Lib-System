package service.utils;

import model.enums.UserType;

import java.time.LocalDate;

public abstract class BorrowMethod {
    private int maxBorrowCnt;
    private int maxSingleBorrowCnt;
    private int borrowDays;
    private int borrowCntThisTime;
    private int borrowedCnt;

    public static BorrowMethod getBorrowMethod(UserType userType, int borrowCntThisTime, int borrowedCnt) {
        switch (userType) {
            case TEACHER: return new TeacherBorrowMethod(borrowCntThisTime, borrowedCnt);
            case UNDERGRADUATE: return new UndergraduateBorrowMethod(borrowCntThisTime, borrowedCnt);
            case GRADUATE: return new GraduateBorrowMethod(borrowCntThisTime, borrowedCnt);
            default: return null;
        }
    }

    protected void setMaxBorrowCnt(int maxBorrowCnt) {
        this.maxBorrowCnt = maxBorrowCnt;
    }

    protected void setMaxSingleBorrowCnt(int maxSingleBorrowCnt) {
        this.maxSingleBorrowCnt = maxSingleBorrowCnt;
    }

    protected void setBorrowDays(int borrowDays) {
        this.borrowDays = borrowDays;
    }

    protected void setBorrowCntThisTime(int borrowCntThisTime) {
        this.borrowCntThisTime = borrowCntThisTime;
    }

    protected void setBorrowedCnt(int borrowedCnt) {
        this.borrowedCnt = borrowedCnt;
    }

    public  boolean isOverSingleLimit() {
        return borrowCntThisTime > maxSingleBorrowCnt;
    }

    public boolean isOverLimit() {
        return borrowCntThisTime + borrowedCnt > maxBorrowCnt;
    }

    public void add() {
        borrowCntThisTime ++;
    }

    public void subtract() {
        borrowCntThisTime --;
    }

    public LocalDate getDue() {
        return LocalDate.now().plusDays(borrowDays);
    }
}

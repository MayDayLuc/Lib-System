package service.utils;

public abstract class BorrowMethod {
    private int maxBorrowCnt;
    private int maxSingleBorrowCnt;
    private int borrowDays;

    public int getMaxBorrowCnt() {
        return maxBorrowCnt;
    }

    protected void setMaxBorrowCnt(int maxBorrowCnt) {
        this.maxBorrowCnt = maxBorrowCnt;
    }

    public int getMaxSingleBorrowCnt() {
        return maxSingleBorrowCnt;
    }

    protected void setMaxSingleBorrowCnt(int maxSingleBorrowCnt) {
        this.maxSingleBorrowCnt = maxSingleBorrowCnt;
    }

    public int getBorrowDays() {
        return borrowDays;
    }

    protected void setBorrowDays(int borrowDays) {
        this.borrowDays = borrowDays;
    }
}

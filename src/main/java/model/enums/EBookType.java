package model.enums;

public enum EBookType {
    DOC, PDF, EPUB;

    private static final int E_BOOK_NUM = 3;

    public static int getEBookNum() {
        return E_BOOK_NUM;
    }
}

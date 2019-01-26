package model.enums;

import java.util.HashMap;

public enum UserType {
    TEACHER, UNDERGRADUATE, GRADUATE, ADMINISTRATOR;

    public static String transform(UserType userType){
        return UT_TO_CH[userType.ordinal()];
    }

    private static final String[] UT_TO_CH= new String[] {"教师", "本科生", "研究生", "管理员"};
    private static final HashMap<String, UserType> CH_TO_UT;
    private static final int USER_NUM = 3;

    static {
        CH_TO_UT = new HashMap<>();
        CH_TO_UT.put("本科生", UNDERGRADUATE);
        CH_TO_UT.put("教师", TEACHER);
        CH_TO_UT.put("管理员", ADMINISTRATOR);
        CH_TO_UT.put("研究生", GRADUATE);
    }

    public static UserType chineseToUserType(String s){
        return CH_TO_UT.get(s);
    }

    public static int getUserNum() {
        return USER_NUM;
    }
}
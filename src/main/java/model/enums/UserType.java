package model.enums;

import java.util.HashMap;

public enum UserType {
    TEACHER, UNDERGRADUATE, GRADUATE, ADMINISTRATOR;

    public static String transform(UserType userType){
        return uTToCh[userType.ordinal()];
    }

    private static final String[] uTToCh= new String[] {"教师", "本科生", "研究生", "管理员"};
    private static final HashMap<String, UserType> chToUT;

    static {
        chToUT = new HashMap<>();
        chToUT.put("本科生", UNDERGRADUATE);
        chToUT.put("教师", TEACHER);
        chToUT.put("管理员", ADMINISTRATOR);
        chToUT.put("研究生", GRADUATE);
    }

    public static UserType chineseToUserType(String s){
        return chToUT.get(s);
    }
}
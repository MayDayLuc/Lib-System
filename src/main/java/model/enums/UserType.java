package model.enums;

public enum UserType {
    TEACHER, UNDERGRADUATE, GRADUATE, ADMINISTRATOR;

    public static String transform(UserType userType){
        switch (userType){
            case UNDERGRADUATE: return "本科生";
            case TEACHER: return "教师";
            case ADMINISTRATOR: return "管理员";
            case GRADUATE: return "研究生";
            default:return "";
        }
    }
}
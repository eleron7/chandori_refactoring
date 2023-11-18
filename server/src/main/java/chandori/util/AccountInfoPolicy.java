package chandori.util;

import java.util.regex.Pattern;

public class AccountInfoPolicy {
    public static void checkUserIdFormat(String id) {
        String pattern = "^[a-z0-9]+$";

        if (id == null || id.isBlank()) {
            throw new NullPointerException("아이디는 반드시 입력해주셔야 합니다.");
        }

        if (id.length() < 8 || id.length() > 20) {
            throw new RuntimeException("아이디는 8~20자로 설정해야 합니다");
        }

        if (!Pattern.matches(pattern, id)) {
            throw new RuntimeException("아이디는 영문 소문자, 숫자를 반드시 한개씩은 포함하여야 합니다");
        }
    }

    public static void checkPasswordFormat(String pw) {
        String pattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*]).{8,}$";

        if (pw == null || pw.isBlank()) {
            throw new NullPointerException("비밀번호는 반드시 입력해주셔야 합니다");
        }

        if (pw.length() < 8 || pw.length() > 30) {
            throw new RuntimeException("비밀번호는 8~30자로 설정해야 합니다");
        }

        if (!Pattern.matches(pattern, pw)) {
            throw new RuntimeException("비밀번호는 반드시 영문 대문자, 소문자, 숫자, 특수문자(!@#$%^&*)를 한개씩은 포함하여야 합니다");
        }
    }
}

package itsum.study.consts;

public enum ErrorCode {
    OK(200, "성공"),
    FAIL(999, "실패"),
    NOT_FOUND_ERROR(100, "NOT_FOUND_ERROR"),
    NOT_FOUND_TOKEN(101, "NOT_FOUND_TOKEN"),
    ;

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

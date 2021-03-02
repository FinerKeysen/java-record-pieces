package exception;

/**
 * @author zhujun
 */
public class CodedException extends RuntimeException {

    private static final long serialVersionUID = 3404262781594313963L;
    private final int code;

    public CodedException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CodedException(Errors errors) {
        super(errors.getMsg());
        this.code = errors.getCode();
    }

    public CodedException(Errors errors, String msgSuffix) {
        super(errors.getMsg() + ", " + msgSuffix);
        this.code = errors.getCode();
    }

    public CodedException(Errors errors, String msgSuffix, Throwable cause) {
        super(errors.getMsg() + ", " + msgSuffix, cause);
        this.code = errors.getCode();
    }

    public CodedException(Errors errors, Throwable cause) {
        super(errors.getMsg(), cause);
        this.code = errors.getCode();
    }

    public CodedException(Throwable cause) {
        super(cause);
        this.code = Errors.UNKNOWN_TYPE.getCode();
    }

    public int getCode() {
        return this.code;
    }
}

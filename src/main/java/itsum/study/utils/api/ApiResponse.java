package itsum.study.utils.api;

import com.nimbusds.oauth2.sdk.ErrorObject;
import com.nimbusds.oauth2.sdk.ErrorResponse;
import com.nimbusds.oauth2.sdk.http.HTTPResponse;
import itsum.study.consts.ErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T>  {
    private ApiHeader header;
    private ApiBody body;
    private static final int SUCCESS = 200;

    public ApiResponse(ApiHeader header, ApiBody body) {
        this.header = header;
        this.body = body;
    }

    public ApiResponse(ApiHeader header) {
        this.header = header;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(new ApiHeader(SUCCESS, "SUCCESS"), new ApiBody(data, null));
    }

    public static <T> ApiResponse<T> fail(ErrorCode errorCode) {
        return new ApiResponse(new ApiHeader(errorCode.getCode(), errorCode.name()), new ApiBody(null, errorCode.getMessage()));
    }
}

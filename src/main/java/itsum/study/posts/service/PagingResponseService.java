package itsum.study.posts.service;

import itsum.study.posts.common.CommonResult;
import itsum.study.posts.common.SliceResult;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class PagingResponseService {

    /**
     * @Method : getSliceResult
     * @Description : 페이징 결과 처리
     * @Parameter : [list]
     * @Return : SliceResult<T>
     **/
    public <T> SliceResult<T> getSliceResult(Slice<T> list) {
        SliceResult<T> result = new SliceResult<>();
        result.setData(list);
        setSuccessResult(result);
        return result;
    }

    /**
     * @Method : setSuccessResult
     * @Description : 결과에 api 요청 성공 데이터 세팅
     * @Parameter : [result]
     * @Return : null
     **/
    private void setSuccessResult(CommonResult result) {
        result.setSuccess(true);
        result.setCode(0);
        result.setMsg("성공하였습니다");
    }
}

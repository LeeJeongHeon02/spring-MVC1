package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model 모델 객체를 파라미터로 전달해준다.
     *              즉, 각 컨트롤러에서 모델 객체를 만들 필요가 없다.
     * @return
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);
}

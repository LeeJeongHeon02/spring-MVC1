package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<String, ControllerV4>();

    // 0. 컨트롤러 등록
    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        System.out.println("FrontControllerServletV3.service");
        // 1. 컨트롤러 조회
        String requestURI = request.getRequestURI();
        // 2. 컨트롤러 호출
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) { // 예외처리, 컨트롤러가 없으면 404 응답
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 3. ModelView 반환 로직
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); //v3 -> v4 추가
        String viewName = controller.process(paramMap, model); // 각 컨트롤러에 해당하는 viewName을 반환받음.

        // 4. ViewResolver 호출 및 5. MyView 반환
        MyView view = viewResolver(viewName);

        // 6. render(model) 호출
        view.render(model, request, response);
    }

    // viewPath (물리 뷰 경로)를 완성해서 반환해준다.
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}

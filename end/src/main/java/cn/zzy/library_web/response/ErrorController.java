package cn.zzy.library_web.response;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice("cn.zzy.library_web")
public class ErrorController {
    final static Logger LOG = LoggerFactory.getLogger(ErrorController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("调用异常！");
    }


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object exceptionHandler(BindException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("参数校验异常！");
    }



    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object exceptionHandler(RuntimeException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg(e.getMessage());
    }

}

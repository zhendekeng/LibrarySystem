package cn.zzy.library_web.response;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
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
        return JsonResponse.serverError().setMsg(e.getMessage());
    }


    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Object exceptionHandler(BindException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("参数校验异常！");
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public Object exceptionHandler(ExpiredJwtException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("token过期");
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    public Object exceptionHandler(SignatureException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("token有问题");
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Object exceptionHandler(RuntimeException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public Object exceptionHandler(NullPointerException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("空指针异常");
    }

    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public Object exceptionHandler(ClassCastException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("类型强制转换异常");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public Object exceptionHandler(IllegalArgumentException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("非法参数异常");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Object exceptionHandler(ArithmeticException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("算术运算异常");
    }
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public Object exceptionHandler(IndexOutOfBoundsException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("下标越界异常");
    }
    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public Object exceptionHandler(NumberFormatException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("数字格式异常异常");
    }

    @ExceptionHandler(NegativeArraySizeException.class)
    @ResponseBody
    public Object exceptionHandler(NegativeArraySizeException e){
        LOG.error("", e);
        return JsonResponse.serverError().setMsg("负数的数组异常");
    }
}

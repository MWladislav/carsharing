package epam.training.finalproject.carsharing.controller;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DataAccessException.class)
    public ModelAndView dataAccessExceptionHandle(HttpServletRequest req, Exception e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("errorPage");
        modelAndView.addObject("errorMsg",e.getMessage());
        LOGGER.info("DataAccessException Occured: URL="+req.getRequestURL());
        LOGGER.error(e.getMessage(),e.getCause());
        return modelAndView;
    }
}

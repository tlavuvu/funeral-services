//package za.co.supremeworx.interceptors;
//
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//public class LogHandlerInterceptor implements HandlerInterceptor{
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		// TODO Auto-generated method stub
////		return HandlerInterceptor.super.preHandle(request, response, handler);
//		log.info("LogHandlerInterceptor::prehandle {} {}",request.getLocalAddr(),request.getHeaderNames());
//		return true;
//	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//		log.info("LogHandlerInterceptor::postHandle");
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//		log.info("LogHandlerInterceptor::afterCompletion");
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
//	}
//
//	
//}

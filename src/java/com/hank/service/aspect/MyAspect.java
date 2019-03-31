
package com.hank.service.aspect;

import com.hank.web.action.GenericAction;
import com.hank.web.action.RegisterAction;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;


@Service("myAspect")
@Aspect
public class MyAspect{
    
    @Around("MyAspect.testWhetherLoginPointcut()")
    public Object testWhetherLoginAspect(ProceedingJoinPoint joinPoint) throws Throwable{
        Map<String,Object> session = ActionContext.getContext().getSession();
        Member member = (Member)session.get("member");
        if(member == null){
            // forward to login.jsp
            // 提示訊息 : 請先登入
            GenericAction action = (GenericAction)joinPoint.getTarget();
            action.addActionError("請先登入");
            return "input";
        }
        return joinPoint.proceed();      
    }
    @Around("MyAspect.testWhetherPassword_Equals_PasswordConfirmed_PointCut()")
    public Object testWhetherPassword_Equals_PasswordConfirmed_Aspect(ProceedingJoinPoint joinPoint) throws Throwable{
        RegisterAction registerAction = (RegisterAction)joinPoint.getTarget();
        String password = registerAction.getMember().getPassword();
        String password_confirmed = registerAction.getPassword_confirmed();
        if( !password.equals(password_confirmed) ){
            //forward to register.jsp
            //提示訊息 : 密碼與密碼確認不一致
            registerAction.addActionError("密碼與密碼確認不一致");
            return "input";
        }
        return joinPoint.proceed(); 
    }
    
    
    
    @Pointcut(" execution( public * com.hank.service.OrderAction.*(..) ) ||"   //OrderService底下的所有方法(public修飾)
            + " execution( public * com.hank.service.MainAction.add*InCart(..) )|| " //MainService底下的add*InCart方法(public修飾)
            + " execution( public * com.hank.service.MainAction.logOut(..) ) ")  //MainService底下的logOut方法(public修飾)
    private void testWhetherLoginPointcut(){
        
    }
     @Pointcut("execution(* com.hank.service.RegisterAction.execute(..) )")
    private void testWhetherPassword_Equals_PasswordConfirmed_PointCut(){
        
    }
}

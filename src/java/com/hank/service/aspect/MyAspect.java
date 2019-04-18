
package com.hank.service.aspect;

import com.hank.service.GenericService;
import com.hank.service.RegisterService;
import com.hank.web.action.RegisterAction;
import com.opensymphony.xwork2.ActionContext;
import hibernate_pojo.Member;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service("myAspect")
public class MyAspect{
    
    @Around("testWhetherLoginPointcut()")
    public Object testWhetherLoginAspect(ProceedingJoinPoint joinPoint) throws Throwable{
        Map<String,Object> session = ActionContext.getContext().getSession();
        Member member = (Member)session.get("member");
        if(member == null){
            // forward to login.jsp
            // 提示訊息 : 請先登入
            GenericService service = (GenericService)joinPoint.getTarget();
            service.getAction().addActionError("請先登入");
            return "input";
        }
        return joinPoint.proceed();      
    }
    @Around("testWhetherPassword_Equals_PasswordConfirmed_PointCut()")
    public Object testWhetherPassword_Equals_PasswordConfirmed_Aspect(ProceedingJoinPoint joinPoint) throws Throwable{
        RegisterService registerService = (RegisterService)joinPoint.getTarget();
        RegisterAction registerAction = (RegisterAction)registerService.getAction();
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
    
    
    
    @Pointcut(" execution( public * com.hank.service.OrderService.*(..) ) ||"   //OrderService底下的所有方法(public修飾)
            + " execution( public * com.hank.service.MainService.add*InCart(..) )|| " //MainService底下的add*InCart方法(public修飾)
            + " execution( public * com.hank.service.MainService.logOut(..) ) ||"
            + " execution( public * com.hank.service.ChangeComboService.*(..) )")  //ChangeComboService底下的所有方法(public修飾)
    private void testWhetherLoginPointcut(){
        
    }
     @Pointcut("execution(* com.hank.service.RegisterService.register(..) )")
    private void testWhetherPassword_Equals_PasswordConfirmed_PointCut(){
        
    }
}

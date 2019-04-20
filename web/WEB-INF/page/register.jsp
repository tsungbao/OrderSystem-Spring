<%-- 
    Document   : register
    Created on : 2018/11/29, 上午 10:54:53
    Author     : hank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>琮寶點餐系統</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Online Food World  template Responsive, Login form web template,Flat Pricing tables,Flat Drop downs Sign up Web Templates, 
              Flat Web Templates, Login sign up Responsive web template, SmartPhone Compatible web template, free web designs for Nokia, Samsung, LG, SonyEricsson, Motorola web design">
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <!-- Custom Theme files -->
        <link href="<c:url value='/css/style.css'/>" rel='stylesheet' type='text/css' />
        <!--fonts-->
        <link href="//fonts.googleapis.com/css?family=Cookie" rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i" rel="stylesheet">
        <!--//fonts--> 
    </head>
    <body>
        <!-- login -->
         <div style="text-align:center;">
            <s:a action="main" cssClass="wthree">TSUNG-BAO</s:a>
        </div>
        <div class="container login-section">	
            <div class="login-w3l">	
                <div class="login-form">	
                    <h2 class="wthree">Register!</h2>	
                    <s:actionerror cssStyle="color:red;"/>
                    <s:form  action="register"  method="post"  class="agile_form"  theme="simple">

                        <div class="w3ls-name1">
                            <s:label cssClass="header">姓名</s:label>
                                <div class="agile_form">
                                <s:fielderror fieldName="member.memberName" cssStyle="color:red;"/>
                                <s:textfield name="member.memberName"/>
                            </div>
                        </div>
                        <div class="w3ls-name1">
                            <s:label cssClass="header">電話</s:label>
                                <div class="agile_form">
                                <s:fielderror fieldName="member.memberPhone" cssStyle="color:red;"/>
                                <s:textfield name="member.memberPhone"/>
                            </div>
                        </div>	
                        <div class="w3ls-name1">
                            <s:label cssClass="header">帳號</s:label>
                                <div class="agile_form">
                                <s:fielderror fieldName="member.account" cssStyle="color:red;"/>
                                <s:textfield name="member.account"/>
                            </div>
                        </div>	
                        <div class="w3ls-name1">
                            <s:label cssClass="header">密碼</s:label>
                                <div class="agile_form">
                                <s:fielderror fieldName="member.password" cssStyle="color:red;"/>
                                <s:textfield name="member.password"/>
                            </div>
                        </div>	
                        <div class="w3ls-name1">
                            <s:label cssClass="header">密碼確認</s:label>
                                <div class="agile_form">
                                <s:fielderror fieldName="password_confirm" cssStyle="color:red;"/>
                                <s:textfield name="password_confirmed"/>
                            </div>
                        </div>	 
                        <div class="agile_form">                                        
                            <s:submit value="註冊完成" />
                            <s:a action="main">取消註冊</s:a>
                        </div>    
                    </s:form>
                </div>
            </div>	
            <div class="login-w3l-bg">	
                <img src="<c:url value='/css/imagesForLogin/grid.png'/>" alt=""/>
            </div>	 
            <div class="clearfix"></div>	
        </div> 	
        <p class="footer">© 2017 Online Food World. All Rights Reserved | Design by <a href="http://w3layouts.com/"> W3layouts</a></p>
    </body>
</html>

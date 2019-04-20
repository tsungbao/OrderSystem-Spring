

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>琮寶點餐系統</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="">
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
				<h2 class="wthree">Login !</h2>	
                                <s:actionerror cssStyle="color:red;" />
                                <s:form action="login" method="post" class="agile_form" theme="simple">
                                    
					<div class="w3ls-name1">
                                            <s:label cssClass="header">帳號</s:label>                                           
                                            
                                            <div class="agile_form">
                                                <s:fielderror fieldName="account" cssStyle="color:red;"/>
                                                <s:textfield name="account"/>
                                            </div>
					</div>
					<div class="w3ls-name1">
                                            <s:label cssClass="header">密碼</s:label>
                                            <div class="agile_form">
                                            <s:fielderror fieldName="password" cssStyle="color:red;"/>
                                            <s:textfield name="password"/>
                                            </div>
					</div>	
                                        <div class="agile_form">
                                            <s:url id="login" action="login" method="login"/>                                                                               
                                            <s:submit value="登入"/>
                                            <s:a action="register" >註冊</s:a>
                                        </div>    
				</s:form>
			</div>
		</div>	
		<div class="login-w3l-bg">	
                     <!--這種純html語法 沒有所謂根目錄的概念  就是 絕對路徑(C:/user/...)和相對路徑-->
                     <img src="<c:url value='/css/imagesForLogin/grid.png'/>" alt=""/>
		</div>	 
		<div class="clearfix"></div>	
	</div> 	
	<p class="footer">© 2017 Online Food World. All Rights Reserved | Design by <a href="http://w3layouts.com/"> W3layouts</a></p>
    </body>
</html>

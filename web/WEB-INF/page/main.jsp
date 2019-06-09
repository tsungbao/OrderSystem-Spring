<%-- 
    Document   : main
    Created on : 2018/11/27, 下午 11:17:07
    Author     : hank
--%>

<%-- 
        member - session
        menuItems - request
        combos - request
   img
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib  prefix="myTags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>琮寶點餐系統</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="" />
        <script type="application/x-javascript"> addEventListener("load", function() {setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>

        <!--fonts-->
        <link href='//fonts.googleapis.com/css?family=Fredoka+One' rel='stylesheet' type='text/css'>

        <!--fonts-->
        <!--bootstrap-->

        <link href="<c:url value='/cssForMain/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
        <!--coustom css-->

        <link href="<c:url value='/cssForMain/style.css'/>" rel="stylesheet" type="text/css"/>
        <!--shop-kart-js-->

        <script src="<c:url value='/js/simpleCart.min.js'/>"></script>
        <!--default-js-->

        <script src="<c:url value='/js/jquery-2.1.4.min.js'/>"></script>
        <!--bootstrap-js-->

        <script src="<c:url value='/js/bootstrap.min.js'/>"></script>
        <!--script-->
        <script>
            $(function () {
                $("input[type='submit']").click(function () {
                    $('html,body').animate({scrollTop: $('#cartStatus').offset().top}, 10);
                });
            });
        </script>
        <sj:head/>
        <s:head />

    </head>
    <body>
        <myTags:Header/>
        <div class="header-end">
            <div class="container">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>

                    <!-- Wrapper for slides -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="<c:url value='/images/shoe2.jpg'/>" alt="...">
                            <div class="carousel-caption car-re-posn">
                                <h3>AirMax</h3>
                                <h4>You feel to fall</h4>
                                <span class="color-bar"></span>
                            </div>
                        </div>
                        <div class="item">
                            <img src="<c:url value='/images/shoe2.jpg'/>" alt="...">
                            <div class="carousel-caption car-re-posn">
                                <h3>AirMax</h3>
                                <h4>You feel to fall</h4>
                                <span class="color-bar"></span>
                            </div>
                        </div>
                        <div class="item">
                            <img src="<c:url value='/images/shoe2.jpg'/>" alt="...">
                            <div class="carousel-caption car-re-posn">
                                <h3>AirMax</h3>
                                <h4>You feel to fall</h4>
                                <span class="color-bar"></span>
                            </div>
                        </div>
                    </div>

                    <!-- Controls -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left car-icn" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right car-icn" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <div class="feel-fall">
            <div class="container">
                <c:forEach var="realCombo" items="${requestScope.combos}">
                    <div class="pull-left fal-box" >
                        <div class=" fall-left">
                            <h3>${realCombo.name}</h3>
                            <img src="<c:url value='/images/grid4.jpg'/>" alt="/" class="img-responsive fl-img-wid">
                            <div class=" fall-left">                               
                                <!-- 輸入欲購買套餐數量-->                                                           
                                <c:choose>                        
                                    <c:when test="${sessionScope.member!=null}">    
                                        <input type="button" value="更換套餐內容"/>
                                        <!--  itemName參數-->
                                        <c:set var="currentIterate_comboName" scope="request" value="${realCombo.name}"/>  
                                        <!--  description參數-->
                                        <c:set var="currentIterate_comboDescription" scope="request" value="${realCombo.description}"/>   
                                        <%
                                            String desc = (request.getAttribute("currentIterate_comboDescription")).toString();

                                            desc = desc.replace("[", "");
                                            desc = desc.replace("]", "");
                                            desc = desc.replaceAll(" ", "");
                                            request.setAttribute("currentIterate_comboDescription", desc);
                                        %>

                                        <s:form name="quantityForm%{#request.currentIterate_comboName}" id="quantityForm%{#request.currentIterate_comboName}" action="addComboInCart" method="post">                                          
                                            <!-- quantity 參數 -->
                                            <s:hidden name="itemName" value="%{#request.currentIterate_comboName}"/>
                                            <s:hidden name="description" value="%{#request.currentIterate_comboDescription}"/>
                                            <s:textfield id="quantity%{#request.currentIterate_comboName}"  label="請輸入要購買的數量(%{#request.currentIterate_comboName})" type="number"  value="1" name="quantity"  min="1" max="30" />
                                            <sj:submit  value="確認" targets="cartStatus" executeScripts="true" formId="quantityForm%{#request.currentIterate_comboName}" />
                                        </s:form>
                                    </c:when>    
                                    <c:otherwise>
                                        <a href="login" type="button">請先登入</a>
                                    </c:otherwise>
                                </c:choose>

                            </div>
                            <p>${realCombo.description}<br>${realCombo.price}</p>

                            <span class="fel-fal-bar"></span>

                        </div>

                    </div>      
                </c:forEach>
                <div class="clearfix"></div>
            </div>
        </div>


        <div class="shop-grid">
            <div class="container">
                <c:forEach var="item" items="${requestScope.menuItems}">

                    <div class="col-md-4 grid-stn simpleCart_shelfItem">
                        <!-- normal -->
                        <div class="ih-item square effect3 bottom_to_top">
                            <div class="bottom-2-top">

                                <div class="img"><img src="<c:url value='/images/grid4.jpg'/>" alt="/" class="img-responsive gri-wid"></div>
                                <div class="info">
                                    <div class="pull-left styl-hdn">
                                        <h3>${item.name}</h3>
                                    </div>
                                    <div class="pull-right styl-price">                                  
                                        <p><a class="item_add"><span class="glyphicon glyphicon-shopping-cart grid-cart" aria-hidden="true"></span> <span class=" item_price">$${item.price}</span></a></p>                                                                                     
                                    </div>                               
                                    <div class="clearfix"></div>
                                </div></div>
                        </div>
                        <!-- end normal -->
                        <div class="quick-view">
                            <sj:div  cssStyle="text-align:center">
                                <!-- 輸入欲購買單品數量-->                               
                                <c:choose>
                                    <c:when test="${sessionScope.member!=null}">    
                                        <c:set var="currentIterate_itemName" scope="request" value="${item.name}"/>
                                        <s:form cssStyle="position:inline-block" name="quantityForm%{#request.currentIterate_itemName}" id="quantityForm%{#request.currentIterate_itemName}" action="addSingleInCart?itemName=%{#request.currentIterate_itemName}" method="post" theme="simple">
                                            欲購數量:<s:textfield id="quantity%{#request.currentIterate_itemName}" type="number"  value="1" name="quantity"  min="1" max="30"/>
                                            <sj:submit value="確認" targets="cartStatus" executeScripts="true" formId="quantityForm%{#request.currentIterate_itemName}" />
                                        </s:form>
                                    </c:when>    
                                    <c:otherwise>
                                        <a href="login" type="button">請先登入</a>
                                    </c:otherwise>
                                </c:choose>                                
                            </sj:div>  
                        </div>
                    </div>
                </c:forEach>  
                <div class="clearfix"></div>
            </div>
        </div>  
        <div class="copy-rt">
            <div class="container">
                <p>&copy;   2015 N-AIR All Rights Reserved. Design by <a href="http://www.w3layouts.com">w3layouts</a></p>
            </div>
        </div>     
    </body>
</html>

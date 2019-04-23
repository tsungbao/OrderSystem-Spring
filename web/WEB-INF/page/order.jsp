<%-- 
    Document   : order
    Created on : 2018/11/30, 下午 03:38:02
    Author     : hank
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
        <title>訂單確認</title>
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
        <!-- FlexSlider -->
        <script src="<c:url value='/js/imagezoom.js'/>"></script>
        <script defer src="<c:url value='/js/jquery.flexslider.js'/>"></script>
        <link rel="stylesheet" href="<c:url value='/cssForMain/flexslider.css'/>" type="text/css" media="screen" />

        <script>
            // Can also be used with $(document).ready()
            $(window).load(function () {
                $('.flexslider').flexslider({
                    animation: "slide",
                    controlNav: "thumbnails"
                });
            });
        </script>


        <!-- //FlexSlider-->
        <sj:head/>
        <s:head />
    </head>
    <body>
        <myTags:Header/>
        <s:actionerror/>
        <div class="head-bread">
            <div class="container">
                <ol class="breadcrumb">
                    <li><a>Home</a></li>
                    <!--  <li><a href="products.hml">Products</a></li>  -->
                    <li class="active">CART</li>
                </ol>
            </div>
        </div>
        <!-- check-out -->
        <div class="check">
            <div class="container">	 
                <div class="col-md-3 cart-total">
                    <a class="continue" href="main">繼續購物</a>

                    <hr class="featurette-divider">
                    <ul class="total_price">
                        <li class="last_price"> <h4>TOTAL</h4></li>	
                        <li class="last_price"><span>$${sessionScope.member.cart.totalPrice} </span></li>
                        <div class="clearfix"></div>
                    </ul> 
                    <div class="clearfix"></div>
                    <s:url var="submit" action="submit"/>
                    <a class="order" href="<s:property value='%{#submit}' />">確認送出訂單</a>
                </div>
                <div class="col-md-9 cart-items">
                    <h1 id="shoppingBag">My Shopping Bag (${sessionScope.member.cart.quantity})</h1>

                    <div id="cart">
                        <c:forEach var="cartItem" items="${requestScope.cartRealsMap}">
                            <div class="cart-header">       
                                <!--給到order_cart.jsp 的 url-->
                                <s:url var="deleteComboInCart" action="deleteComboInCart" escapeAmp="false">
                                    <s:param name="name"><c:out value='${cartItem.key.name}' escapeXml='true'/></s:param>            
                                    <c:set var="description" value="${cartItem.key.description}" scope="request"/>
                                    <s:param name="description">
                                        <s:property 
                                            value="%{@com.hank.domain.context.Context@description_fromArrayList_toString(#request.description)}"></s:property>                         
                                    </s:param>
                                </s:url>
                                <!--給/changeCombo_order用的 將comboToBeChangedQuantity以及 cartId_comboToBeChanged 放入參數  -->
                                <s:url var="changeCombo_order" action="changeCombo_order" escapeAmp="false">
                                    <s:param name="comboToBeChangedQuantity"><c:out value="${cartItem.value}"/></s:param>
                                    <s:param name="cartId_comboToBeChanged"><c:out value="${cartItem.key.cartId}"/></s:param>
                                </s:url>
                                <div class="close1"><a href="<s:property value='%{#deleteComboInCart}'/>">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </a></div>
                                <div class="cart-sec simpleCart_shelfItem">
                                    <div class="cart-item cyc">
                                        <img src="<c:url value='/images/grid4.jpg'/>" class="img-responsive" alt=""/>
                                    </div>
                                    <div class="cart-item-info">
                                        <ul class="qty">
                                            <li><p>商品名稱 : ${cartItem.key.name}</p></li>
                                            <li><p style="display:inline">套餐內容 </p>
                                                <p style="display:inline"><a href="<s:property value='%{#changeCombo_order}' />">(點選可更換)</a></p>                                                                           
                                                <p style="display:inline"> : ${cartItem.key.description}</p></li>
                                            <li><p>單品價格  : $${cartItem.key.price}</p></li>
                                            <li><p>購買數量  : ${cartItem.value}</p></li>
                                            <li><p>總金額  : ${cartItem.value*cartItem.key.price}</p></li>                                      
                                        </ul>	
                                    </div>
                                    <div class="clearfix"></div>                            
                                </div>
                            </div>
                        </c:forEach>   
                        <c:forEach var="cartItem" items="${requestScope.cartSinglesMap}">
                            <div class="cart-header">

                                <!--給到order_cart.jsp 的 url-->
                                <s:url var="deleteSingleInCart" action="deleteSingleInCart">
                                    <s:param name="name"><c:out value='${cartItem.key.name}' escapeXml='true'/></s:param>
                                </s:url>

                                <div class="close1"><a href="<s:property value='%{#deleteSingleInCart}'/>">
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </a></div>
                                <div class="cart-sec simpleCart_shelfItem">
                                    <div class="cart-item cyc">
                                        <img src="<c:url value='/images/grid4.jpg'/>" class="img-responsive" alt=""/>
                                    </div>
                                    <div class="cart-item-info">
                                        <ul class="qty">
                                            <li><p>商品名稱 : ${cartItem.key.name}</p></li>
                                            <li><p>單品價格  : $${cartItem.key.price}</p></li>
                                            <li><p>購買數量  : ${cartItem.value}</p></li>
                                            <li><p>總金額  : ${cartItem.value*cartItem.key.price}</p></li>                                       
                                        </ul>

                                    </div>
                                    <div class="clearfix"></div>                            
                                </div>
                            </div>
                        </c:forEach>   
                    </div>


                </div>
                <div class="clearfix"> </div>
            </div>
        </div>
        <div class="footer-grid">
            <div class="copy-rt">
                <div class="container">
                    <p>&copy;   2015 N-AIR All Rights Reserved. Design by <a href="http://www.w3layouts.com">w3layouts</a></p>
                </div>
            </div>
        </div>
    </body>
</html>

<%-- 
    Document   : order_cart
    Created on : 2018/12/5, 下午 09:49:21
    Author     : hank
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<script>
    function update() {
        document.getElementById("shoppingBag").innerHTML = "My Shopping Bag(" + '${sessionScope.member.cart.quantity}' + ")";
    }
</script>
<c:forEach var="cartItem" items="${requestScope.cartRealsMap}">
    <div class="cart-header">       
        <!--給到order_cart.jsp 的 url-->
        <s:url var="deleteComboInCart" action="deleteComboInCart">
            <s:param name="name"><c:out value='${cartItem.key.name}' escapeXml='true'/></s:param>

            <c:set var='description' scope='request' value='${cartItem.key.description}' />
            <s:param name="description">
                <s:property 
                    value="@com.hank.domain.menu.Context@description_fromArrayList_toString(#request.description)"></s:property>                         
            </s:param>
        </s:url>

        <div class="close1"><sj:a href="%{#deleteComboInCart}" targets="cart"  onclick="update()"> 
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </sj:a></div>
        <div class="cart-sec simpleCart_shelfItem">
            <div class="cart-item cyc">
                <img src="<c:url value='${cartItem.key.imgResc}'/>" class="img-responsive" alt=""/>
            </div>
            <div class="cart-item-info">
                <ul class="qty">
                    <li><p>商品名稱 : ${cartItem.key.name}</p></li>
                    <li><p>套餐內容  : ${cartItem.key.description}</p></li>
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


        <div class="close1"><sj:a href="%{#deleteSingleInCart}"  targets="cart" onclick="update()">
                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
            </sj:a></div>
        <div class="cart-sec simpleCart_shelfItem">
            <div class="cart-item cyc">
                <img src="<c:url value='${cartItem.key.imgResc}'/>" class="img-responsive" alt=""/>
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

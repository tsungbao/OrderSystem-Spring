<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%--
<script>
    document.getElementById('enterQuantity'+ <s:property value='%{#request.itemNameForJsp}'/>).style.display='none';
</script>--%>
   
<span <%--class="simpleCart_total" --%>  > 
    $${sessionScope.member.cart.totalPrice}
</span>(
<span> <%-- id="simpleCart_quantity" class="simpleCart_quantity"   --%> 
 <s:property value="#session.member.cart.quantity"/>   
</span>)


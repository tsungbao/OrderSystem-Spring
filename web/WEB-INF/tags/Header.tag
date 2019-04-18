<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib  prefix="myTags" tagdir="/WEB-INF/tags" %>
        <div class="header">
            <div class="container">
                <div class="header-top">
                    <div class="logo">
                        <a href="main">Tsung-Bao</a>
                        </div>
                        <div class="login-bars">
                        <a class="btn btn-default log-bar" href="register">註冊</a>
                        <c:choose>
                            <c:when test="${sessionScope.member==null}">
                                <s:a  cssClass="btn btn-default log-bar" action="login" role="button">登入</s:a>
                            </c:when>
                            <c:otherwise>
                                <s:a  cssClass="btn btn-default log-bar" action="logOut" role="button">登出</s:a>
                            </c:otherwise>
                        </c:choose>

                        <c:choose>
                            <c:when test="${sessionScope.member==null}">
                                <p class="btn btn-default log-bar">Guest</p>
                            </c:when>
                            <c:otherwise>
                                <p class="btn btn-default log-bar">${member.memberName}</p>
                            </c:otherwise>
                        </c:choose>


                        <div class="cart box_1">
                        <c:if test="${sessionScope.member!=null}">
                            <a href="order">
                        </c:if>        
                                <h3>
                                    <sx:div id="cartStatus" cssClass="total">
                                        <span <%--class="simpleCart_total" --%>  > 
                                            $${sessionScope.member.cart.totalPrice}
                                        </span>(
                                        <span  <%-- id="simpleCart_quantity" class="simpleCart_quantity"   --%> >
                                            <s:property value="#session.member.cart.quantity"/>   
                                        </span>)
                                    </sx:div>
                                </h3>
                        <c:if test="${sessionScope.member!=null}">        
                            </a>
                        </c:if>
                            <div class="clearfix"> </div>
                        </div>	


                    </div>
                    <div class="clearfix"></div>
                </div>
                <!---menu-----bar--->

                <!--header-bottom-->
            </div>
        </div>
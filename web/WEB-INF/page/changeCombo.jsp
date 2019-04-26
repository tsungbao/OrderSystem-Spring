<%-- 
[session]
<member>
[request]
<comboToBeChanged>
categories_for_comboToBeChanged : Map<Combo,ArrayList<Combo>.>
<quantity>
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib  prefix="myTags" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更換套餐內容</title>
        <link href="<c:url value='/cssForChangCombo/css/bootstrap.css'/>" rel='stylesheet' type='text/css' /><!-- bootstrap css -->
        <link href="<c:url value='/cssForChangCombo/css/style.css'/>" rel='stylesheet' type='text/css' /><!-- custom css -->
        <link href="<c:url value='/cssForChangCombo/css/css_slider.css'/>" type="text/css" rel="stylesheet" media="all">
        <link href="<c:url value='/cssForChangCombo/css/font-awesome.min.css'/>" rel="stylesheet"><!-- fontawesome css -->
        <link href="<c:url value='/cssForMain/style.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/cssForMain/bootstrap.min.css'/>" rel="stylesheet" type="text/css">
        <script src="<c:url value='/js/jquery-2.1.4.min.js'/>"></script>
        <script>
            function changeCombo(itemName, categoryName) {
                document.getElementById(categoryName).innerHTML = itemName;
            }

            $(function () {
                $('div.scrollToComboItem').click(function () {
                    $('html,body').animate({scrollTop: $('#comboItem').offset().top}, 10);
                });
            });


        </script>
        <script>
            function doPostForm() {
            <%-- <div>集合     --%>
                var new_descs = document.getElementsByClassName("new_desc");
            <%--   <s:hidden>     --%>
                var hidden = document.getElementById("new_desc");
                hidden.value = "";
                for (i = 0; i < new_descs.length; i++) {
            
                    hidden.value += new_descs[i].firstElementChild.innerHTML;
                    if (i !== new_descs.length - 1) {
                        hidden.value += ',';
                    }


                }
            }
        </script>
    </head>
    <body>
        <myTags:Header/>
        <div class="container">
            <h3 id="comboItem" class="heading mb-5">當前套餐內容</h3>
            <s:form id="form"  action="changeCombo_submit" theme="simple" method="post" >
                <s:hidden name="quantity" value="%{#request.quantity}"/>
                <s:hidden name="cartId_comboToBeChanged" value="%{#request.comboToBeChanged.cartId}"/>
                <s:hidden id="new_desc" name="new_desc" value=""/>
                <c:forEach var="categories_for_comboToBeChanged_entry" items="${requestScope.categories_for_comboToBeChanged}">
                    <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12">
                        <div class="our-services-wrapper mb-60">
                            <div class="services-inner">
                                <div class="our-services-img">
                                    <img src="images/s1.png" alt="">
                                </div>
                                <div class="our-services-text new_desc" >
                                    <h4 id="<c:out value='${categories_for_comboToBeChanged_entry.key.category}' />">${categories_for_comboToBeChanged_entry.key.name}</h4>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <s:submit value="確認套餐內容"  onclick="doPostForm()" />
            </s:form>
        </div>
        <hr>
        <section class="services py-5" id="services">
            <c:forEach var="categories_for_comboToBeChanged_entry" items="${requestScope.categories_for_comboToBeChanged}">
                <div class="container">
                    <h3 class="heading mb-5">${categories_for_comboToBeChanged_entry.key.category}</h3>

                    <%--  <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12">
                                          <div class="our-services-wrapper mb-60">
                                              <div class="services-inner">
                                                  <div class="our-services-img">
                                                      <img src="images/s1.png" alt="">
                                                  </div>
                                                  <div class="our-services-text">
                                                      <h4>${categories_for_comboToBeChanged_entry.key.name}</h4>
                                                  </div>
                                              </div>
                                          </div>
                                   </div> --%>

                    <div class="row ml-sm-5">
                        <%-- 同一種類商品 --%>
                        <c:forEach var="categoryItem_for_comboToBeChanged" items="${categories_for_comboToBeChanged_entry.value}">
                            <div class="col-xl-4 col-lg-4 col-md-6 col-sm-12 scrollToComboItem" 
                                 onclick="changeCombo('${categoryItem_for_comboToBeChanged.name}', '${categoryItem_for_comboToBeChanged.category}')">
                                <div class="our-services-wrapper mb-60">
                                    <div class="services-inner">
                                        <div class="our-services-img">
                                            <img src="images/s1.png" alt="">
                                        </div>
                                        <div class="our-services-text">
                                            <h4>${categoryItem_for_comboToBeChanged.name}</h4>
                                            <p>${categoryItem_for_comboToBeChanged.price}</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                        <%-- 同一種類商品 --%>
                    </div>
                </div>
            </c:forEach>   
        </section>
    </body>
</html>

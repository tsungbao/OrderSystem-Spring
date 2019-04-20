<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<head>
    <sj:head/>
    <script>     
       
            document.getElementById('enterQuantity'+<s:property value='%{#request.itemNameForJsp}'/>).style.display='';
        
        
    </script>
</head>






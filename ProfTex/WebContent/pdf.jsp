<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<object data="<%= request.getAttribute("pdf_path") %>#view=FitH" type="application/pdf" class="pdf_obj"> 
		Your Browser does not support this feature, please click here: <a href="<%= request.getAttribute("pdf_path") %>">Access PDF</a> 
	</object>
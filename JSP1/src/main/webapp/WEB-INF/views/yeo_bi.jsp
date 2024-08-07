<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>연습</title>
</head>
<body>

    
    <div>
      취업 : <%= request.getParameter("Employment") %>
      <br>
      age : <%= request.getParameter("age") %>
    </div>

    <div>
      취업 : ${param.Employment}
      <br>
      희망직종 : ${param.gob}
    </div>
  
</body>
</html>
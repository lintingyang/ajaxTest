<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String name = request.getParameter("name");
String age = request.getParameter("age");
out.print("Hello " + name + " You are " + age + " years old.");
%>

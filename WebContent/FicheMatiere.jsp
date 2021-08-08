<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%@page import="modele.Matiere"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Matiere u=(Matiere)request.getAttribute("matiere");
String code="";
String libelle="";
int type=2;
float cof=0.0f;

if(u!=null)
{  
	 code=u.getCode();
	 libelle=u.getLibelle();
	 type=u.getType();
	 cof=u.getCoeificient();

}

%>

<form method="POST" action ="FicheMatiereAction">
Veuillez saisir vos paramètres:
<hr>
<table>
<tr>
<td>Code Matiere:</td>
<td><input type="text" value="<%=code %>" name="code" /></td>
</tr>
<tr>
<td>Libelle:</td>
<td><input type="text" value="<%=libelle %>" name="libelle" /></td>
</tr>
<tr>
<td>Coeificient:</td>
<td><input type="text" value="<%=cof %>" name="coeff" /></td>
</tr>
<tr>
<td>Type </td>
<% if(type==0) {%>
<td>
Cour : <input type="radio" value="0" name="r" checked="checked"/>
Tp :<input type="radio" value="1" name="r" />
</td>
<%} else if(type==1)  { %>
<td>
Cour : <input type="radio" value="0" name="r" />
Tp :<input type="radio" value="1" name="r" checked="checked" />
</td>

<%} else { %>
<td>
Cour : <input type="radio" value="0" name="r" />
Tp :<input type="radio" value="1" name="r" />
</td>

<%} %>
</tr>
<tr>
<td align="center" colspan="2">
<input type="submit" value="ok " />
<input type="reset" value="Annuler" />
</td>
</tr>
</table>

</form>

</body>
</html>
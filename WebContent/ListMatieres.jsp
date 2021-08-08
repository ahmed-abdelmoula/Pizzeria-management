<%@page import="modele.Matiere"%>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<%
ArrayList<Matiere>ltrait=(ArrayList<Matiere>)session.getAttribute("listOfMatiere");


%>
<body>
<table border="1">

<tr>
<th>Code </th><th>Libelle </th><th>Coeificient </th><th>Type </th><th>Action</th>
</tr>
<%if(ltrait.size()!=0||ltrait!=null)
{for(int i=0;i<ltrait.size();i++){ %>
<tr>
<td><%=ltrait.get(i).getCode() %> </td>
<td><%=ltrait.get(i).getLibelle() %> </td>
<td><%=ltrait.get(i).getCoeificient() %> </td>
<% if(ltrait.get(i).getType()==1){ %>
<td>TP<input type="radio"   checked="checked"  value="" disabled="disabled"/>
Cour<input type="radio"  value=""  disabled="disabled"/></td>
<% } else { %>
<td>TP<input type="radio"     value="" disabled="disabled" />
Cour<input type="radio" checked="checked" value="" disabled="disabled"/></td>
<%} %>
<!-- <td><input type="radio" checked="checked" value="" /></td>
<td>	<input type="radio" value="" /> </td> 
 -->
<td><a href='FicheMatiereAction?mode=Edition&id=<%=ltrait.get(i).getCode() %>'>Editer</a>
<a href='FicheMatiereAction?mode=Suppression&id=<%=ltrait.get(i).getCode()%>'>Supprimer</a>


</td>
</tr><% }}%>
<tr>
<td colspan=4><center><a href="FicheEtudiant.jsp">Ajouter </a></center></td><td colspan=1><center><a href='ListNote.jsp'>AjoutNote</a></center> </td>

</tr>
</table>


</body>
</html>
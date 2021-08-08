<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="modele.NoteCour"%>
<%@page import="modele.Matiere"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>

</head>
<body>
	<style>
.unselectable {
	background-color: #ddd;
	cursor: not-allowed;
}
</style>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
    body {
        color: #566787;
		background: #f5f5f5;
		font-family: 'Varela Round', sans-serif;
		font-size: 13px;
	}
	.table-wrapper {
        background: #fff;
        padding: 20px 25px;
        margin: 30px auto;
		border-radius: 3px;
        box-shadow: 0 1px 1px rgba(0,0,0,.05);
    }
	.table-wrapper .btn {
		float: right;
		color: #333;
    	background-color: #fff;
		border-radius: 3px;
		border: none;
		outline: none !important;
		margin-left: 10px;
	}
	.table-wrapper .btn:hover {
        color: #333;
		background: #f2f2f2;
	}
	.table-wrapper .btn.btn-primary {
		color: #fff;
		background: #03A9F4;
	}
	.table-wrapper .btn.btn-primary:hover {
		background: #03a3e7;
	}
	.table-title .btn {		
		font-size: 13px;
		border: none;
	}
	.table-title .btn i {
		float: left;
		font-size: 21px;
		margin-right: 5px;
	}
	.table-title .btn span {
		float: left;
		margin-top: 2px;
	}
	.table-title {
		color: #fff;
		background: #4b5366;		
		padding: 16px 25px;
		margin: -20px -25px 10px;
		border-radius: 3px 3px 0 0;
    }
    .table-title h2 {
		margin: 5px 0 0;
		font-size: 24px;
	}
	.show-entries select.form-control {        
        width: 60px;
		margin: 0 5px;
	}
	.table-filter .filter-group {
        float: right;
		margin-left: 15px;
    }
	.table-filter input, .table-filter select {
		height: 34px;
		border-radius: 3px;
		border-color: #ddd;
        box-shadow: none;
	}
	.table-filter {
		padding: 5px 0 15px;
		border-bottom: 1px solid #e9e9e9;
		margin-bottom: 5px;
	}
	.table-filter .btn {
		height: 34px;
	}
	.table-filter label {
		font-weight: normal;
		margin-left: 10px;
	}
	.table-filter select, .table-filter input {
		display: inline-block;
		margin-left: 5px;
	}
	.table-filter input {
		width: 200px;
		display: inline-block;
	}
	.filter-group select.form-control {
		width: 110px;
	}
	.filter-icon {
		float: right;
		margin-top: 7px;
	}
	.filter-icon i {
		font-size: 18px;
		opacity: 0.7;
	}	
    table.table tr th, table.table tr td {
        border-color: #e9e9e9;
		padding: 12px 15px;
		vertical-align: middle;
    }
	table.table tr th:first-child {
		width: 60px;
	}
	table.table tr th:last-child {
		width: 80px;
	}
    table.table-striped tbody tr:nth-of-type(odd) {
    	background-color: #fcfcfc;
	}
	table.table-striped.table-hover tbody tr:hover {
		background: #f5f5f5;
	}
    table.table th i {
        font-size: 13px;
        margin: 0 5px;
        cursor: pointer;
    }	
	table.table td a {
		font-weight: bold;
		color: #566787;
		display: inline-block;
		text-decoration: none;
	}
	table.table td a:hover {
		color: #2196F3;
	}
	table.table td a.view {        
		width: 30px;
		height: 30px;
		color: #2196F3;
		border: 2px solid;
		border-radius: 30px;
		text-align: center;
    }
    table.table td a.view i {
        font-size: 22px;
		margin: 2px 0 0 1px;
    }   
	table.table .avatar {
		border-radius: 50%;
		vertical-align: middle;
		margin-right: 10px;
	}
	.status {
		font-size: 30px;
		margin: 2px 2px 0 0;
		display: inline-block;
		vertical-align: middle;
		line-height: 10px;
	}
    .text-success {
        color: #10c469;
    }
    .text-info {
        color: #62c9e8;
    }
    .text-warning {
        color: #FFC107;
    }
    .text-danger {
        color: #ff5b5b;
    }
    .pagination {
        float: right;
        margin: 0 0 5px;
    }
    .pagination li a {
        border: none;
        font-size: 13px;
        min-width: 30px;
        min-height: 30px;
        color: #999;
        margin: 0 2px;
        line-height: 30px;
        border-radius: 2px !important;
        text-align: center;
        padding: 0 6px;
    }
    .pagination li a:hover {
        color: #666;
    }	
    .pagination li.active a {
        background: #03A9F4;
    }
    .pagination li.active a:hover {        
        background: #0397d6;
    }
	.pagination li.disabled i {
        color: #ccc;
    }
    .pagination li i {
        font-size: 16px;
        padding-top: 6px
    }
    .hint-text {
        float: left;
        margin-top: 10px;
        font-size: 13px;
    }    
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('[data-toggle="tooltip"]').tooltip();
});
</script>

	<%
		ArrayList<NoteCour> ltrait = (ArrayList<NoteCour>) session.getAttribute("listOfnote");
		ArrayList<Matiere> wik = (ArrayList<Matiere>) session.getAttribute("listOfMatiere");
	%>
	
 <div class="container">
        <div class="table-wrapper">
            <div class="table-title">
                <div class="row">
                    <div class="col-sm-6">
						<h2>Liste Des  <b>Notes</b></h2>
					</div>
					<div class="col-sm-6">
						<a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Employee</span></a>
						<a href="#deleteEmployeeModal" class="btn btn-danger" data-toggle="modal"><i class="material-icons">&#xE15C;</i> <span>Delete</span></a>						
					</div>
                </div>
            </div>
 <table class="table table-striped table-hover">
                <thead>
		<tr>
			<th>Matiere</th>
			<th>TP</th>
			<th>DC</th>
			<th>DS</th>
			<th>AN</th>
			<th>Moyenne</th>
			<th>Action</th>
		</tr>
		 </thead>
                <tbody>
		<%
			if (wik.size() != 0 || wik != null) {
				for (int j = 0; j < wik.size(); j++) {
		%>
		<tr>
			<td><%=wik.get(j).getLibelle()%></td>

			<%
				if (ltrait.size() != 0) {

							int k = 0;
							boolean b = false;
							while ((k < ltrait.size()) && (b == false)) {
								if (wik.get(j).getCode().equals(ltrait.get(k).getCodeM())) {
									//System.out.println("*****");

									//System.out.println("Matiere"+wik.get(j).getCode());
									//System.out.println("NoteMatiere"+ltrait.get(k).getCodeM());

									b = true;
								} else

									k++;
							}
							//System.out.println("K"+k);
							if (k >= ltrait.size()) {
								if ((wik.get(j).getType() == 0)) {
			%>

			<td class='unselectable'></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>

			<%
				} else {
			%>
			<td>0</td>
			<td class='unselectable'></td>
			<td class='unselectable'></td>
			<td class='unselectable'></td>
			<td></td>

			<%
				}
							} else {

								if ((wik.get(j).getType() == 0)) {
			%>
			<td class='unselectable'></td>
			<td><%=ltrait.get(k).getNoteds()%></td>
			<td><%=ltrait.get(k).getNoteExamin()%></td>
			<td><%=ltrait.get(k).getAutrenote()%></td>
			<td><%=wik.get(j).getMoyenne() %></td>


			<%
				} else { 
			%>
			<td><%=ltrait.get(k).getNoteTp()%></td>
			<td class='unselectable'></td>
			<td class='unselectable'></td>
			<td class='unselectable'></td>
			<td><%=ltrait.get(k).getNoteTp()%></td>

			<%
				}
							}
						}
			%>







			<td>
				<form action="FicheMatiereAction" method="get">
					<input type="checkbox" name="matiere"
						value="<%=wik.get(j).getCode()%>" onChange="this.form.submit()">
					<input type="hidden" name="mode" value="Nature" /> <input
						type="hidden" name="Type" value="<%=wik.get(j).getType()%>" />
				</form>
			</td>


		</tr>
		<%
			}
			}
		%>
	</table>
	<%
		if (request.getParameter("Type") == null || request.getParameter("Type").equals("")) {
	%>
	<div hidden>

		<form action="" method="post">

			Note : <input type="text" name="note" /> tp<input type="radio"
				name="r" /> an<input type="radio" name="r" /> ds<input
				type="radio" name="r" /> dc<input type="radio" name="r" /> <input
				type="submit" value="ajouter">
		</form>

	</div>

	<%
		} else {
	%>

	<div>

		<form action="FicheMatiereAction" method="get">

			Note : <input type="text" name="note" /> <input type="hidden"
				name="mode" value="NoteA">
			<%
				if (Integer.parseInt(request.getParameter("Type")) == 0) {
			%>

			TP<input type="radio" name="r" disabled="disabled" /> AN<input
				type="radio" name="r" value="autrenote" /> DS<input type="radio"
				name="r" value="noteds" /> DC<input type="radio" name="r"
				value="noteExamin" /> <input type="hidden" name="code"
				value="<%=request.getParameter("matiere")%>">
			<%
				

					} else {
			%>
			TP<input type="radio" name="r" value="notetp"/> AN<input type="radio" name="r"
				disabled="disabled" /> DS<input type="radio" name="r"
				disabled="disabled" /> DC<input type="radio" name="r"
				disabled="disabled" /><input type="hidden" name="code"
				value="<%=request.getParameter("matiere")%>">
			<%
				}
			%>
			<input type="submit" value="Ajouter">
		</form>

	</div>

	<%
		}
	%>


	<table>
		<tr>

		</tr>

	</table>
</body>
</html>
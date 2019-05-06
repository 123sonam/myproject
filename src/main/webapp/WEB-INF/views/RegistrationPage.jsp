<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
    <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
        <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <title>Registration</title>
            
            
        </head>
        <body>
            <form:form commandName="userDto" action="register" method="POST">
                <table align="center">
                  
                    <tr>
                    <form:hidden path="id" />
                        <td>
                            <form:label path="name">Name</form:label>
                        </td>
                        <td>
                         <form:input path="name" name="name" id="name" />
                        </td>
                    </tr>
                                    <tr>
                        <td>
                            <form:label path="email">Email</form:label>
                        </td>
                        <td>
                            <form:input path="email" name="email" id="email" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <form:label path="password">Password</form:label>
                        </td>
                        <td>
                            <form:input path="password" name="password" id="password" />
                        </td>
                    </tr>
                        
                   
                   
                        <td></td>
                      <tr>
			<td colspan="2"><input type="submit"
				class="blue-button" /></td>
		</tr>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td></td>
                        <td><a href="index.jsp">Home</a>
                        </td>
                    </tr>
                </table>
            </form:form>
             <table align="center">
                <tr>
                    <td style="font-style: italic; color: red;">${message}</td>
                </tr>
            </table>
        </body>
        </html>
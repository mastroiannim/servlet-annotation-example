<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.SQLException" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        try (Connection connection = DriverManager.getConnection("jdbc:ucanaccess://" + request.getServletContext().getRealPath("/") + "WEB-INF/Vuoto.accdb")) {
            Statement st = connection.createStatement();
            String name = request.getParameter("name"), primary = request.getParameter("primary");
            boolean errore = false;
            if(name != null && name.trim() != "" && primary != null && primary.trim() != ""){ //TODO: Controllare lunghezza
                String tmp = request.getParameter("field"), instruction = "CREATE TABLE " + name + " (" +  primary + " COUNTER PRIMARY KEY";
                int field = Integer.parseInt(tmp);
                for(int i=0;i<field;i++){
                    String fieldName = request.getParameter("field" + i + "name"), fieldType = request.getParameter("field" + i + "type");
                    instruction += (", " + fieldName);
                    if(fieldName != null && fieldName.trim() != "" && fieldType != null && fieldType.trim() != ""){ //TODO: Controllare lunghezza
                        switch (fieldType){
                            case "datetime":
                                instruction += " datetime";
                                break;
                            case "numeric":
                                instruction += " numeric(12,3)";
                                break;
                            default: //type: text
                                instruction += " text(400)";
                        }
                    }else{
                        errore = true; //TODO gestire questi errori
                    }
                }
                instruction += ")";
                
                if(errore){
                    out.println("Errore non gestito 1");
                }else{
                    st.execute(instruction);
                    out.println("Tabella creata con successo");
                }
            }else{
                out.println("Errore non gestito 2");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    %>
</body>
</html>

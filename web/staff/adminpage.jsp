<%-- 
    Document   : adminpage
    Created on : Jun 6, 2022, 4:26:17 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="css/staff.css"/>
    </head>
    <body>
        <div class="container-fluid total">
            <c:set var="staff" value="${sessionScope.staff}"></c:set>
            <header>
                <h1>
                    Admin Page
                </h1>

                <h1>
                    Hello
                    ${sessionScope.staff.first_name}
                </h1>
            </header>

            <div class="row content">

                <div class="submenu col-md-2">
                    <ul>
                        <li>
                            <a href ="manage?model=movies">Parent</a>
                        </li>
                        <li>
                            <a href ="manage?model=cinemas">Teacher</a>
                        </li>
                        <li>
                            <a href ="manage?model=screens">Class</a>
                        </li>
                        <li>
                            <a href ="manage?model=schedules">Attendance</a>
                        </li>
                        <li>
                            <a href ="manage?model=screen_details">Schedule</a>
                        </li>
                        <li>
                            <a href ="manage?model=users">User</a> 
                        </li>
                    </ul>
                </div>

                <div class="content">

                </div>

            </div>
            <footer>

            </footer>
        </div>

        <!--JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    </body>
</html>

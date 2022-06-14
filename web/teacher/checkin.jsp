<%-- 
    Document   : studentlist
    Created on : Jun 8, 2022, 11:16:04 AM
    Author     : Windows 10 TIMT
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <link rel="stylesheet" href="teacher/css/teacherhome.css">
        <script src="https://kit.fontawesome.com/67b5c45612.js" crossorigin="anonymous"></script>
        <script src="teacher/js/teacherhome.js"></script>

    </head>

    <body>
        <div class="wrapper">
            <div class="home">
                <div class="left-side-menu">
                    <div class="vertical-menu">
                        <div class="user-welcome">
                            <img class="user-img" src="teacher/img/userImg/dummy-user-img.png" style="width: 80px; height: 80px;" alt="">
                            <p>Harry Porter</p>
                        </div>
                        <div class="menu-item-container">
                            <ul class="item-lists">
                                <li class="menu-item current">
                                    <a href="loadteacherhome">Home</a>
                                </li>
                                <li class="menu-item">
                                    <a href="schedule.html">View schedule</a>
                                </li>
                            </ul>
                        </div>
                        <div class="log-out">
                            <a href="login?action=logout">Log out</a>
                        </div>
                    </div>
                </div>

                <div class="right-side">
                    <div class="page-content">
                        <div class="header-container">
                            <div class="header-intro">
                                <div class="class-section">
                                    <h1>SE1608</h1>
                                </div>
                                <div class="search-section">
                                    <form>
                                        <input type="search" placeholder="Search...">
                                        <button type="submit">Search</button>
                                    </form>
                                </div>
                            </div>
                            <div class="header-filter">
                                <div class="left-modes">
                                    <div class="check-section">
                                        <div class="check-in check-item current1">
                                            <a href="attendance">Check in</a>
                                        </div>
                                        <div class="check-out check-item">
                                            <a href="checkout">Check out</a>
                                        </div>
                                    </div>
                                    <div class="student-filter">
                                        <div class="filter-item">
                                            <a href="attendance?action=all">All</a>
                                        </div>
                                        <div class="filter-item">
                                            <a href="attendance?action=absent">Absent only</a>
                                        </div>
                                    </div>
                                </div>
                                <div class="mode-menu right-mode">
                                    <a href="loadteacherhome" class="window-mode mode-item">
                                        <img src="teacher/img/icon/372745201553239377-128.png" style="width: 33px; height: 35px; line-height: 32px;" alt="">
                                    </a>
                                    <a href="#" class="list-mode mode-item current1" style="text-decoration: none;">
                                        <i class="fa-solid fa-bars" style="font-size: 25px; line-height: 35px;"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="body-container">
                            <c:if test="${sessionScope.present_kids == null}">
                                <form action="attendance" method="POST">
                                    <div class="list-students-ver2">
                                        <%
                                        int count = 0;
                                        %>
                                        <c:forEach var="s" items="${requestScope.listStudent}">
                                            <input type="hidden" name="action" value="check_in"/>
                                            <div class="student-infor">
                                                <%
                                                    count++;
                                                %>
                                                <p><%=count%></p>
                                                <div class="img-section">
                                                    <img src="teacher/img/userImg/download.png" alt="">
                                                </div>
                                                <a href="kidprofile?kid_id=${s.kinder_id}">${s.first_name} ${s.last_name}</a>
                                                <div class="check-attendance">
                                                    <input type="radio" name="checkAttendence${s.kinder_id}" value="1"  placeholder="Attend"> Attend
                                                    <input type="radio" name="checkAttendence${s.kinder_id}" value="0" checked placeholder="Absent"> Absent
                                                </div>
                                            </div>
                                        </c:forEach>
                                        <div class="submit-btn">
                                            <input type="submit" name="Save" value="Save"/>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                            <c:if test="${sessionScope.present_kids != null}">
                                <form action="attendance" method="POST">
                                    <div class="list-students-ver2">
                                        <%
                                        int count = 0;
                                        %>
                                        <c:forEach var="s" items="${requestScope.listStudent}">
                                            <input type="hidden" name="action" value="check_in"/>
                                            <div class="student-infor">
                                                <%
                                                    count++;
                                                %>
                                                <p><%=count%></p>
                                                <div class="img-section">
                                                    <img src="teacher/img/userImg/download.png" alt="">
                                                </div>
                                                <a href="kidprofile?kid_id=${s.kinder_id}">${s.first_name} ${s.last_name}</a>
                                                <c:forEach var="ps" items="${sessionScope.present_kids}">
                                                    <c:if test="${s.kinder_id == ps.student_id}">
                                                        <div class="check-attendance">
                                                            <c:set value="${ps.status}" var="status"/>
                                                            <input type="radio" name="checkAttendence${s.kinder_id}" value="1" 
                                                                   ${status==1?"checked":""}/> Attend
                                                            <input type="radio" name="checkAttendence${s.kinder_id}" value="0" 
                                                                   ${status==0?"checked":""}/> Absent
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </c:forEach>

                                        <div class="submit-btn">
                                            <input type="submit" name="Save" value="Save"/>
                                        </div>
                                    </div>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>

</html>

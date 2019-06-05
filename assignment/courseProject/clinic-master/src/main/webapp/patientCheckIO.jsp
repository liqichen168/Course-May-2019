<%@ page import="com.cm.model.User" %>
<%@ page import="java.util.*" %>
<%@ page import="com.cm.dao.PatientCheckIODao" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Patients' checking in/out</title>
    <link rel="icon" href="css/favicon.ico">
    <link href="css/carousel.css" rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="css/jquery-accordion-menu.css" rel="stylesheet" type="text/css" />
    <link href="css/font-awesome.css" rel="stylesheet" type="text/css" />

    <script src="assets/js/jquery-1.11.2.min.js" type="text/javascript"></script>
    <script src="assets/js/jquery-accordion-menu.js" type="text/javascript"></script>
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<%--    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
<%--    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>--%>
    <base href="<%=basePath%>">
    <title>PatientCheckin/out</title>

    <script type="text/javascript">

        $(function(){
            //顶部导航切换
            $("#demo-list li").click(function(){
                $("#demo-list li.active").removeClass("active")
                $(this).addClass("active");
            })
        })
    </script>

    <style type="text/css">

        .filterinput{
            background-color:rgba(249, 244, 244, 0);
            border-radius:15px;
            width:90%;
            height:30px;
            border:thin solid #FFF;
            text-indent:0.5em;
            font-weight:bold;
            color:#FFF;
        }
        #demo-list a{
            overflow:hidden;
            text-overflow:ellipsis;
            -o-text-overflow:ellipsis;
            white-space:nowrap;
            width:100%;
        }

        body{
            background-color: white;
            background-size: 100% auto;
        }
        p{
            margin: 0;
            padding: 0;
            text-align: center;
        }
        h2{
            margin: 0;
            padding: 0;
            text-align: center;
            background-color: darkblue;
            color: white;
        }
        /*ul{*/
        /*    text-align: center;*/
        /*}*/
        /*li{*/
        /*    text-decoration: none;*/
        /*    list-style: none;*/
        /*    display: inline;*/
        /*    margin: 1%;*/
        /*    padding: 1%;*/
        /*}*/

    </style>
</head>
<body>

<div id="head-part" style=" height: 310px;width: 100%;">
    <div class="navbar-wrapper">
        <div class="container">
            <nav class="navbar navbar-inverse navbar-static-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="viewAdmin.jsp">Welcome, ${admin.getUserName()}</a>
                    </div>
                    <div id="navbar" class="navbar-collapse collapse">
                        <ul class="nav navbar-nav">
                            <li><a href="">Home</a></li>
                            <li><a href="">About</a></li>
                            <li><a href="">Contact</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">service <span class="caret"></span></a>
                                <ul class="dropdown-menu">
                                    <li><a href="addUserPatient.html">Registration for Patient</a></li>
                                    <li><a href="addUserMedic.jsp">Registration for Medical Staff</a></li>
                                </ul>
                            </li>
                            <li><a href="Logout">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

        </div>
    </div>

    <div id="myCarousel" class="carousel slide" data-ride="carousel" style="height: 300px;" >
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox" style="height: 300px;">
            <div class="item active">
                <img class="first-slide" src="img/photo.jpg" alt="First slide">
                <div class="carousel-caption" style="top:20%">
                    <p>If you want to add new patients, please click this button</p>
                    <p><a class="btn btn-lg btn-primary" href="addUserPatient.html" role="button">add patients</a></p>
                </div>
            </div>
            <div class="item">
                <img class="second-slide" src="img/01.jpg" alt="second slide">
                <div class="carousel-caption" style="top:20%">
                    <p>If you want to add new staff, please click this button</p>
                    <p><a class="btn btn-lg btn-primary" href="addUserMedic.jsp" role="button">add staff</a></p>
                </div>
            </div>
            <div class="item">
                <img class="third-slide" src="img/login.jpg" alt="Third slide">
                <div class="carousel-caption" style="top:20%">
                    <p>If you want to add new admin, please click this button</p>
                    <p><a class="btn btn-lg btn-primary" href="addAdmin.html" role="button">add admin</a></p>
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev" style="height: 300px">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next" style="height: 300px;">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div><!-- /.carousel -->

</div>


<div id="body-left" style="width: 30%;height:300px;float: left;">
    <div class="content">

        <div id="jquery-accordion-menu" class="jquery-accordion-menu black">
            <div class="jquery-accordion-menu-header" id="form" style="padding-top: 10px;"></div>
            <ul id="demo-list">

                <li class="active"><a href="viewAdmin.jsp"><i class="fa fa-home"></i>Home </a></li>
                <li><a href="javascript:void(0);"><i class="fa fa-glass"></i>Events </a></li>
                <li><a href="javascript:void(0);"><i class="fa fa-file-image-o"></i>Message </a><span class="jquery-accordion-menu-label">
				12 </span></li>
                <li><a href="javascript:void(0);"><i class="fa fa-cog"></i>Services </a>
                    <ul class="submenu">
                        <li><a href="javascript:void(0);">Medical Assistant </a></li>
                        <li><a href="doctorAppointment.jsp">doctor appointment </a></li>
                        <li><a href="javascript:void(0);">register users </a>
                            <ul class="submenu">
                                <li><a href="addAdmin.html">add admin </a></li>
                                <li><a href="addUserPatient.html">addUserPatient </a></li>
                                <li><a href="addUserMedic.jsp">add medical staff </a></li>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li><a href="javascript:void(0);"><i class="fa fa-user"></i>About </a></li>
                <li><a href="javascript:void(0);"><i class="fa fa-envelope"></i>Contact </a></li>

            </ul>
        </div>
    </div>
</div>
<div id="body-center" style="width: 60%;margin: auto;float: left;padding-left: 2%">
    <h3 class="display-4" style="margin: 0;padding: 0;text-align: center;background-color: #1b809e;color: white;height: 10%;line-height: 300%">Patients' List</h3>
    <h4 class="display-5" style="margin:2px;"><c:out value="There is:   ${positionNum}   position left"></c:out></h4>
    <table class="table table-bordered" style="background-color: white" >
        <thead class="thead-light">
        <tr>
            <td>Patient ID</td>
            <td>Patient Name</td>
            <td>Operation</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${userList}" >
            <tr>
                <td><c:out value="${user.getUserID()}"/></td>
                <td><c:out value="${user.getUserName()}"/></td>

                <td>
                    <form action="hospitalBedcheck" method="post" style="margin: auto">
                        <input type="hidden" name="userid" value="${user.getUserID()}"/>
                        <input type="hidden" name="checkout" value="false"/>
                        <input type="hidden" name="positionNum" value="${positionNum}">
                        <c:if test="${full}">
                            <p>Positions are full</p>
                        </c:if>
                        <c:if test="${!full}">
                            <c:if test="${!user.isFlag()}">
                                <input type="submit" class="btn btn-primary" value="check in" />
                            </c:if>
                            <c:if test="${user.isFlag()}">
                                <p>checked in</p>
                            </c:if>
                        </c:if>


                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <c:if test="${bedList.size()!=0}">
    <h3 class="display-4" style="margin: 0;padding: 0;text-align: center;background-color: #1b809e;color: white;height: 10%;line-height: 300%">Patients' CheckIn List</h3>
        <table class="table table-bordered" style="background-color: white" >
            <thead class="thead-light">
            <tr>
                <td>Patient ID</td>
                <td>Patient Name</td>
                <td>Operation</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hospital" items="${bedList}">
                <tr>
                    <td><c:out value="${hospital.getUserID()}"/></td>
                    <td><c:out value="${hospital.getUsername()}"/></td>
                    <td>
                        <form action="hospitalBedcheck" method="post" style="margin: auto">
                            <input type="hidden" name="checkout" value="true"/>
                            <input type="hidden" name="userid" value="${hospital.getUserID()}"/>
                            <input type="hidden" name="positionNum" value="${positionNum}">
                            <input type="submit" class="btn btn-primary" value="check out"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>


<div id="foot-part" style="width: 100%;float: left">
    <hr class="featurette-divider" style="margin: 10px 0; ">
    <footer>
        <p style="height: 5%">&copy; Copyright © 2019 Fanghan Xu &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a></p>
    </footer>
</div>

<script type="text/javascript">
    (function($) {
        $.expr[":"].Contains = function(a, i, m) {
            return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
        };
        function filterList(header, list) {
            //@header 头部元素
            //@list 无需列表
            //创建一个搜素表单
            var form = $("<form>").attr({
                "class":"filterform",
                action:"#"
            }), input = $("<input>").attr({
                "class":"filterinput",
                type:"text"
            });
            $(form).append(input).appendTo(header);
            $(input).change(function() {
                var filter = $(this).val();
                if (filter) {
                    $matches = $(list).find("a:Contains(" + filter + ")").parent();
                    $("li", list).not($matches).slideUp();
                    $matches.slideDown();
                } else {
                    $(list).find("li").slideDown();
                }
                return false;
            }).keyup(function() {
                $(this).change();
            });



        }
        $(function() {
            filterList($("#form"), $("#demo-list"));
        });
    })(jQuery);
</script>

<script type="text/javascript">

    jQuery("#jquery-accordion-menu").jqueryAccordionMenu();

</script>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="dist/js/bootstrap.min.js"></script>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="assets/js/ie10-viewport-bug-workaround.js"></script>

</body>
</html>

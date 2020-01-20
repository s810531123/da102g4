<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script type="text/javascript" src="<%=request.getContextPath()%>/back-end/course/js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/index_login.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/sideBar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/back-end/course/js/backCourse.js"></script>
    <!-- sweet alert .css & .js cdn -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js" type="text/javascript"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/backgroundContext.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/navbar.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/allBody_FooterView.css">
    <style type="text/css">
    /*本地字體引入*/
    @font-face{
    font-family: Russo;
    src:url('fonts/RussoOne-Regular.ttf');
    unicode-range: U+00-024F;
    }
    @font-face{
    font-family: Noto;
    src:url('fonts/NotoSansTC-Medium.otf');
    unicode-range: U+4E00-9FFF;
    }
    </style>
</head>
<body>

 <div class="row">
        <nav id="main-nav" class="navbar navbar-expand-sm navbar-dark fixed-top ">
          <div class="container-fluid">
            <img src="<%=request.getContextPath()%>/images/icon.png" class="logo">
          <!--   <img src="images/magnifier.png" class="logo">  -->
            
            <a href="welcome.html" class="navbar-brand" id="mainlogo"></a>
            
            <div id="navbarCollapse" class="collapse navbar-collapse" >
              <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                  <a href="<%=request.getContextPath()%>/back-end/course/listAllCourse.jsp" class="nav-link alink"><i>Home</i></a>
                </li>
                <li class="nav-item">
                  <a href="#" class="nav-link alink"><img src=""></a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>

</body>
</html>
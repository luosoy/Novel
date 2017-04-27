<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="stc" value="${pageContext.request.contextPath}/assets" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1,user-scalable=no" />
        <meta http-equiv="Cache-Control" content="no-store" />
        <meta http-equiv="Pragma" content="no-cache" />
        <meta http-equiv="Expires" content="0" />
        <title><sitemesh:write property='title' /></title>
        <link href="${stc}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${stc}/lib/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>
        <script type="text/javascript">
            var SYS = {
                ctx: '${ctx}',
                path: '${ctx}/web/',
                stc: '${stc}'
            };
        </script>
        <!--[if lt IE 8]><script src="${stc}/lib/json/json3.min.js" type="text/javascript"></script><![endif]-->
        <script src="${stc}/lib/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="${stc}/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <sitemesh:write property='head'/>
    </head>
    <body>
        <sitemesh:write property='body'/>
    </body>
</html>
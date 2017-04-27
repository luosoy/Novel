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
        <title><sitemesh:write property='title' /> - ${title}</title>
        <meta name="keywords" content="${keywords}">
        <meta name="description" content="${description}">
<!--        <link href="${stc}/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${stc}/lib/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet"/>-->
        <link href="${stc}/css/common.css" rel="stylesheet"/>
        <script type="text/javascript">
            var SYS = {
                ctx: '${ctx}',
                path: '${ctx}/novel/',
                stc: '${stc}'
            };
        </script>
        <!--[if lt IE 8]><script src="${stc}/lib/json/json3.min.js" type="text/javascript"></script><![endif]-->
        <script src="${stc}/lib/jquery/jquery.min.js" type="text/javascript"></script>
        <!--<script src="${stc}/lib/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>-->
        <script src="${stc}/js/common.js" type="text/javascript"></script>
    <sitemesh:write property='head'/>
</head>
<body>
    <div class="header">
        <div class="wrap">
            <div class="logo">
                <a href="${url}" title="${name}">${name}<em>${url}</em></a>
            </div>
            <div class="search">
                <div>
                    <input id="search" type="search" class="text" placeholder="小说名称、作者" value="${search}">
                    <input id="searchSubmit" type="submit" class="btn" value="搜 索">
                </div>
            </div>
        </div>
    </div>
    <div class="nav">
        <ul>
            <li><a href="${ctx}/novel/index">首页</a></li>
            <li><a href="${ctx}/novel/xhxs/index">玄幻小说</a></li>
            <li><a href="${ctx}/novel/xzxs/index">修真小说</a></li>
            <li><a href="${ctx}/novel/dsxs/index">都市小说</a></li>
            <li><a href="${ctx}/novel/cyxs/index">穿越小说</a></li>
            <li><a href="${ctx}/novel/wyxs/index">网游小说</a></li>
            <li><a href="${ctx}/novel/khxs/index">科幻小说</a></li>
            <li><a href="${ctx}/novel/qtxs/index">其他小说</a></li>
            <li><a href="${ctx}/novel/phbd/index">排行榜单</a></li>
            <li><a href="${ctx}/novel/wb/index">完本小说</a></li>
        </ul>
    </div>
    <div class="wrap">
        <sitemesh:write property='body'/>
    </div>
    <div class="footer">
        <div class="link">${name}友情连接：
            <!--            <a href="http://www.biqukan.com/16_16759/" target="_blank">主神崛起</a>
                        <a href="http://www.biqukan.com/22_22429/" target="_blank">修真百年归来</a>
                        <a href="http://www.biqukan.com/28_28141/" target="_blank">永恒国度</a>
                        <a href="http://www.biqukan.com/21_21856/" target="_blank">都市超级医圣</a>
                        <a href="http://www.biqukan.com/21_21150/" target="_blank">火影之卡皇</a>
                        <a href="http://www.biqukan.com/16_16635/" target="_blank">限制级巨星</a>
                        <a href="http://www.biqukan.com/21_21143/" target="_blank">天神诀</a>
                        <a href="http://www.biqukan.com/16_16049/" target="_blank">极品美女爱上我</a>
                        <a href="http://www.biqukan.com/0_279/" target="_blank">恶魔囚笼</a>
                        <a href="http://www.biqukan.com/2_2113/" target="_blank">武神空间</a>
                        <a href="http://www.biqukan.com/16_16789/" target="_blank">奋斗在红楼</a>
                        <a href="http://www.biqukan.com/16_16673/" target="_blank">最强狂暴升级</a>
                        <a href="http://www.biqukan.com/0_681/" target="_blank">终极教官</a>
                        <a href="http://www.biqukan.com/1_1097/" target="_blank">逍遥小镇长</a>
                        <a href="http://www.biqukan.com/xiaoshuodaquan/" target="_blank">小说大全</a>
                        <a href="http://www.biqukan.com/1_1468/" target="_blank">寻情仙使</a>
                        <a href="http://www.biqukan.com/0_426/" target="_blank">剑主苍穹</a>
                        <a href="http://www.biqukan.com/1_1267/" target="_blank">孺子帝</a>
                        <a href="http://www.biqukan.com/0_184/" target="_blank">灭世魔帝</a>
                        <a href="http://www.biqukan.com/0_200/" target="_blank">史上最强师兄</a>
                        <a href="http://www.biqukan.com/0_996/" target="_blank">侠行天下</a>
                        <a href="http://www.biqukan.com/1_1421/" target="_blank">纨绔邪皇</a>
                        <a href="http://www.biqukan.com/0_295/" target="_blank">我家萝莉是大明星</a>
                        <a href="http://www.biqukan.com/0_784/" target="_blank">红色苏联</a>
                        <a href="http://www.biqukan.com/0_178/" target="_blank">英雄联盟之七百年后</a>
                        <a href="http://www.biqukan.com/1_1424/" target="_blank">贩妖记</a>-->
            <a href="http://www.touxiang.la/" target="_blank">偷香小说网</a> 
            <a href="http://www.3zm.net/" target="_blank">三掌门小说网</a>
            <a href="http://www.biqukan.com/" target="_blank">笔趣看</a>
            <a href="http://www.biqugex.com/" target="_blank">笔趣阁</a>
            <a href="http://www.wxguan.com/" target="_blank">文学馆</a>
            <a href="http://www.ddbiquge.com/" target="_blank">不灭龙帝</a>
            <a href="http://www.dengbi.cc/" target="_blank">灯笔小说网</a>
            <a href="http://www.09xs.com/" target="_blank">书阅屋</a>
            <a href="http://www.bqg5200.com/" target="_blank">笔趣阁5200</a>
            <a href="http://www.kushubao.com/" target="_blank">书旗小说</a>
            <a href="http://www.bxwx.us/" target="_blank">笔下文学</a>
            <a href="http://www.kong5.com/" target="_blank">悟空追书</a>
        </div><hr>
        <p>本站所有小说为转载作品，所有章节均由网友上传，转载至本站只是为了宣传本书让更多读者欣赏。</p>
        <p>${copyright}</p>
        <div style="display:none">
            <script src="http://s11.cnzz.com/z_stat.php?id=1260938422&amp;web_id=1260938422" language="JavaScript"></script>
            <script src="http://c.cnzz.com/core.php?web_id=1260938422&amp;t=z" charset="utf-8" type="text/javascript"></script>
            <a href="http://www.cnzz.com/stat/website.php?web_id=1260938422" target="_blank" title="站长统计">站长统计</a>
        </div>
    </div>
</body>
</html>
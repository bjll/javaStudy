<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
	HttpSession Session = request.getSession();
	String id = String.valueOf(Session.getAttribute("ID"));
	
	String account = (String)Session.getAttribute("ACCOUNT"); 
	String guide = (String)Session.getAttribute("guide"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

    <title></title>

    <style type="text/css">

    .outer{

        width: 100px;

        height:200px;

        border:2px solid red;

    }

    .inner{

        width:100%;

        height:100%;

        /*border:1px solid #000;*/

    }

    .outer:hover{

        cursor: s-resize;

    }

    .inner:hover{

        cursor: default;         

    }

    </style>

</head>

<body>

<div id="moveBarBox" class="outer"  onmousedown="startDrag()">

    <div class="inner" >

    </div>

</div>

<script type="text/javascript">

    var finalheight = 200; //最后的高度

    var he = 200; //初始高度
    var k=200;//初始宽度
    var dragable = false;//默认不可拖拽

    var oldY = '';//记录第一次的鼠标位置
    var oldX='';
    var startDrag = function(event){

        dragable = true;

        var e=event?event:window.event;

        oldY = e.pageY; //记录第一次的鼠标位置
        oldX=e.pageX;

    };

    var unDrop = function(){

        dragable = false;

        window.event? window.event.cancelBubble = true : e.stopPropagation();

    };

    var endDrop = function(){

        if(dragable){

            finalheight = he;

            dragable = false;            

        };

    };

    document.onmouseup=function(){

        endDrop();

    };

    document.onmousemove=function(event){
        if(dragable){

            var e=event?event:window.event;

            box = document.getElementById('moveBarBox');

            console.log(box);

            he =  e.pageY - oldY  + parseInt(finalheight);
            k= e.pageX - oldX  + parseInt(finalheight);
            //鼠标的位移 + div的最后高度 = div的新高度

            //向上拉  he =  oldY - e.pageY  + parseInt(finalheight); 

            //向下拉  he =  e.pageY - oldY  + parseInt(finalheight);

            if(dragable){

                if(he<120 || he==120){//div最低高度

                    box.style.height = '120px';he = '120px';
                   
                     return;

                }

                if(k<120 || k==120){//div最低高度
                    box.style.width = '120px';k = '120px';
                     return;

                }

                if(he>400 || he==400){//div最高高度

                    box.style.height = '400px';he = '400px';
                    return;

                }
              /*   if(k>400 || k==400){//div最高高度
                    box.style.width = '400px';k = '400px';
                    return;

                } */
                box.style.height = he + 'px'; 
                box.style.width = k + 'px'; 
            };

        };

    };

</script>
</body>
</html>
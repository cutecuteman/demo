<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="main" style="height: 400px"></div>

	<!-- ECharts单文件引入 -->
	<script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

	<!-- 模块加载器配置echarts和所需图表的路径 -->
	<script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var names=[];//列名 
                var datas=[];//显示的数据值
                
              //定义一个数组
                ////从controller中得到数据 ajax
                
                //$.get $.post $.ajax...

                $.get("${pageContext.request.contextPath}/UsersController?op=echarts",function(data,status){
                	console.log(data,status);
                	//得到的data就是返回的一个json格式的字符串,
                	data=JSON.parse(data);
                	//转成json对象
                	//遍历data...
                	$.each(data,function(index,p){
                		//将p中的pname psellcount 添加到数据中即可
                		names.push(p.userName);
                		datas.push(p.userState);
                	
                });
                
                var option = {
                    tooltip: {
                        show: true
                    },
                    legend: {
                        data:['等级']
                    },
                    xAxis : [
                        {
                          //  type : 'category',
//                            data : ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
                        	type : 'category',
                            data : names
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value'
                        }
                    ],
                    series : [
                        {
//                            "name":"销量",
//                           "type":"bar",
//                            "data":[5, 20, 40, 10, 10, 20]
                            "name":"等级",
                            "type":"bar",
                            "data":datas
                        }
                    ]
                };
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
                });
            }
        );
    </script>

</body>

</html>
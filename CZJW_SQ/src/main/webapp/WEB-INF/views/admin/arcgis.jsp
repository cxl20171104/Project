<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'arcgis.jsp' starting page</title>
    <script src='http://www.supermapol.com/resources/api/libs/SuperMap.Include.js'></script>
	<style>
			 .mapcontainer{
            height: 600px;
        }
	</style>

  </head>
  
  <body>
      <%--<div style="height: 50%">
      <a href="http://localhost:6080/arcgis/rest/services//SampleWorldCities/MapServer?f=jsapi" >打开</a>
      
      </div>
      --%>
         <script>
		    var url="http://www.supermapol.com";
		    var viewer=new SuperMap.Cloud.MapViewer(url,"map");
		   	 viewer.previewMapById(493);
			</script>
      		<div id = "map" class="mapcontainer"></div>
      
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
</head>
<body>
	<h2>Hello World!</h2>
	<input type="file" id="picture" name="picture" />

	<script type="text/javascript" src="js/jquery-2.1.4.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<script type="text/javascript">
		// 导入参赛者资料
		$("#picture").on("change", function() {
			// 文件格式验证
			var url = "test/uploadPicture";
			$.ajaxFileUpload({
				url : url,
				type : "post",
				secureuri : false, // 一般设置为false
				fileElementId : "picture", // 上传文件的id、name属性名
				dataType : "application/json", // 返回值类型，一般设置为json、application/json
				success : function(data, status) {
					console.info(data);
					window.location.reload();
				},
				error : function(data, status, e) {
					console.error(data);
				}
			});
		});
	</script>
</body>
</html>

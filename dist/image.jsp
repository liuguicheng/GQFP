<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"
	pageEncoding="UTF-8"%>
<%
	// 在内存中创建图象 
	int width = 68, height = 33;
	BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

	// 获取图形上下文 
	Graphics g = image.getGraphics();

	// 设定背景色 
	g.setColor(new Color(0xDCDCDC));
	g.fillRect(0, 0, width, height);

	//画边框 
	g.setColor(Color.black);
	g.drawRect(0, 0, width - 1, height - 1);

	// 取随机产生的认证码(4位数字) 
	//String rand = request.getParameter("rand");
	String rand = "";
	java.util.Random random = new java.util.Random();

	for (int i = 0; i < 2; i++) {
		rand += random.nextInt(9);
	}

	String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	char[] c = s.toCharArray();

	for (int i = 0; i < 2; i++) {
		rand += String.valueOf(c[random.nextInt(c.length)]);
	}

	//response.getWriter().print(rand);//输出
	
	// 将认证码存入SESSION 
	session.setAttribute("rand", rand);

	// 将认证码显示到图象中 
	g.setColor(Color.black);
	//Integer tempNumber = new Integer(rand);

	g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
	
	String numberStr = rand;//tempNumber.toString();
	String str = numberStr.substring(0, 1);
	g.drawString(str,0,25);

	str = numberStr.substring(1, 2);
	g.drawString(str,12,20);
	
	str = numberStr.substring(2, 3);
	g.drawString(str,24,25);

	str = numberStr.substring(3, 4);
	g.drawString(str,40,20);

	// 随机产生88个干扰点，使图象中的认证码不易被其它程序探测到 

	for (int i = 0; i < 20; i++) {
		int x = random.nextInt(width);
		int y = random.nextInt(height);
		g.drawOval(x, y, 0, 0);
	}

	// 图象生效 
	g.dispose();
	out.clear();
	out = pageContext.pushBody();
	// 输出图象到页面 
	ImageIO.write(image, "JPEG", response.getOutputStream());
%>
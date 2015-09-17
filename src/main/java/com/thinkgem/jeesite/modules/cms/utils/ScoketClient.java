package com.thinkgem.jeesite.modules.cms.utils;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class ScoketClient {

	/**
	 * 发送socket消息并接收返回的消息
	 * @param host	要连接的服务端对应的地址、IP地址
	 * @param port	要连接的服务端对应的监听端口
	 * @param msg	消息内容
	 * @return sb	返回的消息
	 * @throws Exception
	 */
	public StringBuffer send(String host, int port,String msg) throws Exception{
		// 与服务端建立连接
		Socket client = new Socket(host, port);
		// 建立连接后就可以往服务端写数据了
		Writer writer = new OutputStreamWriter(client.getOutputStream());
		// 给服务端发送消息
		writer.write(msg);
		// 消息结束标识
		writer.write("eof");
		writer.flush();
		// 写完以后进行读操作
		Reader reader = new InputStreamReader(client.getInputStream());
		char chars[] = new char[64];
		int len;
		int index;
		String temp;
		StringBuffer sb = new StringBuffer();
		while ((len = reader.read(chars)) != -1) {
			temp = new String(chars, 0, len);
			if ((index = temp.indexOf("eof")) != -1) {
				sb.append(temp.substring(0, index));
				break;
			}
			sb.append(new String(chars, 0, len));
		}
		//System.out.println("from server: " + sb);
		writer.close();
		reader.close();
		client.close();

		return sb;
	}
}

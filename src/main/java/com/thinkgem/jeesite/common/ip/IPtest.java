package com.thinkgem.jeesite.common.ip;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * 
 * 功能描述：测试
 *
 * @author  Administrator
 *
 * <p>修改历史：(修改人，修改时间，修改原因/内容)</p>
 */
public class IPtest{
	
	/**
	 * 
	 * 功能描述：测试IP归属地
	 *
	 * @author  Administrator
	 * <p>创建日期 ：2012-3-14 上午10:28:48</p>
	 *
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public void testIp(){
		
//		InetAddress addr = null;
//		try {
//			addr = InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		}
//		String ipStr = addr.getHostAddress().toString();//获得本机IP
//		String addressStr = addr.getHostName().toString();//获得本机名称
//		
//		System.out.println("本机IP地址： " + ipStr + " 本机名称： " + addressStr);
		
		String ipStr = this.getRealIp();
		System.out.println("本机IP地址： " + ipStr);
		
		//指定纯真数据库的文件名，所在文件夹
		IPSeeker ip=new IPSeeker();
		//测试IP 58.20.43.13
		System.out.println(ip.getIPLocation(ipStr).getCountry()+":"+ip.getIPLocation(ipStr).getArea());
	}
	
	/**
	 * 
	 * 功能描述：获取本地真实的IP地址
	 *
	 * @author  Administrator
	 * <p>创建日期 ：2012-3-14 上午10:23:01</p>
	 *
	 * @return
	 * @throws SocketException
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public static String getRealIp() {
		
		String localip = null;// 本地IP，如果没有配置外网IP则返回它
		String netip = null;// 外网IP

		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netip = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localip = ip.getHostAddress();
				}
			}
		}
	
		if (netip != null && !"".equals(netip)) {
			return netip;
		} else {
			return localip;
		}
	}



	public static void main(String[] args) {
		String ipStr = getRealIp();
		//String ipStr = "123.56.89.224";
		System.out.println("本机IP地址： " + ipStr);
		
		//指定纯真数据库的文件名，所在文件夹
		IPSeeker ip=new IPSeeker();
		//测试IP 58.20.43.13
		System.out.println(ip.getIPLocation(ipStr).getCountry()+":"+ip.getIPLocation(ipStr).getArea());

	}

}

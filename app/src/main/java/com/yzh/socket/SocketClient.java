package com.yzh.socket;


import com.libraryutils.NetworkUtils;
import com.yzh.base.App;
import com.yzh.base.BaseApplication;
import com.yzh.base.SocketAppPacket;
import com.yzh.requestrespose.request.LoginRequest;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

import de.greenrobot.event.EventBus;

/**
 * <p>
 * 采用MINA的通信客户端
 * </p>
 * 
 * @author <a href="mailto:hongchq@neusoft.com">ChengQian.Hong </a>
 * @version $Revision 1.1 $ 2012-8-7 下午3:49:15
 */
public class SocketClient {

	public SocketClient() {

	}

	private static SocketClient single = null;

	// 静态工厂方法
	public static SocketClient getInstance() {
		if (single == null) {
			single = new SocketClient();
		}
		return single;
	}

	private IoSession session = null;

	private void connectServer() throws Exception {

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				NioSocketConnector connector = new NioSocketConnector();
				// 设置链接超时时间
				connector.setConnectTimeoutMillis(30000);
				connector.getFilterChain().addLast("logger",
						new LoggingFilter());
				connector.getFilterChain().addLast(
						"codec",
						new ProtocolCodecFilter(
								new SocketProtocolCodecFactory())); // 设置编码过滤器
				connector.setHandler(new SocketClientHandlerListener());// 设置事件处理器
				ConnectFuture connect = connector
						.connect(new InetSocketAddress(App.SERVER_IP,
								App.SERVER_PORT));// 建立连接
				connect.awaitUninterruptibly();// 等待连接创建完成

				if (connect.isConnected()) {
					session = connect.getSession();
				}
				
			}
		}).start();

	}

	public void send(final int commandId, final byte[] commandData) {
		new Thread( new Runnable() {
			public void run() {
				if (session != null&&session.isConnected()==true&&session.isActive()&&session.isClosing()==false) {
					
					SocketAppPacket packet = new SocketAppPacket(session);
					packet.setCommandData(commandData);
					packet.setCommandId(commandId);
					session.write(packet);// 发送消息，中英文都有
				} else {
					try {
						
						
						if(!NetworkUtils.isNetworkConnected(BaseApplication.getInstance().getApplicationContext()))
						{
							EventBus.getDefault().post(new SocketMsg("网络连接超时"));
							return;
						}
						connectServer();
						while (session == null) {
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						SocketAppPacket packet = new SocketAppPacket(session);
						packet.setCommandData(commandData);
						packet.setCommandId(commandId);
						session.write(packet);// 发送消息，中英文都有
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						EventBus.getDefault().post(new SocketMsg("网络连接超时"));
						e1.printStackTrace();
					}
				}
			}
		}).start();
		
	}

	private static void main(String[] args) {
		LoginRequest.LoginRequestMessage.Builder requestMessage = LoginRequest.LoginRequestMessage
				.newBuilder();
		requestMessage.setPhone("jeff");
		requestMessage.setPassword("test");
		SocketClient.getInstance().send(0x0001,
				requestMessage.build().toByteArray());
	}

}
package yl.demo.pathHelper.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientHandler {
	private static SocketClientHandler mInstance = null;
	
	private Socket mSocket;
	private String mAddress;
	private int mPort;

	
	/**
	 * 获得实例，默认使用端口8888
	 * @param port		需要使用的端口；若要使用默认端口，port输入非正数
	 * @return
	 */
	public static synchronized SocketClientHandler getInstance(String address, int port) {
		if(mInstance == null) {
			if(port <= 0) {
				mInstance = new SocketClientHandler();
			}
			else {
				mInstance = new SocketClientHandler(address, port);
			}
		}
		return mInstance;
	}
	
	/**
	 * 获得实例，默认使用端口8888
	 * @param port		需要使用的端口；若要使用默认端口，port输入非正数
	 * @return
	 */
	public static synchronized SocketClientHandler getInstance() {
		if(mInstance == null) {
			mInstance = new SocketClientHandler();
		}
		return mInstance;
	}
	
	private SocketClientHandler() {
		mAddress = "1.1.1.1";
		mPort = 8888;
	}
	
	private SocketClientHandler(String address, int port) {
		mAddress = address;
		mPort = port;
	}
	
	public String getConfigVersion() {
		String version = null;
		try {
			mSocket = new Socket(mAddress, mPort);
			DataOutputStream dout = new DataOutputStream(mSocket.getOutputStream());
			DataInputStream din = new DataInputStream(mSocket.getInputStream()); 
			dout.writeUTF("version");
			dout.flush();
			version = din.readUTF();
			mSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return version;	
	}
	
	public String getConfigFile() {
		String file = null;
		try {
			mSocket = new Socket(mAddress, mPort);
			DataOutputStream dout = new DataOutputStream(mSocket.getOutputStream());
			DataInputStream din = new DataInputStream(mSocket.getInputStream()); 
			dout.writeUTF("configFile");
			dout.flush();
			file = din.readUTF();
			mSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file;	
	}
	
	public void sendMessage(String message) {
		try {
			mSocket = new Socket(mAddress, mPort);
			DataOutputStream dout = new DataOutputStream(mSocket.getOutputStream());
			dout.writeUTF(message);
			dout.flush();
			mSocket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void finish() {
		
	}
}

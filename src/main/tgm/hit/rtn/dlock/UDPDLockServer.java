package tgm.hit.rtn.dlock;

import java.net.ServerSocket;

public class UDPDLockServer implements Runnable {

	private DLock dLock;

	private GotLock gotLock;

	private PeerManager peerManager;

	public UDPDLockServer(GotLock gotLock, PeerManager manager, ServerSocket serverSocket) {

	}


	/**
	 * @see java.lang.Runnable#run()
	 */
	public void run() {

	}

}

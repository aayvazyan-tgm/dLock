package tgm.hit.rtn.dlock;

import java.io.Serializable;
import java.util.Objects;

public class Peer implements Serializable{
    private int port;
    private String host;

    public Peer(int port, String host) {
        this.port = port;
        this.host = host;
    }

    /**
     * Getter for property 'host'.
     *
     * @return Value for property 'host'.
     */
    public String getHost() {
        return host;
    }

    /**
     * Setter for property 'host'.
     *
     * @param host Value to set for property 'host'.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Getter for property 'port'.
     *
     * @return Value for property 'port'.
     */
    public int getPort() {
        return port;
    }

    /**
     * Setter for property 'port'.
     *
     * @param port Value to set for property 'port'.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Checks if both Peers contain the same information (Port and Host)
     * @param otherPeer the Peer to compare to.
     * @return true if they contain the same information, false otherwise
     */
    @Override
    public boolean equals(Object otherPeer) {
        if(otherPeer instanceof Peer) {
            Peer storedPeer=(Peer)otherPeer;
            if (this.host.equals(storedPeer.host)
                && this.port == storedPeer.port) return true;
        }
        return false;
    }

    /** Generates a hash code, using the port and host*/
    @Override
    public int hashCode() {
        return Objects.hash(this.port,this.host);
    }
}

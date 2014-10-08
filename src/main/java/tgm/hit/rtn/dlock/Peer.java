package tgm.hit.rtn.dlock;

public class Peer {
    private int port;
    private String Host;

    public Peer(int port, String host) {
        this.port = port;
        Host = host;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String host) {
        Host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

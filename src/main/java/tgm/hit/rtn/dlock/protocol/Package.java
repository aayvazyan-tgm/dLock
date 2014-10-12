package tgm.hit.rtn.dlock.protocol;

import tgm.hit.rtn.dlock.Peer;

import java.io.Serializable;

public class Package  implements Serializable {

	public PackageType type;

	public Peer sender;

	public String msg;

}

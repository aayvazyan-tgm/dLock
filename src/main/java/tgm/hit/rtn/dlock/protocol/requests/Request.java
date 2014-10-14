package tgm.hit.rtn.dlock.protocol.requests;

import tgm.hit.rtn.dlock.protocol.*;

import java.io.Serializable;

public class Request extends tgm.hit.rtn.dlock.protocol.Package implements Serializable {

	public PackageType type = PackageType.REQUEST;

}

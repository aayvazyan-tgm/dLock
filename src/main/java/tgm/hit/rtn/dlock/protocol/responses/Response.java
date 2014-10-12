package tgm.hit.rtn.dlock.protocol.responses;

import tgm.hit.rtn.dlock.protocol.*;

import java.io.Serializable;

public class Response extends tgm.hit.rtn.dlock.protocol.Package implements Serializable {

	public PackageType type = PackageType.RESPONSE;

}

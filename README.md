# dLock [![Build Status](http://ci.truh.in/buildStatus/icon?job=dLock)](http://ci.truh.in/job/dLock/)

dLock is a peer to peer distributed lock manager.


#Installation:
Simply add the .jar as a library.

## As a maven dependency

Add the following repository:
http://ci.truh.in/nexus/content/repositories/releases/

* GroupId: tgm.hit.rtn
* ArtifactId: dLock

The available versions can be seen here:
http://ci.truh.in/nexus/content/repositories/releases/tgm/hit/rtn/dLock/
The version numbering is based on the revision and the sha1 hash of the used
commit.

A gradle build file, depending on dLock could look like this:

```gradle
apply plugin: 'java'

sourceCompatibility = 1.8

version = '1.0'

repositories {
    mavenCentral()
    maven {
        url "http://ci.truh.in/nexus/content/repositories/releases/"
    }
}

dependencies {
    compile group: 'tgm.hit.rtn', name: 'dLock', version: '+'
}
```

#Usage:
To get detailed information about the possibilities of dLock, take a look at the JavaDoc of the `DLock` class

##Simple Lock Example 1
example.java
```java
public class example {
    public void testObjectsPassed() throws InterruptedException {
        DLock lock = new DLock();
        lock.lock();
        foo();
        lock.unlock();
    }
}
```

##Lock Example 2
example.java
```java
public class example {
    public void testObjectsPassed() throws InterruptedException {
        Integer[] sumThis = new Integer[]{1, 1, 2};
        DLock lock = new DLock();
        //Here we wait until dLock is able to aquire a Lock and then keep the lock until Sum finished its run() call
        Integer result=(Integer) lock.lockWhile(new Sum(), sumThis);
    }
}
class Sum implements tgm.hit.rtn.dlock.Callback<Integer,Integer>{
    @Override
    public Integer run(Integer... params) {
        if(params==null)return 0;
        if(params.length==0)return 0;
        int sum=0;
        for (int i = 0; i < params.length; i++) {
            sum+=params[i].intValue();
        }
        return sum;
    }
}
```

#Contribution
Feel free to send pull requests and fork this repo.

##Design idea
Please note that this diagram does currently not represent the current state of this program.
![alt text](docs/Class_Diagram.png "Logo Title Text 1")

package shell;

public class Heap {

  private static final long START_ADDR = 65536; /* 0x10000 */
  private long nextFreeAddr;

  public Heap() {
    nextFreeAddr = START_ADDR;
  }

  public String allocateSpace(long numBytes) {
    String res = "0x" + Long.toHexString(nextFreeAddr);
    nextFreeAddr += numBytes;
    return res;
  }

  public void reset() {
    nextFreeAddr = START_ADDR;
  }
}

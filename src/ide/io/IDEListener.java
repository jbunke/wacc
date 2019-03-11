package ide.io;

import ide.IDE;
import redsquaregl.io.IOListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class IDEListener implements IOListener {
  private IDE ide;

  private boolean control;
  private boolean shift;

  public IDEListener(IDE ide) {
    this.ide = ide;
    control = false;
    shift = false;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    boolean capsLock = Toolkit.getDefaultToolkit().
            getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

    switch (e.getKeyCode()) {
      case KeyEvent.VK_SHIFT:
        shift = true;
        break;
      case KeyEvent.VK_CONTROL:
        control = true;
        break;
      case KeyEvent.VK_A: case KeyEvent.VK_B: case KeyEvent.VK_C:
      case KeyEvent.VK_D: case KeyEvent.VK_E: case KeyEvent.VK_F:
      case KeyEvent.VK_G: case KeyEvent.VK_H: case KeyEvent.VK_I:
      case KeyEvent.VK_J: case KeyEvent.VK_K: case KeyEvent.VK_L:
      case KeyEvent.VK_M: case KeyEvent.VK_N: case KeyEvent.VK_O:
      case KeyEvent.VK_P: case KeyEvent.VK_Q: case KeyEvent.VK_R:
      case KeyEvent.VK_S: case KeyEvent.VK_T: case KeyEvent.VK_U:
      case KeyEvent.VK_V: case KeyEvent.VK_W: case KeyEvent.VK_X:
      case KeyEvent.VK_Y: case KeyEvent.VK_Z: case KeyEvent.VK_0:
      case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3:
      case KeyEvent.VK_4: case KeyEvent.VK_5: case KeyEvent.VK_6:
      case KeyEvent.VK_7: case KeyEvent.VK_8: case KeyEvent.VK_9:
        String key = Character.toString((char) (e.getKeyCode()));
        key = (shift && !capsLock) || (!shift && capsLock)
                ? key.toUpperCase() : key.toLowerCase();
        typeString(key);
        break;
    }
  }

  private void typeString(String toAdd) {
    ide.typeString(toAdd);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    switch (e.getKeyCode()) {
      case KeyEvent.VK_SHIFT:
        shift = false;
        break;
      case KeyEvent.VK_CONTROL:
        control = false;
        break;
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}

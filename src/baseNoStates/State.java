package baseNoStates;

/**
 * Defines door states.
 */
public abstract class State {
  protected String id;
  protected Door porta; //tenim una variable porta per poder definir-la com a locked o unlocked
  public State(Door door) {
    this.porta = door;
  }
  public abstract void lock();
  public abstract void unlock();
  public abstract void unlockShortly();
  public abstract void open();
  public abstract void close();

  public String getId() {  return id;  }

}


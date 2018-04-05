/**
 *
 */
package apgas.glb;

/**
 * Bag presents the required methods for some bag to be processed successfully
 * by a {@link BagProcessor}.
 *
 * @author Patrick Finnerty
 *
 */
public interface Bag<B extends Bag<B>> {

  /**
   * Processes a certain amount of work as specified by the parameter and
   * returns.
   *
   * @param workAmount
   *          number of tasks / amount of work to process
   */
  public void process(int workAmount);

  /**
   * Creates a new instance of Bag which contains tasks shared by this instance
   * to be given to an other computation place. If no tasks can be shared,
   * should return {@code null} rather than an empty Bag.
   * <p>
   * As far as the bag splitting strategy is concerned (how much work is given),
   * this is left to the programmer.
   *
   * @return A new Bag containing tasks shared by this instance, null if no
   *         tasks can be shared.
   */
  public B split();

  /**
   * Merges the task bag given as parameter into this instance.
   * <p>
   * Unlike {@link #split()} which can return {@code null}, the provided
   * parameter will never be null (this is checked by the {@link BagProcessor}).
   *
   * @param b
   *          the tasks to be added to this task bag
   */
  public void merge(B b);

  /**
   * Indicates if the taskBag is empty, that is if all the tasks were performed.
   *
   * @return true if there are no tasks left in the Bag
   */
  public boolean isEmpty();

  /**
   * Sets the TaskProcessor in charge of computing this TaskBg. If at some point
   * the Bag creates some new Bag that should be computed by the task processor,
   * the value passed as paramater should be kept as a member of the class.
   * <p>
   * When the Bag is split and transferred from one place to another, the member
   * is updated automatically by the {@link BagProcessor}. If the {@link Bag}
   * does not spawn any Bag, the implementation of this method can be left
   * empty.
   *
   * @param p
   *          the new {@link BagProcessor} to be kept.
   */
  public void setProcessor(BagProcessor p);
}

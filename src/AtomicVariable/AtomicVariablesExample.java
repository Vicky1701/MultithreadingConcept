package AtomicVariable;

import java.util.concurrent.atomic.*;

public class AtomicVariablesExample {

    // Atomic variables
    private final AtomicInteger atomicInteger = new AtomicInteger(0);
    private final AtomicLong atomicLong = new AtomicLong(0L);
    private final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private final AtomicReference<String> atomicReference = new AtomicReference<>("Initial");
    private final AtomicReferenceArray<String> atomicReferenceArray = new AtomicReferenceArray<>(new String[]{"A", "B", "C"});

    // AtomicInteger example
    public void useAtomicInteger() {
        System.out.println("AtomicInteger Example:");
        System.out.println("Initial Value: " + atomicInteger.get());
        System.out.println("Incremented Value: " + atomicInteger.incrementAndGet());
        System.out.println("Decremented Value: " + atomicInteger.decrementAndGet());
    }

    // AtomicLong example
    public void useAtomicLong() {
        System.out.println("\nAtomicLong Example:");
        System.out.println("Initial Value: " + atomicLong.get());
        System.out.println("Updated Value: " + atomicLong.addAndGet(100L));
    }

    // AtomicBoolean example
    public void useAtomicBoolean() {
        System.out.println("\nAtomicBoolean Example:");
        System.out.println("Initial Value: " + atomicBoolean.get());
        atomicBoolean.set(true);
        System.out.println("Updated Value: " + atomicBoolean.get());
    }

    // AtomicReference example
    public void useAtomicReference() {
        System.out.println("\nAtomicReference Example:");
        System.out.println("Initial Value: " + atomicReference.get());
        atomicReference.compareAndSet("Initial", "Updated");
        System.out.println("Updated Value: " + atomicReference.get());
    }

    // AtomicReferenceArray example
    public void useAtomicReferenceArray() {
        System.out.println("\nAtomicReferenceArray Example:");
        System.out.println("Initial Value at Index 1: " + atomicReferenceArray.get(1));
        atomicReferenceArray.set(1, "Updated-B");
        System.out.println("Updated Value at Index 1: " + atomicReferenceArray.get(1));
    }

    // CAS example
    public void useCompareAndSwap() {
        System.out.println("\nCompare-And-Swap Example (AtomicInteger):");
        int expectedValue = 0;
        int newValue = 5;
        boolean wasUpdated = atomicInteger.compareAndSet(expectedValue, newValue);
        System.out.println("CAS Successful: " + wasUpdated + ", Current Value: " + atomicInteger.get());
    }

    // Atomic vs Volatile demonstration
    public void atomicVsVolatile() {
        System.out.println("\nAtomic Variables vs Volatile:");
        System.out.println("1. Volatile guarantees visibility, but not atomicity for compound actions.");
        System.out.println("2. Atomic variables provide atomicity and visibility for single-variable operations.");
        System.out.println("Example:");
        volatileExample();
    }

    // Example of volatile
    private volatile int volatileCounter = 0;

    public void volatileExample() {
        System.out.println("Volatile Counter Initial Value: " + volatileCounter);
        volatileCounter++;
        System.out.println("Volatile Counter After Increment: " + volatileCounter);
    }

    public static void main(String[] args) {
        AtomicVariablesExample example = new AtomicVariablesExample();

        example.useAtomicInteger();
        example.useAtomicLong();
        example.useAtomicBoolean();
        example.useAtomicReference();
        example.useAtomicReferenceArray();
        example.useCompareAndSwap();
        example.atomicVsVolatile();
    }
}

/**
 * Interview Questions and Answers:
 * 
 * Q1: What is the purpose of Atomic variables in Java?
 * A1: Atomic variables allow performing atomic operations on single variables, ensuring thread safety without explicit synchronization.
 * 
 * Q2: What is Compare-And-Swap (CAS)?
 * A2: CAS is a hardware-level atomic instruction used by atomic variables. It checks if the current value matches an expected value and updates it to a new value atomically.
 * 
 * Q3: What are the common Atomic classes in Java?
 * A3: Common Atomic classes include:
 *     - AtomicInteger
 *     - AtomicLong
 *     - AtomicBoolean
 *     - AtomicReference
 *     - AtomicReferenceArray
 * 
 * Q4: How does Atomic variables differ from volatile?
 * A4: Volatile guarantees visibility across threads but does not ensure atomicity for compound operations. Atomic variables provide both visibility and atomicity.
 * 
 * Q5: When would you use AtomicReference?
 * A5: AtomicReference is used when you need to perform atomic operations on object references.
 * 
 * Q6: What is AtomicReferenceArray?
 * A6: AtomicReferenceArray provides atomic operations for an array of object references, ensuring thread-safe access to elements.
 * 
 * Q7: What are some common methods in AtomicInteger?
 * A7: 
 *     - `get()`: Retrieves the current value.
 *     - `incrementAndGet()`: Increments the value atomically and returns it.
 *     - `compareAndSet(expectedValue, newValue)`: Atomically sets the value if it matches the expected value.
 * 
 * Q8: Are atomic operations lock-free?
 * A8: Yes, atomic operations are lock-free as they rely on CAS operations.
 * 
 * Q9: What are the limitations of Atomic variables?
 * A9: Atomic variables are limited to single-variable operations and cannot handle complex multi-variable logic.
 * 
 * Q10: What happens if CAS fails?
 * A10: If CAS fails, the operation is retried until it succeeds or some other logic handles the failure.
 */


/**
 * Interview Questions and Answers:
 *
 * Q1: What are Atomic variables in Java?
 * A1: Atomic variables are part of the `java.util.concurrent.atomic` package and provide a way to perform atomic operations on single variables, ensuring thread-safety without using explicit synchronization.
 * 
 * Q2: Why are Atomic variables preferred over synchronization?
 * A2: Atomic variables use low-level hardware instructions to perform operations atomically, avoiding the overhead of synchronization and locks, leading to better performance in certain scenarios.
 * 
 * Q3: What are the commonly used Atomic classes in Java?
 * A3: Commonly used Atomic classes include:
 *    - `AtomicInteger`: For atomic operations on integers.
 *    - `AtomicLong`: For atomic operations on long values.
 *    - `AtomicBoolean`: For atomic operations on boolean values.
 *    - `AtomicReference`: For atomic operations on object references.
 *    - `AtomicStampedReference` and `AtomicMarkableReference`: To handle CAS with additional metadata.
 * 
 * Q4: How do Atomic variables ensure atomicity?
 * A4: Atomic variables use **Compare-And-Swap (CAS)** operations at the hardware level to ensure that read-modify-write operations are performed atomically.
 * 
 * Q5: What are some commonly used methods of `AtomicInteger`?
 * A5: Common methods include:
 *    - `get()`: Gets the current value.
 *    - `set(int newValue)`: Sets the value to `newValue`.
 *    - `getAndIncrement()`: Atomically increments and returns the previous value.
 *    - `incrementAndGet()`: Atomically increments and returns the updated value.
 *    - `compareAndSet(int expect, int update)`: Atomically sets the value if it equals the expected value.
 * 
 * Q6: What is CAS (Compare-And-Swap) in the context of Atomic variables?
 * A6: CAS is a hardware-supported mechanism that compares the current value of a variable with an expected value, and if they match, swaps it with a new value atomically. This avoids race conditions without locks.
 * 
 * Q7: Can Atomic variables completely replace synchronization?
 * A7: No, Atomic variables are suitable for simple use cases where operations are confined to single variables. For complex operations involving multiple variables, explicit synchronization or locks are required.
 * 
 * Q8: How do Atomic variables differ from volatile variables?
 * A8:
 *    - `volatile` ensures visibility but does not guarantee atomicity.
 *    - Atomic variables provide both visibility and atomicity for individual operations.
 * 
 * Q9: What are some use cases for Atomic variables?
 * A9: Use cases include:
 *    - Implementing counters in multi-threaded environments.
 *    - Creating non-blocking algorithms.
 *    - Managing state flags atomically in concurrent systems.
 * 
 * Q10: Are Atomic variables lock-free?
 * A10: Yes, Atomic variables are lock-free as they rely on CAS rather than traditional locking mechanisms.
 * 
 * Q11: What are the limitations of Atomic variables?
 * A11:
 *    - Limited to single-variable operations.
 *    - Cannot handle complex logic or operations involving multiple variables.
 * 
 * Q12: How does `compareAndSet` work in Atomic variables?
 * A12: The `compareAndSet` method checks if the current value matches an expected value. If they match, it updates the value to a new value atomically. It returns `true` if successful, otherwise `false`.
 * 
 * Q13: Can you provide an example of `AtomicReference`?
 * A13: 
 * ```java
 * AtomicReference<String> atomicString = new AtomicReference<>("Initial");
 * atomicString.compareAndSet("Initial", "Updated");
 * System.out.println(atomicString.get()); // Output: Updated
 * ```
 * 
 * Q14: When should you use `AtomicStampedReference` or `AtomicMarkableReference`?
 * A14: These classes are used to handle scenarios like ABA problems by adding additional metadata (stamp or mark) to the value being compared and updated.
 */

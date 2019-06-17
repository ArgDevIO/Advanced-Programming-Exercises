package Exercises1;

public class CombinationLock {
    private int combination;
    private boolean isOpen;

    public CombinationLock(int combination) {
        this.combination = combination;
        this.isOpen = false;
    }

    public boolean open(int combination){
        this.isOpen = (this.combination == combination);
        return this.isOpen;
    }

    public boolean changeCombo(int oldCombination, int newCombination){
        boolean isCorrect = (oldCombination == combination);
        if (isCorrect){
            this.combination = newCombination;
        }
        return isCorrect;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public static void main(String[] args) {

        CombinationLock lock = new CombinationLock(123);

        System.out.println("Is the lock open? -> " + lock.isOpen());
        System.out.println("Opening the lock -> " + lock.open(123));
        System.out.println("Is the lock open? -> " + lock.isOpen());
    }
}

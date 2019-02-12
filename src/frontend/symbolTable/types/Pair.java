package frontend.symbolTable.types;

public class Pair extends Type {
    private final Type first;
    private final Type second;

    public Pair(Type fst, Type snd) {
        this.first = fst;
        this.second = snd;
    }

    public Type getFirst() {
        return first;
    }

    public Type getSecond() {
        return second;
    }

    @Override
    public boolean equals(Type other) {
        if (other == this){
            return true;
        }

        if (other instanceof InnerPair){
            return true;
        }

        if (other instanceof Pair){
            Pair pair = (Pair) other;

            return checkEachElement(first, pair.first) && checkEachElement(second, pair.second);
        }

        //obj not an instance of pair
        return false;
    }

    private boolean checkEachElement(Type x, Type y) {
        return x == null || y == null || x.equals(y);
    }

    @Override
    public String toString() {
        String a = first == null ? "pair" : first.toString();
        String b = second == null ? "pair" : second.toString();

        return "pair(" + a + ", " + b + ")";
    }
}

package first;

public class MyHashSet {
    private final int initCapacity = 4;
    private Object[] buckets = new Object[initCapacity];

    private void extendBuckets() {
        Object[] newBuckets = new Object[buckets.length * 2];
        boolean bucketsIsCloned = false;
        while (!bucketsIsCloned) {
            for (int i = 0; i < buckets.length; i++) {
                Object elem = buckets[i];
                if (elem == null) continue;
                int newHCIndex = elem.hashCode() % newBuckets.length;
                if (newBuckets[newHCIndex] == null) {
                    newBuckets[newHCIndex] = elem;
                } else {
                    newBuckets = new Object[newBuckets.length * 2];
                    break;
                }
                bucketsIsCloned = i == buckets.length - 1;
            }
        }
        buckets = newBuckets;
    }

    public boolean contains(Object o) {
        int hcIndex = o.hashCode() % buckets.length;
        return buckets[hcIndex] != null && buckets[hcIndex].equals(o);
    }

    public boolean add(Object o) {
        int hcIndex = o.hashCode() % buckets.length;
        if (this.contains(o)) {
            return false;
        } else if (buckets[hcIndex] == null) {
            buckets[hcIndex] = o;
        } else {
            while (buckets[hcIndex] != null) {
                extendBuckets();
            }
            buckets[hcIndex] = o;
        }
        return true;
    }

    public boolean remove(Object o) {
        int hcIndex = o.hashCode() % buckets.length;
        if (this.contains(o)) {
            buckets[hcIndex] = null;
            return true;
        } else {
            return false;
        }
    }
}

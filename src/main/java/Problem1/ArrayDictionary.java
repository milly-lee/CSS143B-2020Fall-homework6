package Problem1;

public class ArrayDictionary implements Dictionary {
    private KVEntry[] entries;

    public ArrayDictionary(int capacity) {
        entries = new KVEntry[capacity];
    }

    @Override
    public boolean isEmpty() {
        // NOT IMPLEMENTED YET
        return false;
    }

    private int hashFunction(String key) {
        // not ideal
        return key.length();
    }

    @Override
    public void put(String key, String value) {

        int hashedKey = hashFunction(key);

        // when there's no entry yet
        if (entries[hashedKey] == null) {
            entries[hashedKey] = new KVEntry(key, value);
            return;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr.next != null) {
            // update value if key already exists
            if (ptr.key.equals(key)) {
                ptr.value = value;
                return;
            }
            ptr = ptr.next;
        }

        // add an entry to the end of the chain
        ptr.next = new KVEntry(key, value);
    }

    @Override
    public void remove(String key) {
        int hashedKey = hashFunction(key);

        if (entries[hashedKey] == null) //entries doesn't exist, do nothing
        {
            return;
        }

        KVEntry ptr = entries[hashedKey];   //point to the start of the linked list
        KVEntry prev  = null;

        while (ptr != null && !ptr.key.equals(key)) {

            prev = ptr;
            ptr = ptr.next;

        }

        if (ptr != null)    //only remove if found
        {
            if (prev == null)   // first special case, removing head :-)
            {
                entries[hashedKey] = ptr.next;
            }
            else     // second special case, removing from center :^)
            {
                prev.next = ptr.next;
            }

        }




    }

    @Override
    public String get(String key) {
        int hashedKey = hashFunction(key);

        if (entries[hashedKey] == null) {
            return null;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return ptr.value;
            }
            ptr = ptr.next;
        }
        return null;
    }

    @Override
    public boolean contains(String key) {
        int hashedKey = hashFunction(key);

        if (hashedKey >= entries.length) {
            return false;
        }

        if (entries[hashedKey] == null) {
            return false;
        }

        KVEntry ptr = entries[hashedKey];
        while (ptr != null) {
            if (ptr.key.equals(key)) {
                return true;
            }
            ptr = ptr.next;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < entries.length; i++) {
            if (entries[i] == null) {
                builder.append("dictionary[" + i + "] = null\n");
                continue;
            }
            KVEntry ptr = entries[i];
            builder.append("dictionary[" + i + "] = {");
            while (ptr != null) {
                builder.append("(" + ptr.key + ", " + ptr.value + ")");
                ptr = ptr.next;
            }
            builder.append("}\n");
        }
        return builder.toString();
    }
}

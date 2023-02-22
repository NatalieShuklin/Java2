import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ex2 {
    private Date time;
    private String name;
    private List<Long> numbers;
    private List<String> strings;

    public ex2(Date time, String name, List<Long> numbers, List<String>
            strings) {
        this.time = time;
        this.name = name;
        this.numbers = numbers;
        this.strings = strings;
    }

    public boolean equals(Object obj) {
        if (name == null){
            return false;
        }
        if (obj instanceof ex2) {
            return name.equals(((ex2) obj).name);
        }
        return false;
    }

    public String toString() {
        String out = name;
        if (name == null || numbers==null){
            return out;
        }
        for (Long item : numbers) {
            out += " " + item;
        }
        return out;
    }

    public void removeString(String str) {
        strings.removeIf(s -> s.equals(str));
    }

    public boolean containsNumber(long number) {
        if (numbers == null || numbers.size() == 0 ){
            return false;
        }
        for (Long num : numbers) {
            if (num != null && num == number) {
                return true;
            }
        }
        return false;
    }

    public boolean isHistoric() {
        if (time != null) {
            return time.before(new Date());
        }
        return false;
    }

}
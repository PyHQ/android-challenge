package py.keeporswap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by wvk on 11/27/17.
 */

public class Snippet implements Serializable{
    public String code;
    public int indexFirst, indexSecond;
    public String wordFirst, wordSecond;
    public boolean swap;

    public Snippet(String code, int indexFirst, int indexSecond, String wordFirst, String wordSecond, boolean swap) {
        this.code = code;
        this.indexFirst = indexFirst;
        this.indexSecond = indexSecond;
        this.wordFirst = wordFirst;
        this.wordSecond = wordSecond;
        this.swap = swap;
    }

    public static Queue<Snippet> getSnippets() {
        Queue<Snippet> snippets = new LinkedList<>();
        snippets.add(new Snippet("data = [1,2]\nfor i in i:\n  print data", 22, 33, "i", "data", true));
        snippets.add(new Snippet("data = [1, 2]\nif 1 in data:\n  print '1 is present'", 17, 22, "1", "data", false));
        snippets.add(new Snippet("name = 'hello.txt'\nfile = open('r', name)\nfor line in file:\n  print(line)", 31, 36, "'r'", "name", true));
        snippets.add(new Snippet("from string import Template;\nt = Template('Hello, $placeholder !')", 5, 19, "string", "Template", false));
        snippets.add(new Snippet("string = 'Hello'\npattern = 'l*'\nprog = re.compile(string)\nresult = prog.match(pattern)", 50, 78, "string", "pattern", true));
        return snippets;
    }
}
